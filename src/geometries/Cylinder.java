package geometries;

import primitives.*;

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
        Point3D P1 = getAxisRay().getP0().add(getAxisRay().getDir().scale(height));
        if(P1.equals(var1))
        {
            return getAxisRay().getDir();
        }
        if(getAxisRay().getP0().equals(var1)){
            return getAxisRay().getDir().scale(-1);
        }
        try {
            if (var1.subtract(P1).dotProduct(getAxisRay().getDir()) == 0) {
                return getAxisRay().getDir().normalized();
            }



            if (var1.subtract(getAxisRay().getP0()).dotProduct(getAxisRay().getDir()) == 0) {
                return getAxisRay().getDir().scale(-1);
            }
        }catch (Exception e){
            return getAxisRay().getDir();
        }
        return super.getNormal(var1);
    }
    public Vector getNormal()
    {
        return null;
    }
}
