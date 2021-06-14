package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class Triangle extends Polygon{
    public Triangle(Point3D p1, Point3D p2, Point3D p3)
    {
        super(p1,p2,p3);
    }


    @Override
    public Vector getNormal(Point3D point3D) {

        return plane.getNormal();
//        try {
//            plane.getNormal(point3D);
//        }catch (Exception ex){
//            throw new IllegalArgumentException("Point not in triangle");
//        }
//        Vector v0 = vertices.get(1).subtract(vertices.get(0));
//        Vector v1 = vertices.get(2).subtract(vertices.get(0));
//        Vector v2 = point3D.subtract(vertices.get(0));
//        double dot00 = v0.dotProduct(v0);
//        double dot01 = v0.dotProduct(v1);
//        double dot02 = v0.dotProduct(v2);
//        double dot11 = v1.dotProduct(v1);
//        double dot12 = v1.dotProduct(v2);
//
//        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
//        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
//        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
//// Check if point is in triangle
//        if((u >= 0) && (v >= 0) && (u + v < 1))
//        {
//            return plane.getNormal(point3D);
//        }
//        throw new IllegalArgumentException("Point not in triangle");
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray){
        LinkedList<GeoPoint> L = plane.findGeoIntersections(ray);
        if(L == null){
            return null;
        }
        Vector v0 = vertices.get(1).subtract(vertices.get(0));
        Vector v1 = vertices.get(2).subtract(vertices.get(0));
        Vector v2 = L.get(0).point.subtract(vertices.get(0));
        double dot00 = v0.dotProduct(v0);
        double dot01 = v0.dotProduct(v1);
        double dot02 = v0.dotProduct(v2);
        double dot11 = v1.dotProduct(v1);
        double dot12 = v1.dotProduct(v2);

        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
// Check if point is in triangle
        if((u >= 0) && (v >= 0) && (u + v < 1))
        {
            L.getFirst().geometry = this;
            return L;
        }
        return null;
    }

    @Override
    public BoundingBox CreateBox() {

        Box =
                new BoundingBox(new Point3D(
                        Math.min(Math.min(vertices.get(0).getX().getCoord(),vertices.get(1).getX().getCoord() ), vertices.get(2).getX().getCoord()),
                        Math.min(Math.min(vertices.get(0).getY().getCoord(), vertices.get(1).getY().getCoord()), vertices.get(2).getY().getCoord()),
                        Math.min(Math.min(vertices.get(0).getZ().getCoord(), vertices.get(1).getZ().getCoord()), vertices.get(2).getZ().getCoord())
                ),

                new Point3D(
                      Math.max(Math.max(vertices.get(0).getX().getCoord(),vertices.get(1).getX().getCoord() ), vertices.get(2).getX().getCoord()),
                      Math.max(Math.max(vertices.get(0).getY().getCoord(), vertices.get(1).getY().getCoord()), vertices.get(2).getY().getCoord()),
                      Math.max(Math.max(vertices.get(0).getZ().getCoord(), vertices.get(1).getZ().getCoord()), vertices.get(2).getZ().getCoord())

                )
        );
        return Box;
    }
}
