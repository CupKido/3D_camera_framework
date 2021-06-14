package items;

import geometries.BoundingBox;
import geometries.Geometry;
import geometries.Square;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

public class BallsHolder extends Item {

    Square Right;
    Square Left;
    Square Back;

    private static Vector FU = new Vector(-0.2,0,0.14);//front up
    private static Vector FD = new Vector(-0.2,0,0);//front down
    private static Vector LU = new Vector(0.9,0.65,0.14);//left up
    private static Vector LD = new Vector(0.9,0.65,0);//left down
    private static Vector RU = new Vector(0.9, -0.65, 0.14);//right up
    private static Vector RD = new Vector(0.9, -0.65, 0);//right down

    public BallsHolder(Point3D blackBall){
        Right = new Square(blackBall.add(FU),blackBall.add(FD),blackBall.add(RU),blackBall.add(RD));
        Left = new Square(blackBall.add(FU),blackBall.add(FD),blackBall.add(LU),blackBall.add(LD));
        Back = new Square(blackBall.add(LU),blackBall.add(LD),blackBall.add(RU),blackBall.add(RD));
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray) {
        LinkedList<GeoPoint> L = new LinkedList<>();
        LinkedList<GeoPoint> temp = new LinkedList<>();
        temp = Right.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = Left.findGeoIntersections(ray);
        if(temp != null){
            L.addAll(temp);
        }
        temp = Back.findGeoIntersections(ray);
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
        Box = Right.CreateBox();
        Box.add(Left.CreateBox());
        Box.add(Back.CreateBox());
        return Box;
    }
}
