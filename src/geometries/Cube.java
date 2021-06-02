package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

public class Cube extends Geometry{

    Square up, down, left, right, forward, backward;

    public Cube(Point3D URF, Point3D URB, Point3D ULF, Point3D ULB, Point3D DRF, Point3D DRB, Point3D DLF, Point3D DLB)
    {
        build(URF, URB, ULF, ULB, DRF, DRB, DLF, DLB);
    }

    public Cube(Point3D center, double height, double width, double depth)
    {
        if(height <= 0 || width <= 0 || depth <= 0)
            throw new IllegalArgumentException("ERROR: Size must by bigger then zero!");
        Vector up = new Vector(0,0,height/2);
        Vector down = new Vector(0,0,-height/2);
        Vector right = new Vector(width/2,0,0);
        Vector left = new Vector(-width/2,0,0);
        Vector back = new Vector(0,depth/2,0);
        Vector front = new Vector(0,-depth/2,0);

        build(center.add(up).add(right).add(front), center.add(up).add(right).add(back), center.add(up).add(left).add(front), center.add(up).add(left).add(back), center.add(down).add(right).add(front), center.add(down).add(right).add(back), center.add(down).add(left).add(front), center.add(down).add(left).add(back));
    }

    public Cube(Point3D center, double height, double width, double depth, Vector up, Vector right)
    {
        if(height <= 0 || width <= 0 || depth <= 0)
            throw new IllegalArgumentException("ERROR: Size must by bigger then zero!");
        if(up.dotProduct(right) != 0)
            throw new IllegalArgumentException("ERROR: Up and right are not vertical");

        up = up.normalized().scale(height/2);
        Vector down = up.scale(-1);
        right = right.normalized().scale(width/2);
        Vector left = right.scale(-1);
        Vector back = up.crossProduct(right).normalized().scale(depth/2);
        Vector front = back.scale(-1);

        build(center.add(up).add(right).add(front), center.add(up).add(right).add(back), center.add(up).add(left).add(front), center.add(up).add(left).add(back), center.add(down).add(right).add(front), center.add(down).add(right).add(back), center.add(down).add(left).add(front), center.add(down).add(left).add(back));
    }

    @Override
    public Vector getNormal(Point3D point) {
        if(up.inoroutNoExtra(point))
            return up.getNormal();
        if(right.inoroutNoExtra(point))
            return right.getNormal();
        if(left.inoroutNoExtra(point))
            return left.getNormal();
        if(down.inoroutNoExtra(point))
            return down.getNormal();
        if(forward.inoroutNoExtra(point))
            return forward.getNormal();
        if(backward.inoroutNoExtra(point))
            return backward.getNormal();
        return null;
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray) {
        LinkedList<GeoPoint> L = new LinkedList<GeoPoint>();
        LinkedList<GeoPoint> temp = new LinkedList<GeoPoint>();
        temp = up.findGeoIntersections(ray);
        if (temp != null)
        {
            L.addAll(temp);
        }
        temp = down.findGeoIntersections(ray);
        if (temp != null)
        {
            L.addAll(temp);
        }
        temp = left.findGeoIntersections(ray);
        if (temp != null)
        {
            L.addAll(temp);
        }
        temp = right.findGeoIntersections(ray);
        if (temp != null)
        {
            L.addAll(temp);
        }
        temp = forward.findGeoIntersections(ray);
        if (temp != null)
        {
            L.addAll(temp);
        }
        temp = backward.findGeoIntersections(ray);
        if (temp != null)
        {
            L.addAll(temp);
        }
        for (GeoPoint P:
             L) {
            P.geometry = this;
        }
        if(L.isEmpty()){
            return null;
        }
        return L;
    }

    void build(Point3D URF, Point3D URB, Point3D ULF, Point3D ULB, Point3D DRF, Point3D DRB, Point3D DLF, Point3D DLB){
        up = new Square(ULB, ULF, URB, URF);
        down = new Square(DLB, DRB, DLF, DRF);
        left = new Square(ULF, ULB, DLF, DLB);
        right = new Square(URF, URB, DRF, DRB);
        forward = new Square(URF, DRF, ULF, DLF);
        backward = new Square(URB, ULB, DRB, DLB);
    }
}
