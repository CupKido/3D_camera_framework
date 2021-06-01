/**
 *
 */
package elements;

import geometries.Cube;
import geometries.Plane;
import geometries.Sphere;
import geometries.Square;
import org.junit.Test;
import java.awt.Color.*;
import java.util.Random;

import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class AllElementsTests {
    private Scene scene = new Scene("Test scene");
    Color Pink = new Color(255, 26, 198);
    Color Green = new Color(java.awt.Color.GREEN);
    Color Blue = new Color(0, 102, 255);
    Color Yellow = new Color(255, 153, 0);

    @Test
    public void PoolTable(){
        Color Wood = new Color(153, 102, 51);
        Color Matt = new Color(6,97,6);
        Color Lamp = new Color(179, 179, 204);

        Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(5,5,5), 0.15));
        Camera camera = new Camera(new Point3D(10,4,3), new Vector(-10,-2,-1), new Vector(-0.5,0,5))
                .setViewPlaneSize(32, 18).setDistance(18);
        Camera camera2 = new Camera(new Point3D(0,10,1.5), new Vector(0,-1,0), new Vector(0,0,1))
                .setViewPlaneSize(16, 9).setDistance(5);
        scene.geometries.add( //
                //floor
                new Plane(new Point3D(0,0,-1.5), new Point3D(0,1,-1.5), new Point3D(1,0,-1.5))
                        .setEmission(new Color(150, 150, 150)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.5)),

                //Mirror
                new Square(new Point3D(-4,-3,-1.5), new Point3D(-4,-3,1), new Point3D(4,-3,-1.5), new Point3D(4,-3,1))
                        .setEmission(new Color(20, 20, 20)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)),
                //Green mat
                new Square(new Point3D(3,-1.5,0), new Point3D(3, 1.5, 0), new Point3D(-3, -1.5, 0),new Point3D(-3, 1.5, 0))
                .setEmission(Matt).setMaterial(new Material().setKd(0.3).setKs(0.2).setShininess(30)),
                //Wood front
                new Square(new Point3D(3.2,-1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(3.2,-1.7,-1.25), new Point3D(3.2, 1.7, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3,-1.5,0.2), new Point3D(3, 1.5, 0.2),new Point3D(3,-1.5,-1.25), new Point3D(3, 1.5, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3,-1.7,0.2), new Point3D(3, 1.7, 0.2),new Point3D(3.2,-1.7,0.2), new Point3D(3.2, 1.7, 0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                //wood back
                new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(-3.2, 1.7, 0.2),new Point3D(-3.2,-1.7,-1.25), new Point3D(-3.2, 1.7, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3,-1.5,0.2), new Point3D(-3, 1.5, 0.2),new Point3D(-3,-1.5,-1.25), new Point3D(-3, 1.5, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3,-1.7,0.2), new Point3D(-3, 1.7, 0.2),new Point3D(-3.2,-1.7,-1.25), new Point3D(-3.2, 1.7, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                //Wood left
                new Square(new Point3D(-3,-1.5,0.2), new Point3D(3, -1.5, 0.2),new Point3D(-3,-1.5,-1.25), new Point3D(3, -1.5, -1.25))
                    .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(3.2, -1.7, 0.2),new Point3D(-3.2,-1.7,-1.25), new Point3D(3.2, -1.7, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(3.2, -1.7, 0.2),new Point3D(-3.2,-1.5,0.2), new Point3D(3.2, -1.5, 0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //Wood right
                new Square(new Point3D(-3,1.5,0.2), new Point3D(3, 1.5, 0.2),new Point3D(-3,1.5,-1.25), new Point3D(3, 1.5, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(-3.2,1.7,-1.25), new Point3D(3.2, 1.7, -1.25))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(-3.2,1.5,0.2), new Point3D(3.2, 1.5, 0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                //balls
                new Sphere(new Point3D(1.2, 0, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(1.2, 0.2, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(1.2, 0.4, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(1.2, -0.2, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(1.2, -0.4, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),

                new Sphere(new Point3D(1, -0.3, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(1, -0.1, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(1, 0.3, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(1, 0.1, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),

                new Sphere(new Point3D(0.8, 0, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(0.8, -0.2, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(0.8, 0.2, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),

                new Sphere(new Point3D(0.6, 0.1, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),
                new Sphere(new Point3D(0.6, -0.1, 0.1), 0.1)
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),

                new Sphere(new Point3D(0.4, 0, 0.1), 0.1)
                        .setEmission(new Color(Color.BLACK)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),

                new Sphere(new Point3D(-1.5, 0, 0.1), 0.1)
                        .setEmission(new Color(190,190,190)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),

                //balls holder
                new Square(new Point3D(0.2,0,0), new Point3D(1.3,0.65,0),new Point3D(0.2,0,0.14), new Point3D(1.3,0.65,0.14)),
                new Square(new Point3D(0.2,0,0), new Point3D(1.3,-0.65,0),new Point3D(0.2,0,0.14), new Point3D(1.3,-0.65,0.14)),
                new Square(new Point3D(1.3,-0.65,0), new Point3D(1.3,0.65,0),new Point3D(1.3,-0.65,0.14), new Point3D(1.3,0.65,0.14)),

                //Lamp1:
                new Square(new Point3D(1.75, 0.25, 1),new Point3D(1.75, -0.25, 1),new Point3D(1.75, 0.25, 1.5),new Point3D(1.75, -0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(1.25, 0.25, 1),new Point3D(1.75, 0.25, 1),new Point3D(1.25, 0.25, 1.5),new Point3D(1.75, 0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(1.25, -0.25, 1),new Point3D(1.75, -0.25, 1),new Point3D(1.25, -0.25, 1.5),new Point3D(1.75, -0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(1.25, 0.25, 1),new Point3D(1.25, -0.25, 1),new Point3D(1.25, 0.25, 1.5),new Point3D(1.25, -0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(1.75, 0.25, 1.5),new Point3D(1.75, -0.25, 1.5),new Point3D(1.25, -0.25, 1.5),new Point3D(1.25, 0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(1.75, 0.25, 1),new Point3D(1.75, -0.25, 1),new Point3D(1.25, -0.25, 1),new Point3D(1.25, 0.25, 1))
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(4).setKt(0.9)),
//
                //Lamp2:
                new Square(new Point3D(-1.75, 0.25, 1),new Point3D(-1.75, -0.25, 1),new Point3D(-1.75, 0.25, 1.5),new Point3D(-1.75, -0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(-1.25, 0.25, 1),new Point3D(-1.75, 0.25, 1),new Point3D(-1.25, 0.25, 1.5),new Point3D(-1.75, 0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(-1.25, -0.25, 1),new Point3D(-1.75, -0.25, 1),new Point3D(-1.25, -0.25, 1.5),new Point3D(-1.75, -0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(-1.25, 0.25, 1),new Point3D(-1.25, -0.25, 1),new Point3D(-1.25, 0.25, 1.5),new Point3D(-1.25, -0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
                new Square(new Point3D(-1.75, 0.25, 1.5),new Point3D(-1.75, -0.25, 1.5),new Point3D(-1.25, -0.25, 1.5),new Point3D(-1.25, 0.25, 1.5))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),

                //holes
                new Sphere(new Point3D(3,1.5,0),0.15)
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0).setKs(0).setShininess(0)),
                new Sphere(new Point3D(3,-1.5,0),0.15)
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0).setKs(0).setShininess(0)),
                new Sphere(new Point3D(-3,1.5,0),0.15)
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0).setKs(0).setShininess(0)),
                new Sphere(new Point3D(-3,-1.5,0),0.15)
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0).setKs(0).setShininess(0)),
                new Sphere(new Point3D(0,1.5,0),0.15)
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0).setKs(0).setShininess(0)),
                new Sphere(new Point3D(0,-1.5,0),0.15)
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0).setKs(0).setShininess(0)),

                new Cube(new Point3D(-1, 3, 3), new Point3D(0, 3, 3), new Point3D(-1, 2, 3), new Point3D(0, 2, 3), new Point3D(-1, 3, 1), new Point3D(0, 3, 1), new Point3D(-1, 2, 1), new Point3D(0, 2, 1))
                        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70))
                );

        scene.lights.add( //
                new PointLight(new Color(java.awt.Color.WHITE),new Point3D(1.5,0,1.1),1, 0.000005, 0.000005)
        );
        scene.lights.add( //
                new PointLight(new Color(java.awt.Color.WHITE),new Point3D(-1.5,0,1.1),1, 0.000005, 0.000005)
        );
        scene.lights.add( //
                new PointLight(new Color(java.awt.Color.WHITE),new Point3D(20,10,35),1, 0.0005, 0.001)
        );
        Render render1 = new Render(). //
                setImageWriter(new ImageWriter("PoolTableC1", 1280, 720)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render1.renderImage();
        render1.writeToImage();

        Render render2 = new Render(). //
                setImageWriter(new ImageWriter("PoolTableC2", 1280, 720)) //
                .setCamera(camera2) //
                .setRayTracer(new RayTracerBasic(scene));
        render2.renderImage();
        render2.writeToImage();
    }

    private Color RandColor(){
        Random a = new Random();

        switch (a.nextInt(5)){
            case 1:
                return Pink;

            case 2:
                return Blue;

            case 3:
                return Green;

            case 4:
                return Yellow;

            default:
                return Color.BLACK;
        }


    }
}
    /**
     * Produce a picture of a sphere lighted by a spot light
     */


    /**
     * Produce a picture of a sphere lighted by a spot light
     */


    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially
     * transparent Sphere producing partial shadow
     */


