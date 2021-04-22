package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void Geometries() {

    }

    @Test
    void add() {
    }

    @Test
    void findIntersections() {
        Geometries g1 = new Geometries();
        Geometries g2 = new Geometries(new Plane(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(1,1,0)), new Sphere(new Point3D(3,3,4), 3), new Triangle(new Point3D(3.75,2.75,0), new Point3D(2.75,3.75,0), new Point3D(2.75,2.75,1)));

        assertNull(g1.findIntersections(new Ray(new Point3D(1,1,1), new Vector(89,7,6))), "ERROR: problem with empty seen.");
        assertNull(g2.findIntersections(new Ray(new Point3D(7,7,30), new Vector(0,1,0))), "ERROR: problem with seen full but no intersection.");
        assertEquals(2, g2.findIntersections(new Ray(new Point3D(3,3,1), new Vector(0,0,-1))).size(), "ERROR: problem with seen with part of the geometries intersections.");
        assertEquals(4, g2.findIntersections(new Ray(new Point3D(3,3,9), new Vector(0,0,-1))).size(),"ERROR: problem with seen with all of the geometries intersections.");
    }
}