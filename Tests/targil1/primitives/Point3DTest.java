package targil1.primitives;

import org.junit.jupiter.api.Test;
import targil1.primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void add() {
        Point3D p0 = new Point3D(0,0,0);
        Vector v0 = new Vector(7,9,4);
        Point3D p1 = new Point3D(7,9,4);
        System.out.println(p0.add(v0).toString());
    }

    @Test
    void subtract() {
        Point3D p0 = new Point3D(0,0,0);
        Vector v0 = new Vector(7,9,4);
        Point3D p1 = new Point3D(7,9,4);
        System.out.println(p0.subtract(p1).toString());
    }

    @Test
    void distanceSquared() {
        Point3D p0 = new Point3D(0,0,0);
        Vector v0 = new Vector(7,9,4);
        Point3D p1 = new Point3D(7,9,4);
        System.out.println(p0.distanceSquared(p1));

    }

    @Test
    void distance() {
        Point3D p0 = new Point3D(0,0,0);
        Vector v0 = new Vector(7,9,4);
        Point3D p1 = new Point3D(7,9,4);
        System.out.println(p0.distance(p1));
    }

}