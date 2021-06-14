package items;

import geometries.BoundingBox;
import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

public class PoolBalls extends Item {

    PoolBall pb51;
    PoolBall pb52;
    PoolBall pb53;
    PoolBall pb54;
    PoolBall pb55;

    PoolBall pb41;
    PoolBall pb42;
    PoolBall pb43;
    PoolBall pb44;

    PoolBall pb31;
    PoolBall pb32;
    PoolBall pb33;

    PoolBall pb21;
    PoolBall pb22;

    PoolBall pb11;

    public PoolBalls(Point3D p){
        pb11 = new PoolBall(p.add(new Vector(0,0,0.1)), Color.BLACK);

        pb21 = new PoolBall(p.add(new Vector(0.2,0.1,0.1)));
        pb22 = new PoolBall(p.add(new Vector(0.2,-0.1,0.1)));

        pb31 = new PoolBall(p.add(new Vector(0.4,0.2,0.1)));
        pb32 = new PoolBall(p.add(new Vector(0.4,0,0.1)));
        pb33 = new PoolBall(p.add(new Vector(0.4,-0.2,0.1)));

        pb41 = new PoolBall(p.add(new Vector(0.6,0.3,0.1)));
        pb42 = new PoolBall(p.add(new Vector(0.6,0.1,0.1)));
        pb43 = new PoolBall(p.add(new Vector(0.6,-0.1,0.1)));
        pb44 = new PoolBall(p.add(new Vector(0.6,-0.3,0.1)));


        pb51 = new PoolBall(p.add(new Vector(0.8,0.4,0.1)));
        pb52 = new PoolBall(p.add(new Vector(0.8,0.2,0.1)));
        pb53 = new PoolBall(p.add(new Vector(0.8,0,0.1)));
        pb54 = new PoolBall(p.add(new Vector(0.8,-0.2,0.1)));
        pb55 = new PoolBall(p.add(new Vector(0.8,-0.4,0.1)));

    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray) {
        if(CreateBox().findGeoIntersections(ray) == null){
            return null;
        }
        LinkedList<GeoPoint> L = new LinkedList<GeoPoint>();
        LinkedList<GeoPoint> temp = new LinkedList<GeoPoint>();

        temp = pb51.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb52.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb53.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb54.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb55.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }

        temp = pb41.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb42.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb43.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb44.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }

        temp = pb31.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb32.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb33.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }

        temp = pb21.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = pb22.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }

        temp = pb11.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }

        if(L.isEmpty()){
            return null;
        }
        return L;
    }

    @Override
    public BoundingBox CreateBox(){
        if(Box != null)
        {
            return Box;
        }
        Box = pb11.CreateBox().
                add(pb21.CreateBox())
                .add(pb22.CreateBox())
                .add(pb31.CreateBox())
                .add(pb32.CreateBox())
                .add(pb33.CreateBox())
                .add(pb41.CreateBox())
                .add(pb42.CreateBox())
                .add(pb43.CreateBox())
                .add(pb44.CreateBox())
                .add(pb51.CreateBox())
                .add(pb52.CreateBox())
                .add(pb53.CreateBox())
                .add(pb54.CreateBox())
                .add(pb55.CreateBox());
        return Box;
    }

}
