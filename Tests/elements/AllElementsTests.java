/**
 *
 */
package elements;

import geometries.*;
import items.BallsHolder;
import items.*;
import org.junit.jupiter.api.Test;
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

    @Test
    public void PoolTable(){
        //Color Wood = new Color(153, 102, 51);
        Color Wood = new Color(110, 80, 15);
        Color Matt = new Color(6,97,6);
        Color Lamp = new Color(179, 179, 204);

        Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(5,5,5), 0.15));
        Camera camera = new Camera(new Point3D(20,3.5,2.5), new Vector(-10,-2,-1), new Vector(-0.5,0,5))
                .setViewPlaneSize(16, 9).setDistance(35);
        Camera camera2 = new Camera(new Point3D(0,25,1.5), new Vector(0,-1,0), new Vector(0,0,1))
                .setViewPlaneSize(16, 9).setDistance(20);
        Camera camera22 = new Camera(new Point3D( 0, 0, 4), new Vector(0,0,-1), new Vector(0,1,0))
                .setViewPlaneSize(2,2).setDistance(1);
        scene.geometries.add( //

                //floor
                new Plane(new Point3D(0,0,-1.5), new Point3D(0,1,-1.5), new Point3D(1,0,-1.5))
                        .setEmission(new Color(150, 150, 150)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0)),

                //Mirror
//                new Square(new Point3D(-4,-4,-1.5), new Point3D(-4,-4,2), new Point3D(4,-4,-1.5), new Point3D(4,-4,2))
//                        .setEmission(new Color(20, 20, 20)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)),

                //Green mat
                new Square(new Point3D(3,-1.5,0), new Point3D(3, 1.5, 0), new Point3D(-3, -1.5, 0),new Point3D(-3, 1.5, 0))
                        .setEmission(Matt).setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(30)),

                //Wood front
                //new Square(new Point3D(3.2,-1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(3.2,-1.7,-1.25), new Point3D(3.2, 1.7, -1.25))
                new Square(new Point3D(3.2,-1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(3.2,-1.7,-0.2), new Point3D(3.2, 1.7, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3,-1.5,0.2), new Point3D(3, 1.5, 0.2),new Point3D(3,-1.5,-0.2), new Point3D(3, 1.5, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3,-1.7,0.2), new Point3D(3, 1.7, 0.2),new Point3D(3.2,-1.7,0.2), new Point3D(3.2, 1.7, 0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3,-1.7,-0.2), new Point3D(3, 1.7, -0.2),new Point3D(3.2,-1.7,-0.2), new Point3D(3.2, 1.7, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //wood back
                new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(-3.2, 1.7, 0.2),new Point3D(-3.2,-1.7,-0.2), new Point3D(-3.2, 1.7, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3,-1.5,0.2), new Point3D(-3, 1.5, 0.2),new Point3D(-3,-1.5,-0.2), new Point3D(-3, 1.5, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3,-1.7,0.2), new Point3D(-3, 1.7, 0.2),new Point3D(-3.2,-1.7,0.2), new Point3D(-3.2, 1.7, 0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3,-1.7,-0.2), new Point3D(-3, 1.7, -0.2),new Point3D(-3.2,-1.7,-0.2), new Point3D(-3.2, 1.7, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //Wood left
                new Square(new Point3D(-3,-1.5,0.2), new Point3D(3, -1.5, 0.2),new Point3D(-3,-1.5,-0.2), new Point3D(3, -1.5, -0.2))
                    .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(3.2, -1.7, 0.2),new Point3D(-3.2,-1.7,-0.2), new Point3D(3.2, -1.7, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(3.2, -1.7, 0.2),new Point3D(-3.2,-1.5,0.2), new Point3D(3.2, -1.5, 0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,-1.7,-0.2), new Point3D(3.2, -1.7, -0.2),new Point3D(-3.2,-1.5,-0.2), new Point3D(3.2, -1.5, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //Wood right
                new Square(new Point3D(-3,1.5,0.2), new Point3D(3, 1.5, 0.2),new Point3D(-3,1.5,-0.2), new Point3D(3, 1.5, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(-3.2,1.7,-0.2), new Point3D(3.2, 1.7, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(-3.2,1.5,0.2), new Point3D(3.2, 1.5, 0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,1.7,-0.2), new Point3D(3.2, 1.7, -0.2),new Point3D(-3.2,1.5,-0.2), new Point3D(3.2, 1.5, -0.2))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg front right
                /*new Square(new Point3D(3.2,1.7,0.2), new Point3D(3.2, 1.5, 0.2),new Point3D(3.2,1.7,-1.5), new Point3D(3.2, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3,1.7,0.2), new Point3D(3, 1.5, 0.2),new Point3D(3,1.7,-1.5), new Point3D(3, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3.2,1.7,0.2), new Point3D(3, 1.7, 0.2),new Point3D(3.2,1.7,-1.5), new Point3D(3, 1.7, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3.2,1.5,0.2), new Point3D(3, 1.5, 0.2),new Point3D(3.2,1.5,-1.5), new Point3D(3, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),*/
                new Cube(new Point3D(3.1, 1.6, -0.65), 0.2, 0.2, 1.7)
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg front left
                /*new Square(new Point3D(3.2,-1.7,0.2), new Point3D(3.2, -1.5, 0.2),new Point3D(3.2,-1.7,-1.5), new Point3D(3.2, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3,-1.7,0.2), new Point3D(3, -1.5, 0.2),new Point3D(3,-1.7,-1.5), new Point3D(3, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3.2,-1.7,0.2), new Point3D(3, -1.7, 0.2),new Point3D(3.2,-1.7,-1.5), new Point3D(3, -1.7, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(3.2,-1.5,0.2), new Point3D(3, -1.5, 0.2),new Point3D(3.2,-1.5,-1.5), new Point3D(3, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),*/
                new Cube(new Point3D(3.1, -1.6, -0.65), 0.2, 0.2, 1.7)
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg middle right
                /*new Square(new Point3D(0.1,1.7,0.2), new Point3D(0.1, 1.5, 0.2),new Point3D(0.1,1.7,-1.5), new Point3D(0.1, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-0.1,1.7,0.2), new Point3D(-0.1, 1.5, 0.2),new Point3D(-0.1,1.7,-1.5), new Point3D(-0.1, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(0.1,1.7,0.2), new Point3D(-0.1, 1.7, 0.2),new Point3D(0.1,1.7,-1.5), new Point3D(-0.1, 1.7, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(0.1,1.5,0.2), new Point3D(-0.1, 1.5, 0.2),new Point3D(0.1,1.5,-1.5), new Point3D(-0.1, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),*/
                new Cube(new Point3D(0, 1.6, -0.65), 0.2, 0.2, 1.7)
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg middle left
                /*new Square(new Point3D(0.1,-1.7,0.2), new Point3D(0.1, -1.5, 0.2),new Point3D(0.1,-1.7,-1.5), new Point3D(0.1, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-0.1,-1.7,0.2), new Point3D(-0.1, -1.5, 0.2),new Point3D(-0.1,-1.7,-1.5), new Point3D(-0.1, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(0.1,-1.7,0.2), new Point3D(-0.1, -1.7, 0.2),new Point3D(0.1,-1.7,-1.5), new Point3D(-0.1, -1.7, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(0.1,-1.5,0.2), new Point3D(-0.1, -1.5, 0.2),new Point3D(0.1,-1.5,-1.5), new Point3D(-0.1, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),*/
                new Cube(new Point3D(0, -1.6, -0.65), 0.2, 0.2, 1.7)
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg back right
                /*new Square(new Point3D(-3.2,1.7,0.2), new Point3D(-3.2, 1.5, 0.2),new Point3D(-3.2,1.7,-1.5), new Point3D(-3.2, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3,1.7,0.2), new Point3D(-3, 1.5, 0.2),new Point3D(-3,1.7,-1.5), new Point3D(-3, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,1.7,0.2), new Point3D(-3, 1.7, 0.2),new Point3D(-3.2,1.7,-1.5), new Point3D(-3, 1.7, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,1.5,0.2), new Point3D(-3, 1.5, 0.2),new Point3D(-3.2,1.5,-1.5), new Point3D(-3, 1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),*/
                new Cube(new Point3D(-3.1, 1.6, -0.65), 0.2, 0.2, 1.7)
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg back left
                /*new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(-3.2, -1.5, 0.2),new Point3D(-3.2,-1.7,-1.5), new Point3D(-3.2, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3,-1.7,0.2), new Point3D(-3, -1.5, 0.2),new Point3D(-3,-1.7,-1.5), new Point3D(-3, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(-3, -1.7, 0.2),new Point3D(-3.2,-1.7,-1.5), new Point3D(-3, -1.7, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
                new Square(new Point3D(-3.2,-1.5,0.2), new Point3D(-3, -1.5, 0.2),new Point3D(-3.2,-1.5,-1.5), new Point3D(-3, -1.5, -1.5))
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),*/
                new Cube(new Point3D(-3.1, -1.6, -0.65), 0.2, 0.2, 1.7)
                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //bottom green mat
                new Square(new Point3D(3,-1.5,-0.17), new Point3D(3, 1.5, -0.17), new Point3D(-3, -1.5, -0.17),new Point3D(-3, 1.5, -0.17))
                        .setEmission(Matt).setMaterial(new Material().setKd(0.3).setKs(0.2).setShininess(30)),

                //balls
                new Sphere(new Point3D(-1.5, 0, 0.1), 0.1)
                        .setEmission(new Color(190,190,190)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),

                new PoolBalls(new Point3D(1.2, 0, 0)),

                //balls holder
                new BallsHolder(new Point3D(1.2,0,0)),

                //pool sticks
//                new PoolStick(new Point3D(-5,0,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,0.75,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,1.5,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,-0.75,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,-1.5,-1.5), new Vector(0,0,1)),

                //Lamp1:
//                new Square(new Point3D(1.75, 0.25, 1.1),new Point3D(1.75, -0.25, 1.1),new Point3D(1.75, 0.25, 1.6),new Point3D(1.75, -0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(1.25, 0.25, 1.1),new Point3D(1.75, 0.25, 1.1),new Point3D(1.25, 0.25, 1.6),new Point3D(1.75, 0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(1.25, -0.25, 1.1),new Point3D(1.75, -0.25, 1.1),new Point3D(1.25, -0.25, 1.6),new Point3D(1.75, -0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(1.25, 0.25, 1.1),new Point3D(1.25, -0.25, 1.1),new Point3D(1.25, 0.25, 1.6),new Point3D(1.25, -0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(1.75, 0.25, 1.6),new Point3D(1.75, -0.25, 1.6),new Point3D(1.25, -0.25, 1.6),new Point3D(1.25, 0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(1.75, 0.25, 1.1),new Point3D(1.75, -0.25, 1.1),new Point3D(1.25, -0.25, 1.1),new Point3D(1.25, 0.25, 1.1))
//                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(4).setKt(0.9)),
//                new Cylinder(new Ray(new Point3D(1.5, 0, 1.6001), new Vector(0,0, 1)), 0.02, 6)
//                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(4)),

                //Lamp2:
//                new Square(new Point3D(-1.75, 0.25, 1.1),new Point3D(-1.75, -0.25, 1.1),new Point3D(-1.75, 0.25, 1.6),new Point3D(-1.75, -0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(-1.25, 0.25, 1.1),new Point3D(-1.75, 0.25, 1.1),new Point3D(-1.25, 0.25, 1.6),new Point3D(-1.75, 0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(-1.25, -0.25, 1.1),new Point3D(-1.75, -0.25, 1.1),new Point3D(-1.25, -0.25, 1.6),new Point3D(-1.75, -0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(-1.25, 0.25, 1.1),new Point3D(-1.25, -0.25, 1.1),new Point3D(-1.25, 0.25, 1.6),new Point3D(-1.25, -0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Square(new Point3D(-1.75, 0.25, 1.6),new Point3D(-1.75, -0.25, 1.6),new Point3D(-1.25, -0.25, 1.6),new Point3D(-1.25, 0.25, 1.6))
//                        .setEmission(Lamp).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(60)),
//                new Cylinder(new Ray(new Point3D(-1.5, 0, 1.6001), new Vector(0,0, 1)), 0.02, 6)
//                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(4)),

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
                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0).setKs(0).setShininess(0))

//                new Beer(new Point3D(3.1,1.6,0.2), 0.7),
//                new Beer(new Point3D(-5,3.5,-1.5), 0.7)

                //new Cube(new Point3D(2,3,1), 2, 3, 3)
                //        .setEmission(RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.5)),
                //new Cube(new Point3D(2,2,2), 1,1,1, new Vector(1,1,1), new Vector(1,-1,0))
                 //       .setEmission(Color.RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70).setKt(0)),
                //new Cube(new Point3D(-1, 3, 3), new Point3D(-1, 2, 3), new Point3D(0, 3, 3), new Point3D(0, 2, 3), new Point3D(-1, 3, 1), new Point3D(-1, 2, 1), new Point3D(0, 3, 1), new Point3D(0, 2, 1))
                //new Cube(new Point3D(-0.5,2.5,2), 2, 1, 1)
                 //       .setEmission(Color.RandColor()).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70).setKt(0))
                );

        scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),new Point3D(1.5,0,1.25), new Vector(0,0, -1),2.5, 0.00001, 0.0005)
        );
        scene.lights.add( //
                new SpotLight(new Color(java.awt.Color.WHITE),new Point3D(-1.5,0,1.25),new Vector(0,0, -1),2.5, 0.00001, 0.0005)
        );
//        scene.lights.add( //
//                new PointLight(new Color(java.awt.Color.WHITE),new Point3D(20,10,35),1, 0.0005, 0.001)
//        );

//        Render render1 = new Render(). //
//                setImageWriter(new ImageWriter("PoolTableC12", 1280, 720 )) //
//                .setCamera(camera) //
//                .setRayTracer(new RayTracerBasic(scene));
//        render1.renderImage();
//        render1.writeToImage();

//        Render render2 = new Render(). //
//                setImageWriter(new ImageWriter("PoolTableC2", 1280, 720)) //
//                .setCamera(camera2) //
//                .setRayTracer(new RayTracerBasic(scene));
//        render2.renderImage();
//        render2.writeToImage();

        Render render22 = new Render(). //
                setImageWriter(new ImageWriter("PoolTableC24", 500, 500 )) //
                .setCamera(camera22) //
                .setRayTracer(new RayTracerBasic(scene));
        render22.renderImage();
        render22.writeToImage();
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


