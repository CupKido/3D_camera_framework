package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Sphere s0 = new Sphere(new Point3D( 0,0,0), 3);

    @Test
    void getNormal() {
        assertThrows(IllegalArgumentException.class, () -> {s0.getNormal(new Point3D(0,0,0));}, "ERROR: Sphere 0 was created");// p0 = val
        assertEquals(new Vector(0,1,0), s0.getNormal(new Point3D(0,2,0)), "ERROR: Sphere's getNormal() does not work");// p0 in sphere
        assertEquals(new Vector(0,1,0), s0.getNormal(new Point3D(0,3,0)), "ERROR: Sphere's getNormal() does not work");// p0 on sphere
    }

    @Test
    void findIntersections() {
    }
}