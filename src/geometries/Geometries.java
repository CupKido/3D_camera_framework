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

    public BoundingBox getBox(){
            for (Intersectable Inter:
                    L) {
                BoundingBox inner = Inter.CreateBox();
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

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray) {

         LinkedList<GeoPoint> res = new LinkedList<GeoPoint>();
        LinkedList<GeoPoint> intersects;
        getBox();
        for (Intersectable G : L) {
            if(G.CreateBox() == null){
                intersects = G.findGeoIntersections(ray);
                if (intersects != null) {
                    res.addAll(intersects);
                }
            }else if(G.CreateBox().findGeoIntersections(ray) != null) {
                intersects = G.findGeoIntersections(ray);
                if (intersects != null) {
                    res.addAll(intersects);
                }
            }
        }
        if(res.isEmpty()) return null;
        return res;
    }

//    private LinkedList<GeoPoint> EarlyRejectOff(Ray ray){
//        LinkedList<GeoPoint> res = new LinkedList<GeoPoint>();
//        LinkedList<GeoPoint> intersects;
//        for (Intersectable G : L) {
//            intersects = G.findGeoIntersections(ray);
//            if(intersects != null){
//                res.addAll(intersects);
//            }
//        }
//        if(res.isEmpty()) return null;
//        return res;
//    }


    @Override
    public BoundingBox CreateBox() {
        if(Box == null){
        return getBox();
        }
        else return Box;
    }

    public void CreateBVH(){
        if(L.size() <= 2){
            return;
        }

        getBox();
        char axis = 'x';
        double x = Box.max.getX().getCoord() - Box.min.getY().getCoord();
        double y = Box.max.getY().getCoord() - Box.min.getY().getCoord();
        double z = Box.max.getZ().getCoord() - Box.min.getZ().getCoord();

        if(y > x && y >z){
            axis = 'y';
        }
        if(z > x && z >y){
            axis = 'z';
        }

        bubbleSort(L, axis);

        Geometries left = new Geometries();
        Geometries right = new Geometries();
        for(int i = 0; i < L.size()/2 ; i++){
            left.add(L.get(i));
        }
        for(int i = L.size()/2; i < L.size() ; i++){
            right.add(L.get(i));
        }
        right.CreateBVH();
        left.CreateBVH();

        L.clear();
        L.add(left);
        L.add(right);
    }

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

    private double sizeRelToAxis(Intersectable inter, char axis){
        BoundingBox temp = inter.CreateBox();
        double sum = 0;
        switch (axis) {
            case 'x':
                sum = temp.max.getX().getCoord() - temp.min.getX().getCoord();
                break;
            case 'y':
                sum = temp.max.getY().getCoord() - temp.min.getY().getCoord();
                break;
            case 'z':
                sum = temp.max.getZ().getCoord() - temp.min.getZ().getCoord();
                break;
        }
        return sum;
    }
}
