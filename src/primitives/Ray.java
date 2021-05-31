package primitives;


import geometries.Intersectable.GeoPoint;

import java.awt.geom.GeneralPath;
import java.util.LinkedList;

public class Ray {

    private static final double DELTA = 0.1;

    Point3D p0;
    Vector dir;

    public Point3D getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Ray(Point3D point3D, Vector Vec){
        p0 = point3D;
        dir = Vec.normalized();
    }

    public Ray(Point3D point3D, Vector Vec, Vector n){
        Vector Delta = n.scale(n.dotProduct(Vec) > 0 ? DELTA : - DELTA);
        Point3D point = point3D.add(Delta);
        p0 = point;
        dir = Vec.normalized();
    }

    @Override
    public String toString() {
        return p0.toString() + " x" + dir.toString();
    }

    public Point3D getPoint(double t){
        try {
            return p0.add(dir.scale(t));
        }catch (IllegalArgumentException e)
        {
            throw e;
        }
    }

    public Point3D findClosestPoint(LinkedList<Point3D> intersections){
        if(intersections == null)
        {
            return null;
        }

        Point3D ClosestPoint = intersections.getFirst();
        double shortestD = p0.distanceSquared(ClosestPoint);
        double currentD;

        for (Point3D point: intersections) {
            currentD = p0.distanceSquared(point);
            if(currentD < shortestD)
            {
                shortestD = currentD;
                ClosestPoint = point;
            }
        }
        return ClosestPoint;
    }

    public GeoPoint findClosestGeoPoint(LinkedList<GeoPoint> intersections){
        if(intersections == null || intersections.isEmpty())
        {
            return null;
        }

        GeoPoint ClosestPoint = intersections.getFirst();
        double shortestD = p0.distanceSquared(ClosestPoint.point);
        double currentD;

        for (GeoPoint point: intersections) {
            currentD = p0.distanceSquared(point.point);
            if(currentD < shortestD)
            {
                shortestD = currentD;
                ClosestPoint = point;
            }
        }
        return ClosestPoint;
    }
}
