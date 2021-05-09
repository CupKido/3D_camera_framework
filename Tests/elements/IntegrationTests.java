package elements;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {
    Geometries G = new Geometries(new Plane(new Point3D(1,2,1), new Point3D(2,2,2), new Point3D(4,2,8)));
    Vector To = new Vector(0,1,0);
    Vector Up = new Vector(0,0,1);
    Point3D P = new Point3D(0,2,0);
    Camera C = new Camera(P,To,Up);

    @Test
    void Camera()
    {
        assertEquals(new Vector(1,0,0), new Camera(P,To,Up).Right, "Vector Crossing Error");
    }

    @Test
    void integrationTests() {
        //1st test
        //Case r=1
        G = new Geometries();
        G.add(new Sphere(new Point3D(0,0,-3),1));
        C = new Camera(new Point3D(0,0,0), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(2, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //2nd test
        //Case r=2
        G = new Geometries();
        G.add(new Sphere(new Point3D(0,0,-2.5),2.5));
        C = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(18, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //3rd test
        //Case r=2
        G = new Geometries();
        G.add(new Sphere(new Point3D(0,0,-2),2));
        C = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(10, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //4th Test
        //Case r=4
        G = new Geometries();
        G.add(new Sphere(new Point3D(0,0,-0.5),4));
        C = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(9, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //5th Test
        //Case r=0.5
        G = new Geometries();
        G.add(new Sphere(new Point3D(0,0,1),0.5));
        C = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(0, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //6th Test
        //Case intersection with Plane
        G = new Geometries();
        G.add(new Plane(new Point3D(0,0,-1),new Vector(0,0,1)));
        C = new Camera(new Point3D(0,0,1), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(9, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //7th Test
        //Case Different intersection with plane
        G = new Geometries();
        G.add(new Plane(new Point3D(0,3,7),new Vector(0,7,1)));
        C = new Camera(new Point3D(0,2,3), new Vector(0,1,0), new Vector(0,0,1));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(9, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //8th Test
        //Case Different intersection with plane only up and mid
        G = new Geometries();
        G.add(new Plane(new Point3D(0,20,7),new Vector(0,1,1)));
        C = new Camera(new Point3D(0,0,3), new Vector(0,1,0), new Vector(0,0,1));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(6, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //9th Test
        //Case Different intersection with triangle only mid mid
        G = new Geometries();
        G.add(new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2), new Point3D(-1,-1,-2)));
        C = new Camera(new Point3D(0,0,1), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(1, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");

        //10th Test
        //Case Different intersection with triangle only mid of mid and up
        G = new Geometries();
        G.add(new Triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2), new Point3D(-1,-1,-2)));
        C = new Camera(new Point3D(0,0,10), new Vector(0,0,-1), new Vector(0,1,0));
        C.setDistance(1);
        C.setViewPlaneSize(3,3);
        assertEquals(2, numOfInsects(3,3, G, C), "ERROR: constructRayThroughPixel() is not working well probably.");


    }

    public int numOfInsects(int nx, int ny, Geometries g, Camera c) {
        int res = 0;
        int nX = nx;
        int nY = ny;
        LinkedList<Point3D> temp = new LinkedList<Point3D>();
        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; ++j) {
                temp = G.findIntersections(C.constructRayThroughPixel(nX, nY, i -1, j -1));
                if (temp != null)
                    res += temp.size();
            }
    return res;
    }
}

