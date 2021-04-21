package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
 */


class TriangleTest {

    @Test
    void getNormal() {
        Triangle Tri = new Triangle(
                new Point3D(0,0,0),
                new Point3D(0,5,0),
                new Point3D(5,0,0)
        );
        assertThrows(IllegalArgumentException.class, () -> { Tri.getNormal(new Point3D(-1,0,0));}, "getNormal() did return even though point is not on triangle");

        assertDoesNotThrow(() -> { Tri.getNormal(new Point3D(0,0,0));}, "getNormal() did not return even though point is on triangle");
        assertDoesNotThrow(() -> { Tri.getNormal(new Point3D(1,1,0));}, "getNormal() did not return even though point is on triangle");
        assertDoesNotThrow(() -> { Tri.getNormal(new Point3D(1,0,0));}, "getNormal() did not return even though point is on triangle");

        assertEquals(Tri.plane.getNormal(), Tri.getNormal(new Point3D(0,0,0)), "getNormal() for triangle didnt work");

    }
}