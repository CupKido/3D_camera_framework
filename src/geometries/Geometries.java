package geometries;

import primitives.Point3D;
import primitives.Ray;
import renderer.Render;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    LinkedList<Intersectable> L; //Because we assume that ArrayList would take more time

    public Geometries(){
        L = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries){
        L = new LinkedList<Intersectable>(Arrays.asList(geometries));
    }
    public void add(Intersectable... geometries){
        L.addAll(Arrays.asList(geometries));
    }

    /**
     * creates a Bounding box from all the objects in L, saves it and returns it
     * @return
     */
    public BoundingBox getBox(){
        BoundingBox inner;
            for (Intersectable Inter:
                    L) {
                inner = Inter.CreateBox();
                if (inner != null) {
                    if (Box == null) {
                        Box = new BoundingBox(inner.min, inner.max);
                    }else {
                        Box.add(Inter.CreateBox());
                    }
                }
            }
        return Box;
    }

    BoundingBox Box = null;

    /**
     * goes over all the geometries and returns a list of all of their intersections with ray
     * if the BVH is active, works recursively
     * @param ray
     * @return
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> listPoints = null;
        for (Intersectable geometry : L) {
            if (geometry.CreateBox().findGeoIntersections(ray)) {
                List<GeoPoint> points = geometry.findGeoIntersections(ray);
                if (points != null) {
                    if (listPoints == null)
                        listPoints = new LinkedList<>();
                    listPoints.addAll(points);
                }
            }
        }
        return listPoints;
    }

    /**
     * if theres a box, it returns it,
     *    if not, it creates it with getBox()
     * @return
     */
    @Override
    public BoundingBox CreateBox() {
        if(Box == null){
            return getBox();
        }
        else return Box;
    }


    /**
     * puts the geometries in the right boxes and creates a fitting tree
     */
    public void CreateBVH(){
        if(L.size() <= 4){
            return;
        }

        CreateBox();
        char axis = 'x';
        double x = Box.max.getX().getCoord() - Box.min.getX().getCoord();
        double y = Box.max.getY().getCoord() - Box.min.getY().getCoord();
        double z = Box.max.getZ().getCoord() - Box.min.getZ().getCoord();

        if(y > x && y >z){
            axis = 'y';
        }
        else if(z > x && z >y){
            axis = 'z';
        }


        char finalAxis = axis;
        L.sort((Intersectable a, Intersectable b) -> {
            if(sizeRelToAxis(a, finalAxis) > sizeRelToAxis(b, finalAxis)){
                return 1;
            }else if(sizeRelToAxis(a, finalAxis) < sizeRelToAxis(b, finalAxis)){
                return -1;
            }
            return 0;
        });

        Geometries left = new Geometries();
        Geometries right = new Geometries();
        for(int i = 0; i < L.size()/2 ; i++){
            left.add(L.get(i));
        }
        for(int i = L.size()/2; i < L.size() ; i++){
            right.add(L.get(i));
        }
        L.clear();
        right.CreateBVH();
        left.CreateBVH();


        L.add(left);
        L.add(right);
    }

    /**
     * sorts the list of intersectables with bubble sort
     * @param L
     * @param axis
     */
    private void bubbleSort(LinkedList<Intersectable> L, char axis)
    {
        int n = L.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++) {
                if (sizeRelToAxis(L.get(j), axis) > sizeRelToAxis(L.get(j + 1), axis)) {
                    // swap arr[j+1] and arr[j]
                    Intersectable temp = L.get(j);
                    L.set(j, L.get(j + 1));
                    L.set(j + 1, temp);
                }
            }
    }

    /**
     *  returns the middle of the box relatively to the selected axis
     * @param inter
     * @param axis
     * @return
     */
    private double sizeRelToAxis(Intersectable inter, char axis){
        BoundingBox temp = inter.CreateBox();
        double sum = 0;
        switch (axis) {
            case 'x':
                sum = temp.max.getX().getCoord() + temp.min.getX().getCoord();
                break;
            case 'y':
                sum = temp.max.getY().getCoord() + temp.min.getY().getCoord();
                break;
            case 'z':
                sum = temp.max.getZ().getCoord() + temp.min.getZ().getCoord();
                break;
        }
        return sum/2;
    }
}
