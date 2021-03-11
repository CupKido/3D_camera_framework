package targil1.Tests;

import org.junit.jupiter.api.Test;
import targil1.geometries.Plane;
import targil1.primitives.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    Plane p0 = new Plane(new Point3D(3,1,0), new Point3D(1,2,0), new Point3D(1,4,0) );

    @Test
    void getNormal() {
        System.out.println(p0.getNormal(Point3D.ZERO));
    }

    @Test
    void testGetNormal() {
    }
}