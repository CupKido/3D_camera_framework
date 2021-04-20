package geometries;

import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.*;

class PlaneTest {


    @Test
    void getNormal() {
        Plane p0 = new Plane(new Point3D(6, 1, 0), new Point3D(1, 2, 0), new Point3D(1, 4, 0));
        Vector vec = new Vector(0.5, 0, 0);

    }

    @Test
    void testGetNormal() {
    }
}