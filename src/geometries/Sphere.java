package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sphere extends Geometry
{
    Point3D q0;
    double radius;

    public Sphere(Point3D p0, double r){
        if(r == 0)
        {
            throw new IllegalArgumentException("ERROR: cannot create 0 sphere");
        }
        q0 = p0;
        radius = r;
    }

    public Point3D getQ0() {
        return q0;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Center: " + q0.toString() + ", Radius: " + radius;
    }

    public Vector getNormal(Point3D var1)
    {
        if(q0.equals(var1)){
            throw new IllegalArgumentException("ERROR: Cannot create vector from same point");
        }
        return var1.subtract(q0).normalized();
    }
    public Vector getNormal()
    {
        return null;
    }


    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> L = new LinkedList<>();
        if(q0.equals(ray.getP0())){
            GeoPoint P = new GeoPoint(this, ray.getP0().add(ray.getDir().scale(radius)));
            L.add(P);
            return L;
        }
        Vector u = q0.subtract(ray.getP0());
        double Tm = ray.getDir().dotProduct(u);
        double d = Math.sqrt(u.length() * u.length() - (Tm * Tm));
        if(d >= radius){
            return null;
        }
        double Th = Math.sqrt(radius * radius - (d * d));
        double T1 = Util.alignZero(Tm - Th);
        double T2 = Util.alignZero(Tm + Th);
        if(T1 > 0)
        {
            L.add(new GeoPoint(this, ray.getPoint(T1)));
        }
        if(T2 > 0)
        {
            L.add(new GeoPoint(this, ray.getPoint(T2)));
        }
        if(L.isEmpty()){
            return null;
        }
        return L;
    }

    @Override
    public BoundingBox CreateBox() {
        if(Box != null){
            return Box;
        }
        Box = new BoundingBox(
                new Point3D(q0.getX().getCoord() - radius, q0.getY().getCoord() - radius,q0.getZ().getCoord() - radius),
                new Point3D(q0.getX().getCoord() + radius, q0.getY().getCoord() + radius,q0.getZ().getCoord() + radius)
        );
        return Box;
    }
}
