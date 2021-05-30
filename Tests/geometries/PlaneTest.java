package geometries;

import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    Plane P1 = new Plane(
                new Point3D(0,0,0),
                new Point3D(0,5,0),
                new Point3D(5,0,0)
        );
    Plane P2 = new Plane(
            new Point3D(5,5,5),
            new Point3D(0,5,5),
            new Point3D(5,0,5)
    );

    LinkedList<Point3D> L = new LinkedList<Point3D>();

    @Test
    void getNormal() {
        assertThrows(IllegalArgumentException.class, () -> { P1.getNormal(new Point3D(1,1,1));}, "getNormal() did return even though point is not in plane");
        assertDoesNotThrow(() -> { P1.getNormal(new Point3D(1,1,0));}, "getNormal() did not return even though point is in plane");
        assertEquals(new Vector(0,0,-1), P1.getNormal(new Point3D(1, 1, 0)));


    }

    @Test
    void testGetNormal() {
        Vector v = P1.getNormal();
        assertEquals(1.0,v.length(), 0.0001, "Error: length != 1");
    }

    @Test
    void findIntersections() {
        //ray intersects plane
        L.clear();
        L.add(new Point3D(0,5,5));
        assertEquals(L, P2.findIntersections(new Ray(new Point3D(0,-1,-1), new Vector(0,1,1))), "ERROR: findIntersections() Does not work well.");

        //ray parallel to plane in plane
        L.clear();
        assertEquals(null, P2.findIntersections(new Ray(new Point3D(5,5,5), new Vector(0,1,0))),"ERROR: findIntersections() Does not work well.");

        //ray parallel to plane in outside
        L.clear();
        assertEquals(null, P2.findIntersections(new Ray(new Point3D(5,5,4), new Vector(0,1,0))),"ERROR: findIntersections() Does not work well.");

        //ray is orthogonal to the plane
        L.clear();
        L.add(new Point3D(1,1,5));
        assertEquals(L, P2.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,0,1))),"ERROR: findIntersections() Does not work well.");

        //ray starts on plane to out
        L.clear();
        assertEquals(null, P2.findIntersections(new Ray(new Point3D(5,4,5), new Vector(0,0,1))),"ERROR: findIntersections() Does not work well.");

        //ray q0 = plane q0
        L.clear();
        assertEquals(null, P2.findIntersections(new Ray(new Point3D(5,5,5), new Vector(0,1,1))), "ERROR: findIntersections() Does not work well.");

        //getNormal
    }
}