package items;

import geometries.BoundingBox;
import geometries.Geometry;
import geometries.Square;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

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
        Right = new Square(blackBall.add(FU),blackBall.add(FD),blackBall.add(RD),blackBall.add(RU));
        Left = new Square(blackBall.add(FU),blackBall.add(FD),blackBall.add(LD),blackBall.add(LU));
        Back = new Square(blackBall.add(LU),blackBall.add(LD),blackBall.add(RD),blackBall.add(RU));
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> L = new LinkedList<>();
        List<GeoPoint> temp = new LinkedList<>();
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
        if(Box != null){
            return Box;
        }
        Box = Right.CreateBox();
        Box.add(Left.CreateBox());
        Box.add(Back.CreateBox());
        return Box;
    }
}
