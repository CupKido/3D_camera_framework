package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    Ray R1 = new Ray(new Point3D(0,0,0), new Vector(0, 1, 0));
    Tube T0 = new Tube(R1, 3);

    @Test
    void getNormal() {
        Vector V0 = new Vector(0,0, 1);
        assertEquals(V0, T0.getNormal(new Point3D(0, 7, 3)), "Tube's getNormal() is not working properly");
        assertEquals(T0.getAxisRay().getDir().scale(-1), T0.getNormal(new Point3D(0,0,1)), "First Base Does not work in getNormal()");
    }

    @Test
    void findIntersections() {

    }
}