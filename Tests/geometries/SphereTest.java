package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import primitives.Vector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Sphere s0 = new Sphere(new Point3D( 0,0,0), 3);
    Sphere s1 = new Sphere(new Point3D( 5,5,5), 5);
    LinkedList<Point3D> L = new LinkedList<Point3D>();

    @Test
    void getNormal() {
        assertThrows(IllegalArgumentException.class, () -> {s0.getNormal(new Point3D(0,0,0));}, "ERROR: Sphere 0 was created");// p0 = val
        assertEquals(new Vector(0,1,0), s0.getNormal(new Point3D(0,2,0)), "ERROR: Sphere's getNormal() does not work");// p0 in sphere
        assertEquals(new Vector(0,1,0), s0.getNormal(new Point3D(0,3,0)), "ERROR: Sphere's getNormal() does not work");// p0 on sphere
    }

    @Test
    void findIntersections() {
        // ray in circle
        L.clear();
        L.add(new Point3D(5,5,0));
        assertEquals(L, s1.findIntersections(new Ray(new Point3D(5,5,4), new Vector(0,0,-1))), "ERROR: FindIntersections() does not work well.");

        // ray on circle and goes in
        L.clear();
        L.add(new Point3D(5,5,0));
        assertEquals(L, s1.findIntersections(new Ray(new Point3D(5,5,10), new Vector(0,0,-1))), "ERROR: FindIntersections() does not work well.");

        // ray on circle and goes out
        L.clear();
        assertEquals(null, s1.findIntersections(new Ray(new Point3D(5,5,0), new Vector(0,0,-1))) , "ERROR: FindIntersections() does not work well.");

        // ray out from circle and touches it
        L.clear();
        assertEquals(null, s1.findIntersections(new Ray(new Point3D(0,0,0), new Vector(1,1,0))) , "ERROR: FindIntersections() does not work well.");

        // ray out from circle and gets inside it
        L.clear();
        L.add(new Point3D(5,5,10));
        L.add(new Point3D(5,5,0));
        assertEquals(L, s1.findIntersections(new Ray(new Point3D(5,5,11), new Vector(0,0,-1))), "ERROR: FindIntersections() does not work well.");

        // ray out from circle and does not touch it
        L.clear();
        assertEquals(null, s1.findIntersections(new Ray(new Point3D(5,5,-1), new Vector(0,0,-1))) , "ERROR: FindIntersections() does not work well.");

    }
}