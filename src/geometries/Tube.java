package geometries;

import primitives.*;

import java.awt.*;
import java.util.LinkedList;
import static primitives.Util.*;

public class Tube extends Geometry
{
    private Ray axisRay;
    private double radius;

    public Tube(Ray AR, double R)
    {
        axisRay = AR;
        radius = R;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Ray: " + axisRay.toString() + ", Radius: " + radius;
    }

    public Vector getNormal(Point3D var1)
    {

        double t = var1.subtract(axisRay.getP0()).dotProduct(axisRay.getDir());
        Point3D O = axisRay.getP0().add(axisRay.getDir().scale(t));
        try {
            return var1.subtract(O).normalized();
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }
    }
    public Vector getNormal()
    {
        return null;
    }

    @Override
    public LinkedList<Point3D> findIntersections(Ray ray)
    {
        Vector d = ray.getDir();
        Vector v = axisRay.getDir();
        if(d.equals(v) || d.equals(v.scale(-1)))
            return null;

        LinkedList<Point3D> res = new LinkedList<Point3D>();
        Vector x = ray.getP0().subtract(axisRay.getP0());
        Vector nx = x.normalized();
        double dv = d.dotProduct(v);
        if(ray.getP0().equals(axisRay.getP0()) || nx.equals(v) || nx.equals(v.scale(-1)))
        {
            if(isZero(dv))
            {
                res.add(ray.getPoint(radius));
                return res;
            }
            res.add(ray.getPoint(Math.sqrt(radius * radius / d.subtract(v.scale(dv)).lengthSquared())));
            return res;
        }

        double xv = x.dotProduct(v);

        double a = 1 - dv * dv;
        double b = 2 * x.dotProduct(d) - 2 * dv * xv;
        double c = x.lengthSquared() - xv * xv - radius * radius;

        double disc = alignZero(b * b - 4 * a * c);

        if(disc < 0)
            return null;

        double t;

        if(disc == 0)
        {
            if(isZero(dv))
                return null;

            t = alignZero(-b / 2 * a);

            if(t <= 0)
                return null;

            res.add(ray.getPoint(t));
            return res;
        }

        double sqrtDisc = Math.sqrt(disc);
        t = alignZero((-b + sqrtDisc) / 2 * a);

        if(t > 0)
            res.add(ray.getPoint(t));

        t = alignZero((-b - sqrtDisc) / 2 * a);

        if(t > 0)
            res.add(ray.getPoint(t));

        if(res.isEmpty())
            return null;
        return res;
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray)
    {
        Vector d = ray.getDir();
        Vector v = axisRay.getDir();
        if(d.equals(v) || d.equals(v.scale(-1)))
            return null;

        LinkedList<GeoPoint> res = new LinkedList<GeoPoint>();
        Vector x = ray.getP0().subtract(axisRay.getP0());
        Vector nx = x.normalized();
        double dv = d.dotProduct(v);
        if(ray.getP0().equals(axisRay.getP0()) || nx.equals(v) || nx.equals(v.scale(-1)))
        {
            if(isZero(dv))
            {
                res.add(new GeoPoint(this, ray.getPoint(radius)));
                return res;
            }
            res.add(new GeoPoint(this, ray.getPoint(Math.sqrt(radius * radius / d.subtract(v.scale(dv)).lengthSquared()))));
            return res;
        }

        double xv = x.dotProduct(v);

        double a = 1 - dv * dv;
        double b = 2 * x.dotProduct(d) - 2 * dv * xv;
        double c = x.lengthSquared() - xv * xv - radius * radius;

        double disc = alignZero(b * b - 4 * a * c);

        if(disc < 0)
            return null;

        double t;

        if(disc == 0)
        {
            if(isZero(dv))
                return null;

            t = alignZero(-b / 2 * a);

            if(t <= 0)
                return null;

            res.add(new GeoPoint(this, ray.getPoint(t)));
            return res;
        }

        double sqrtDisc = Math.sqrt(disc);
        t = alignZero((-b + sqrtDisc) / 2 * a);

        if(t > 0)
            res.add(new GeoPoint(this, ray.getPoint(t)));

        t = alignZero((-b - sqrtDisc) / 2 * a);

        if(t > 0)
            res.add(new GeoPoint(this, ray.getPoint(t)));

        if(res.isEmpty())
            return null;
        return res;
    }
}
