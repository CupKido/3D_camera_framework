package targil1.geometries;

import targil1.primitives.*;

public class Plane implements Geometry {


    private Point3D q0;
    private Vector normal;

    //creates the normal vector and inserts one of the points in q0
    public Plane(Point3D p1, Point3D p2, Point3D p3)
    {
        q0 = p1;
        normal = null;


        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        try
        {
            normal = v2.crossProduct(v1);
        }catch(IllegalArgumentException ex)
        {
            throw ex;
        }

        normal.normalize();


    }

    public Plane(Point3D p, Vector v)
    {
        q0 = p;
        normal = v.normalized();
    }

    public Point3D getQ0() {
        return q0;
    }

    //checks if point is in-plane, then returns the normal vector
    public Vector getNormal(Point3D point)
    {
        if(point == null)
        {
            return normal;
        }
        Vector inPlane = q0.subtract(point);
        if(inPlane.dotProduct(normal) != 0)
        {
            throw new IllegalArgumentException("ERROR - Point is not in Plane");
        }

        //if(point != null && false);//todo צריכים לבדוק כאן (במקום הפולס) אם צריכים להחזיר את המינוס של הנורמל ואם כן אז לשנות את רז
        return normal;

    }

    //returns normal
    public Vector getNormal()
    {
        return getNormal(null);
    }
}
