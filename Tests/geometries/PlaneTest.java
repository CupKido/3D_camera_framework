package geometries;

import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    Plane PL = new Plane(
                new Point3D(0,0,0),
                new Point3D(0,5,0),
                new Point3D(5,0,0)
        );

    @Test
    void getNormal() {
        assertThrows(IllegalArgumentException.class, () -> { PL.getNormal(new Point3D(1,1,1));}, "getNormal() did return even though point is not in plane");
        assertDoesNotThrow(() -> { PL.getNormal(new Point3D(1,1,0));}, "getNormal() did not return even though point is in plane");
        assertEquals(new Vector(0,0,-1), PL.getNormal(new Point3D(1, 1, 0)));
    }

    @Test
    void testGetNormal() {
    }
}