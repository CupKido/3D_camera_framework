package primitives;

import geometries.Intersectable.GeoPoint;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    LinkedList<Point3D> L = new LinkedList<Point3D>();
    LinkedList<GeoPoint> GL = new LinkedList<GeoPoint>();
    Ray R1;
    @Test
    void findClosestPoint() {
        //Test 1
        //Closest Point in middle of list
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        L.clear();
        L.add(new Point3D(5,0,1));
        L.add(new Point3D(2,0,1));
        L.add(new Point3D(7,0,1));
        assertEquals(new Point3D(2,0,1), R1.findClosestPoint(L), "ERROR: findClosestPoint() does not work well.");

        //Test 2
        //Empty List
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        L.clear();
        assertNull(R1.findClosestPoint(L), "ERROR: findClosestPoint() does not work well.");

        //Test 3
        //Closest Point is 1st in list
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        L.clear();
        L.add(new Point3D(2,0,1));
        L.add(new Point3D(5,0,1));
        L.add(new Point3D(7,0,1));
        assertEquals(new Point3D(2,0,1), R1.findClosestPoint(L), "ERROR: findClosestPoint() does not work well.");


        //Test 4
        //Closest Point is last in list
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        L.clear();
        L.add(new Point3D(5,0,1));
        L.add(new Point3D(7,0,1));
        L.add(new Point3D(2,0,1));
        assertEquals(new Point3D(2,0,1), R1.findClosestPoint(L), "ERROR: findClosestPoint() does not work well.");

    }

    @Test
    void findClosestGeoPoint() {

        //Test 1
        //Closest Point in middle of list
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        GL.clear();
        GL.add(new GeoPoint(null, new Point3D(5,0,1)));
        GL.add(new GeoPoint(null, new Point3D(2,0,1)));
        GL.add(new GeoPoint(null, new Point3D(7,0,1)));
        assertEquals(new GeoPoint(null, new Point3D(2,0,1)), R1.findClosestGeoPoint(GL), "ERROR: findClosestGeoPoint() does not work well.");

        //Test 2
        //Empty List
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        GL.clear();
        assertNull(R1.findClosestGeoPoint(GL), "ERROR: findClosestPoint() does not work well.");

        //Test 3
        //Closest Point is 1st in list
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        GL.clear();
        GL.add(new GeoPoint(null, new Point3D(2,0,1)));
        GL.add(new GeoPoint(null, new Point3D(5,0,1)));
        GL.add(new GeoPoint(null, new Point3D(7,0,1)));
        assertEquals(new GeoPoint(null, new Point3D(2,0,1)), R1.findClosestGeoPoint(GL), "ERROR: findClosestPoint() does not work well.");


        //Test 4
        //Closest Point is last in list
        R1 = new Ray(new Point3D(1,0,1), new Vector(1,0,0));
        GL.clear();
        GL.add(new GeoPoint(null, new Point3D(5,0,1)));
        GL.add(new GeoPoint(null, new Point3D(7,0,1)));
        GL.add(new GeoPoint(null, new Point3D(2,0,1)));
        assertEquals(new GeoPoint(null, new Point3D(2,0,1)), R1.findClosestGeoPoint(GL), "ERROR: findClosestPoint() does not work well.");

    }
}