package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

public class Cube extends Geometry{

    Square up, down, left, right, forward, backward;

    public Cube(Point3D URF, Point3D URB, Point3D ULF, Point3D ULB, Point3D DRF, Point3D DRB, Point3D DLF, Point3D DLB){
        up = new Square(ULB, ULF, URB, URF);
        down = new Square(DLB, DRB, DLF, DRF);
        left = new Square(ULF, ULB, DLF, DLB);
        right = new Square(URF, URB, DRF, DRB);
        forward = new Square(URF, DRF, ULF, DLF);
        backward = new Square(URB, ULB, DRB, DLB);
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
}
