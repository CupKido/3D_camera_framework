package targil1.geometries;

import targil1.primitives.*;


public class Cylinder extends Tube
{
    double height;

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
