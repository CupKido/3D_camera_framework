package primitives;



import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void add() {

    }

    @Test
    void subtract() {
        // Test operations with points and vectors
        Point3D p1 = new Point3D(1, 2, 3);
        assertTrue(Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))),"ERROR: Point + Vector does not work correctly" );
        assertTrue(new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)),"ERROR: Point - Point does not work correctly");
    }

    @Test
    void distanceSquared() {


    }

    @Test
    void distance() {
        Point3D p1 = new Point3D(0,0,0);
        Point3D p2 = new Point3D(0,0,9);
        assertEquals(9, p1.distance(p2), "ERROR: distance() for points doesnt work well.");
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testSubtract() {
    }

    @Test
    public void testDistanceSquared() {
    }

    @Test
    public void testDistance() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
    }
}