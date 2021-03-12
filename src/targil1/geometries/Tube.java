package targil1.geometries;


import targil1.primitives.*;


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
        return null;
    }
    public Vector getNormal()
    {
        return null;
    }
}
