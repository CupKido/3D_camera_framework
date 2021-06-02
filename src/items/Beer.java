package items;

import geometries.Cylinder;
import geometries.Geometry;
import geometries.Sphere;
import primitives.*;

import java.util.LinkedList;

public class Beer extends Geometry {

    Geometry bottom;
    Geometry top;
    Geometry neck;

    private static final Color BeerC = new Color(73, 29, 0);
    private static final Material BeerM = new Material().setKd(0.2).setKs(0.9).setShininess(100).setKt(0.3);

    public Beer(Point3D point, double height){
        bottom = new Cylinder(new Ray(point, new Vector(0,0,1)), 0.08001, height * 2/3)
                .setEmission(BeerC).setMaterial(BeerM);
        top = new Sphere(point.add(new Vector(0,0,1).scale(height * 2/3.2)), 0.08)
                .setEmission(BeerC).setMaterial(BeerM);
        neck = new Cylinder(new Ray(point.add(new Vector(0,0,1).scale(height * 2/2.95)), new Vector(0,0,1)), 0.03, height * 1/3)
                .setEmission(BeerC).setMaterial(BeerM);

    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray) {
        LinkedList<GeoPoint> L = new LinkedList<GeoPoint>();
        LinkedList<GeoPoint> temp = new LinkedList<GeoPoint>();

        temp = bottom.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = top.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = neck.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        if(L.isEmpty()){
            return null;
        }
        return L;
    }
}
