package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


class TriangleTest {

    Triangle T1 = new Triangle(new Point3D(5,5,5), new Point3D(0,5,5), new Point3D(5,0,5));
    LinkedList<Point3D> L = new LinkedList<Point3D>();

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

    @Test
    void findIntersections() {
        //Ray intersects with Triangle
        L.clear();
        L.add(new Point3D(4,4,5));
        assertEquals(L, T1.findIntersections(new Ray(new Point3D(1,1,1), new Vector(3,3,4))), "ERROR: findIntersections() Does not work well.");

        //Ray is orthogonal to The Triangle
        L.clear();
        L.add(new Point3D(3,3,5));
        assertEquals(L, T1.findIntersections(new Ray(new Point3D(3,3,0), new Vector(0,0,1))), "ERROR: findIntersections() Does not work well.");

        //Ray parallels to Triangle
        L.clear();
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(-1,0,0), new Vector(1,1,0))), "ERROR: findIntersections() Does not work well.");

        //Ray does not intersects with Triangle and is not parallel
        L.clear();
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(3,3,0), new Vector(-1,0,-1))), "ERROR: findIntersections() Does not work well.");

        //Ray intersects with vertex
        L.clear();
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(1,1,1), new Vector(1,1,1))), "ERROR: findIntersections() Does not work well.");

        //Ray starts from triangle and goes inside (Parallel)
        L.clear();
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(4,4,5), new Vector(1,1,0))), "ERROR: findIntersections() Does not work well.");
        //Ray starts from triangle and goes outside
        L.clear();
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(4,4,5), new Vector(1,0,1))), "ERROR: findIntersections() Does not work well.");

        //Ray starts from triangle and is orthogonal
        L.clear();
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(4,4,5), new Vector(0,0,1))), "ERROR: findIntersections() Does not work well.");


    }
}
