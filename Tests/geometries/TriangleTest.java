package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

/**
 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
 */


class TriangleTest {

    @Test
    void getNormal() {
        Triangle Tri = new Triangle(
                new Point3D(0,0,0),
                new Point3D(0,5,0),
                new Point3D(5,0,0)
        );
        System.out.println(Tri.getNormal(new Point3D(1,1,0)));
        try{
            System.out.println(Tri.getNormal(new Point3D(1,1,1)));
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }


    }
}