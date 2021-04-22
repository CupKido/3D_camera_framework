package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class Tube implements Geometry
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
    public LinkedList<Point3D> findIntersections(Ray ray) {
        return null;

    }
}
