package jct.taler.targils.targil1.geometries;


import jct.taler.targils.targil1.primitives.*;


public class Tube implements Geometry
{
    Ray axisRay;
    double radius;

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
        return null;
    }
    public Vector getNormal()
    {
        return null;
    }
}
