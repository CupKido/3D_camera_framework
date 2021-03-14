package targil1.geometries;

import org.junit.jupiter.api.Test;
import targil1.primitives.Point3D;

import static org.junit.jupiter.api.Assertions.*;

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