package geometries;

import primitives.*;

public class Sphere implements Geometry
{
    Point3D q0;
    double radius;

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
        return q0.subtract(var1).normalized();
    }
    public Vector getNormal()
    {
        return null;
    }
}