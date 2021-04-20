package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
 */



class VectorTest {

    // ============ Equivalence Partitions Tests ==============
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    @Test
    void Vector()
    {

        assertThrows( Exception.class , () -> {new Vector(0,0,0); }, "ERROR: zero vector does not throw an exception");

    }
    // tests get head
    @Test
    void getHead() {

    }

    // tests add
    @Test
    void add() {

    }

    // tests subtract
    @Test
    void subtract() {

    }

    // tests scale
    @Test
    void scale() {

    }

    // tests Dot-Product
    @Test
    void dotProduct() {

        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");


    }

    // tests Cross Product
    @Test
    void crossProduct() {
        assertThrows(Exception.class, () -> {v1.crossProduct(v2);} , "ERROR: crossProduct() for parallel vectors does not throw an exception");

        // test Cross-Product
        Vector vr = v1.crossProduct(v3);
        assertTrue(isZero(vr.length() - v1.length() * v3.length()), "ERROR: crossProduct() wrong result length");
        assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)), "ERROR: crossProduct() result is not orthogonal to its operands");
    }

    @Test
    void lengthSquared() {
    }

    // tests length
    @Test
    void length() {


        // test length..assertTrue();
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }

    @Test
    void normalized() {
        // test vector normalization vs vector length and cross-product
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals(vCopyNormalize, vCopy, "ERROR: normalize() function creates a new vector");
        assertTrue(isZero(vCopyNormalize.length() - 1), "ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        assertNotEquals(u, v, "ERROR: normalizated() function does not create a new vector");
    }
}
