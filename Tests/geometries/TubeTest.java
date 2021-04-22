package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TubeTest {

    Ray R1 = new Ray(new Point3D(0,0,0), new Vector(0, 1, 0));
    Tube T0 = new Tube(R1, 3);
    Tube T1 = new Tube(new Ray(new Point3D(0,0,0), new Vector(0,0,1)), 5);

    @Test
    void getNormal() {
        Vector V0 = new Vector(0,0, 1);
        assertEquals(V0, T0.getNormal(new Point3D(0, 7, 3)), "Tube's getNormal() is not working properly");
        assertEquals(T0.getAxisRay().getDir().scale(-1), T0.getNormal(new Point3D(0,0,1)), "First Base Does not work in getNormal()");
    }

    @Test
    void findIntersections() {
        // point in the tube and stay in
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0, 0, 1))), "ERROR: findIntersections() is not work as it should.");
        // point in the tube and gos out
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0, 0, 1))), "ERROR: findIntersections() is not work as it should.");
        // point on the tube and gos out
        assertEquals(Arrays.asList(new Point3D(0, 5,5)), T1.findIntersections(new Ray(new Point3D(0,1,5), new Vector(0, 1, 0))), "ERROR: findIntersections() is not work as it should.");
        // point on the tube and gos in
        assertEquals(Arrays.asList(new Point3D(0, 5,5)), T1.findIntersections(new Ray(new Point3D(0,-5,5), new Vector(0, 1, 0))), "ERROR: findIntersections() is not work as it should.");
        // point on the tube and stay on
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        // point out of the tube and does not touch it
        assertEquals(null, T1.findIntersections(new Ray(new Point3D(-6, 10,5), new Vector(1,0,0))), "ERROR: findIntersections() is not work as it should.");
        // point out of the tube touches it
        assertEquals(Arrays.asList(new Point3D(0, 5,5)), T1.findIntersections(new Ray(new Point3D(-6, 5,5), new Vector(1,0,0))), "ERROR: findIntersections() is not work as it should.");
        // point out of the tube and gos inside it
        assertEquals(Arrays.asList(new Point3D(-5, 1, 5), new Point3D(5, 1,5)), T1.findIntersections(new Ray(new Point3D(-6, 1,5), new Vector(1,0,0))), "ERROR: findIntersections() is not work as it should.");
    }
}