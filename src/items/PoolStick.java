package items;

import geometries.Cylinder;
import geometries.Geometry;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class PoolStick extends Geometry {

    Geometry wood;
    Geometry edge;

    public PoolStick(Point3D p, Vector Dir) {
        wood = new Cylinder(new Ray(p, Dir), 0.05, 3.5)
                .setEmission(new Color(97,60,36)).setMaterial(new Material().setKd(0.5).setKs(0.4).setShininess(50));
        edge = new Cylinder(new Ray(p.add(Dir.normalized().scale(3.5)), Dir), 0.03, 0.08)
                .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.7).setKs(0.2).setShininess(10));
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> L = new LinkedList<>();
        List<GeoPoint> temp = new LinkedList<>();
        temp = wood.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = edge.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        if(L.isEmpty()){
            return null;
        }
        return L;
    }
}
