package geometries;

import primitives.Point3D;
import primitives.Ray;

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
    @Override
    public LinkedList<Point3D> findIntersections(Ray ray)
    {
        LinkedList<Point3D> res = new LinkedList<Point3D>();
        LinkedList<Point3D> intersects;
        for (Intersectable G : L) {
            intersects = G.findIntersections(ray);
            if(intersects != null){
            res.addAll(intersects);
            }
        }
        if(res.isEmpty()) return null;
        return res;
    }
}
