package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {
    Ray R1 = new Ray(new Point3D(0,0,0), new Vector(0, 1, 0));
    Cylinder C0 = new Cylinder(R1, 3, 8);

    @Test
    void getNormal() {
        assertThrows(Exception.class, () -> {C0.getNormal(new Point3D(0,5,0));}, "ERROR: vector 0 was Created"); //in case of inside vector
        assertEquals(C0.getAxisRay().getDir().scale(-1), C0.getNormal(new Point3D(0,0,0)), "ERROR: Wrong Vector was returned"); //in case of circle 1
        assertEquals(C0.getAxisRay().getDir(), C0.getNormal(new Point3D(1,8,0.5)), "ERROR: Wrong Vector was returned"); //in case of circle 2

        assertEquals(new Vector(1,0,0), C0.getNormal(new Point3D(1,1,0)), "Wrong Value returned for getNormal for cylinder"); //check of get normal


        assertEquals(C0.getAxisRay().getDir(), C0.getNormal(new Point3D(0,8,1)), "Second Base Does not work in getNormal()");


    }
}