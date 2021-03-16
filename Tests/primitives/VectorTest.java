package primitives;

import org.junit.jupiter.api.Test;

/**
 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
 */


class VectorTest {

    // ============ Equivalence Partitions Tests ==============
    Vector v1 = new Vector(1,1,0);
    Vector v2 = new Vector(1,0,0);
    Vector v3 = new Vector(0,1,0);

    // tests get head
    @Test
    void getHead() {
        System.out.println(v1.getHead());
    }

    // tests add
    @Test
    void add() {
        System.out.println(v1.add(v2));
    }

    // tests subtract
    @Test
    void subtract() {
        System.out.println(v1.subtract(v2));
    }

    // tests scale
    @Test
    void scale() {

        System.out.println(v1.scale(3));
    }

    // tests Dot-Product
    @Test
    void dotProduct() {

        System.out.println(v1.dotProduct(v2));
        // =============== Boundary Values Test ==================
        // test unaligned vectors dot product equals to 0
        System.out.println(v1.dotProduct(v3));
    }

    // tests Cross Product
    @Test
    void crossProduct() {

    }

    @Test
    void lengthSquared() {
    }

    // tests length
    @Test
    void length() {
    }

    @Test
    void normalized() {
    }
}