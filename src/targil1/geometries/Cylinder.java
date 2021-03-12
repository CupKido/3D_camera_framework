package targil1.geometries;

import targil1.primitives.*;


public class Cylinder extends Tube
{
    private double height;

    public Cylinder(Ray AR, double R, double H)
    {
        super(AR, R);
        height = H;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return super.toString() + ", Height: " + height;
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
