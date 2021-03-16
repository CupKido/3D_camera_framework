package geometries;

import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.*;

class PlaneTest {

    Plane p0;
    Vector vec;
    @Test
    void getNormal() {
        try {
            p0 = new Plane(new Point3D(6, 1, 0), new Point3D(1, 2, 0), new Point3D(1, 4, 0));
            vec = new Vector(0.5, 0, 0);
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return;
        }
        System.out.println(p0.getNormal(Point3D.ZERO));
    }

    @Test
    void testGetNormal() {
    }
}