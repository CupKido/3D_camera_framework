package items;

import geometries.BoundingBox;
import geometries.Geometry;
import geometries.Sphere;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class PoolBall extends Item {
    public Geometry ball;

    public PoolBall(Point3D p){
        ball = new Sphere(p, 0.1)
                .setEmission(Color.RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(70));
    }

    public PoolBall(Point3D p, Color c){
        ball = new Sphere(p, 0.1)
                .setEmission(c).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(70));
    }

    public PoolBall(Point3D p, Color c, double size){
        ball = new Sphere(p, size/2)
                .setEmission(c).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(70));
    }



    @Override
    public Vector getNormal(Point3D point) {
        return ball.getNormal(point);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return ball.findGeoIntersections(ray);
    }

    @Override
    public BoundingBox CreateBox(){
        if(Box != null){
            return Box;
        }
        Box = ball.CreateBox();
        return Box;
    }

}
