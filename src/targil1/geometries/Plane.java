package targil1.geometries;

import targil1.primitives.*;

public class Plane implements Geometry {
    Point3D q0;
    Vector normal;

    //creates the normal vector and inserts one of the points in q0
    public Plane(Point3D p1, Point3D p2, Point3D p3)
    {
        q0 = p1;
        normal = null;
        //todo לבדוק אם עדכני

        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        normal = v1.crossProduct(v2);
        normal.normalize();


    }

    public Plane(Point3D p, Vector v)
    {
        q0 = p;
        normal = v.normalized();
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
