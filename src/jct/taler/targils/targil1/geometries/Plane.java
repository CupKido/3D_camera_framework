package jct.taler.targils.targil1.geometries;

import java.util.List;
import jct.taler.targils.targil1.primitives.*;

public class Plane implements Geometry {
    Point3D q0;
    Vector normal;

    public Plane(Point3D p1, Point3D p2, Point3D p3)
    {
        q0 = p1;
        //todo אמורים לשנות את זה מתישהו
        normal = null;
    }

    public Plane(Point3D p, Vector v)
    {
        q0 = p;
        normal = v;
    }

    public Vector getNormal(Point3D point)
    {
        Vector res = normal;
        if(point != null && false);//todo צריכים לבדוק כאן (במקום הפולס) אם צריכים להחזיר את המינוס של הנורמל ואם כן אז לשנות את רז
        return res;
    }
    public Vector getNormal()
    {
        return getNormal(null);
    }
}
