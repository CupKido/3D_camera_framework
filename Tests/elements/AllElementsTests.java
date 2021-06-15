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
                .setViewPlaneSize(16, 9).setDistance(30);

        Camera camera2 = new Camera(new Point3D(0,8,2), new Vector(0,-1,0), new Vector(0,0,1))
                .setViewPlaneSize(16, 9).setDistance(10);
        Camera camera22 = new Camera(new Point3D( -2.5, 0, 0.1), new Vector(-1,0,0), new Vector(0,0,1))
                .setViewPlaneSize(2,2).setDistance(20);
        scene.geometries.add( //



                //floor
                new Plane(new Point3D(0,0,-1.5), new Point3D(0,1,-1.5), new Point3D(1,0,-1.5))
                        .setEmission(new Color(150, 150, 150)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0)),

                //Mirror
                new Square(new Point3D(-4,-4,-1.5), new Point3D(-4,-4,2), new Point3D(4,-4,2), new Point3D(4,-4,-1.5))
                        .setEmission(new Color(20, 20, 20)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKr(0.95)),



                //table Upper


                //Green mat
                new Square(new Point3D(3,-1.5,0), new Point3D(3, 1.5, 0), new Point3D(-3, 1.5, 0), new Point3D(-3, -1.5, 0))
                        .setEmission(Matt).setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(30)),

                //bottom green mat
                new Square(new Point3D(3,-1.5,-0.17), new Point3D(3, 1.5, -0.17), new Point3D(-3, 1.5, -0.17), new Point3D(-3, -1.5, -0.17))
                        .setEmission(Matt).setMaterial(new Material().setKd(0.3).setKs(0.2).setShininess(30)),


                //Wood front

                newTable(new Point3D(3,-1.71, -0.2), new Point3D(3.21, 1.71, 0.21), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(3.2,-1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(3.2,-1.7,-0.2), new Point3D(3.2, 1.7, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(3,-1.5,0.2), new Point3D(3, 1.5, 0.2),new Point3D(3,-1.5,-0.2), new Point3D(3, 1.5, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(3,-1.7,0.2), new Point3D(3, 1.7, 0.2),new Point3D(3.2,-1.7,0.2), new Point3D(3.2, 1.7, 0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(3,-1.7,-0.2), new Point3D(3, 1.7, -0.2),new Point3D(3.2,-1.7,-0.2), new Point3D(3.2, 1.7, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),


                //wood back
                    newTable(new Point3D(-3.21, -1.71, -0.2), new Point3D(-3, 1.71, 0.21), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(-3.2, 1.7, 0.2),new Point3D(-3.2,-1.7,-0.2), new Point3D(-3.2, 1.7, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3,-1.5,0.2), new Point3D(-3, 1.5, 0.2),new Point3D(-3,-1.5,-0.2), new Point3D(-3, 1.5, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3,-1.7,0.2), new Point3D(-3, 1.7, 0.2),new Point3D(-3.2,-1.7,0.2), new Point3D(-3.2, 1.7, 0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3,-1.7,-0.2), new Point3D(-3, 1.7, -0.2),new Point3D(-3.2,-1.7,-0.2), new Point3D(-3.2, 1.7, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),


                //Wood left
                newTable(new Point3D(-3.21, -1.71, -0.2), new Point3D(3.21, -1.5, 0.21), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3,-1.5,0.2), new Point3D(3, -1.5, 0.2),new Point3D(-3,-1.5,-0.2), new Point3D(3, -1.5, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(3.2, -1.7, 0.2),new Point3D(-3.2,-1.7,-0.2), new Point3D(3.2, -1.7, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3.2,-1.7,0.2), new Point3D(3.2, -1.7, 0.2),new Point3D(-3.2,-1.5,0.2), new Point3D(3.2, -1.5, 0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                        new Square(new Point3D(-3.2,-1.7,-0.2), new Point3D(3.2, -1.7, -0.2),new Point3D(-3.2,-1.5,-0.2), new Point3D(3.2, -1.5, -0.2))
//                                .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),


                //Wood right
                newTable(new Point3D(-3.21, 1.5, -0.2), new Point3D(3.21, 1.71, 0.21), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                new Square(new Point3D(-3,1.5,0.2), new Point3D(3, 1.5, 0.2),new Point3D(-3,1.5,-0.2), new Point3D(3, 1.5, -0.2))
//                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                new Square(new Point3D(-3.2,1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(-3.2,1.7,-0.2), new Point3D(3.2, 1.7, -0.2))
//                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                new Square(new Point3D(-3.2,1.7,0.2), new Point3D(3.2, 1.7, 0.2),new Point3D(-3.2,1.5,0.2), new Point3D(3.2, 1.5, 0.2))
//                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),
//                new Square(new Point3D(-3.2,1.7,-0.2), new Point3D(3.2, 1.7, -0.2),new Point3D(-3.2,1.5,-0.2), new Point3D(3.2, 1.5, -0.2))
//                        .setEmission(Wood).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)),


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

                        //balls
                new Sphere(new Point3D(-1.5, 0, 0.1), 0.1)
                    .setEmission(new Color(190,190,190)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(70)),



                new PoolBalls(new Point3D(1.2, 0, 0)),

//                //balls holder
                new BallsHolder(new Point3D(1.2,0,0)),




                //table lower


                //leg front right
                newTable(new Point3D(3, 1.5, -1.5), new Point3D(3.2, 1.7, 0.2), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg front left
                newTable(new Point3D(3, -1.7, -1.5), new Point3D(3.2, -1.5, 0.2), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg middle right
                newTable(new Point3D(-0.1, 1.5, -1.5), new Point3D(0.1, 1.7, 0.2), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),

                //leg middle left
                newTable(new Point3D(-0.1, -1.7, -1.5), new Point3D(0.1, -1.5, 0.2), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),


                //leg back right
                newTable(new Point3D(-3.2, 1.5, -1.5), new Point3D(-3, 1.7, 0.2), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),


                //leg back left
                newTable(new Point3D(-3.2, -1.7, -1.5), new Point3D(-3, -1.5, 0.2), Wood, new Material().setKd(0.5).setKs(0.5).setShininess(10)),







                //pool sticks
//                new PoolStick(new Point3D(-5,0,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,0.75,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,1.5,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,-0.75,-1.5), new Vector(0,0,1)),
//                new PoolStick(new Point3D(-5,-1.5,-1.5), new Vector(0,0,1)),

                //Lamp1:
                new Square(new Point3D(1.75, 0.25, 1.1),new Point3D(1.75, -0.25, 1.1),new Point3D(1.75, -0.25, 1.6),new Point3D(1.75, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(1.25, 0.25, 1.1),new Point3D(1.75, 0.25, 1.1), new Point3D(1.75, 0.25, 1.6),new Point3D(1.25, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(1.25, -0.25, 1.1),new Point3D(1.75, -0.25, 1.1),new Point3D(1.75, -0.25, 1.6), new Point3D(1.25, -0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(1.25, 0.25, 1.1),new Point3D(1.25, -0.25, 1.1),new Point3D(1.25, -0.25, 1.6),new Point3D(1.25, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(1.75, 0.25, 1.6),new Point3D(1.75, -0.25, 1.6),new Point3D(1.25, -0.25, 1.6),new Point3D(1.25, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
//                new Square(new Point3D(1.75, 0.25, 1.1),new Point3D(1.75, -0.25, 1.1),new Point3D(1.25, -0.25, 1.1),new Point3D(1.25, 0.25, 1.1))
//                        .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.7).setShininess(4).setKt(0.9)),

//
//                //Lamp2:

                new Square(new Point3D(-1.75, 0.25, 1.1),new Point3D(-1.75, -0.25, 1.1),new Point3D(-1.75, -0.25, 1.6),new Point3D(-1.75, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(-1.25, 0.25, 1.1),new Point3D(-1.75, 0.25, 1.1),new Point3D(-1.75, 0.25, 1.6),new Point3D(-1.25, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(-1.25, -0.25, 1.1),new Point3D(-1.75, -0.25, 1.1),new Point3D(-1.75, -0.25, 1.6),new Point3D(-1.25, -0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(-1.25, 0.25, 1.1),new Point3D(-1.25, -0.25, 1.1),new Point3D(-1.25, -0.25, 1.6),new Point3D(-1.25, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),
                new Square(new Point3D(-1.75, 0.25, 1.6),new Point3D(-1.75, -0.25, 1.6),new Point3D(-1.25, -0.25, 1.6),new Point3D(-1.25, 0.25, 1.6))
                        .setEmission(Lamp).setMaterial(new Material().setKd(0.6).setKs(0.8).setShininess(60)),






                        new Cylinder(new Ray(new Point3D(-1.5, 0, 1.6001), new Vector(0,0, 1)), 0.02, 6)
                                .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(4)),
                        new Cylinder(new Ray(new Point3D(1.5, 0, 1.6001), new Vector(0,0, 1)), 0.02, 6)
                                .setEmission(Color.BLACK).setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(4))






//                new Beer(new Point3D(3.1,1.6,0.2), 0.7)
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
                new SpotLight(new Color(255,117,24).add(new Color(java.awt.Color.WHITE).scale(0.5)),new Point3D(1.5,0,1.22), new Vector(0,0, -1),2.5, 0.00001, 0.0005)
        );
        scene.lights.add( //
                new SpotLight(new Color(255,117,24).add(new Color(java.awt.Color.WHITE).scale(0.5)),new Point3D(-1.5,0,1.22),new Vector(0,0, -1),2.5, 0.00001, 0.0005)
        );
        scene.lights.add( //
                new PointLight(new Color(java.awt.Color.WHITE),new Point3D(40,15,7),4, 0.0005, 0.0005)
        );

//        Render render1 = new Render(). //
//                setImageWriter(new ImageWriter("PoolTableR500", 1280, 720 )) //
//                .setCamera(camera) //
//                .setRayTracer(new RayTracerBasic(scene))
//                .setReject(true)
//                .setRAYS(1)
//                .setSHADOWRAYS(1)
//                .setMultithreading(3).setDebugPrint();
//        render1.renderImage();
//        render1.writeToImage();
//
        camera.MoveRight(2);

        camera2.TwistClockWise(10);
        Render render2 = new Render(). //
                setImageWriter(new ImageWriter("PoolTableC2", 1280, 720)) //
                .setCamera(camera2) //
                .setRayTracer(new RayTracerBasic(scene))
                .setReject(true)
                .setRAYS(1)
                .setSHADOWRAYS(3)
                .setMultithreading(3).setDebugPrint();
        render2.renderImage();
        render2.writeToImage();

//        Render render22 = new Render(). //
//                setImageWriter(new ImageWriter("PoolTableC24", 1, 1 )) //
//                .setCamera(camera22) //
//                .setRayTracer(new RayTracerBasic(scene))
//                .setMultithreading(1).setDebugPrint();
//        render22.renderImage();
//        render22.writeToImage();
    }

    @Test
    public void LikePresentation(){



    }

    public Geometries newTable(Point3D min,Point3D max, Color emission, Material mat){
        return new Geometries(
                new Square(
                new Point3D(max.getX().getCoord(), max.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(max.getX().getCoord(), min.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(min.getX().getCoord(),min.getY().getCoord(),max.getZ().getCoord()),
                new Point3D(min.getX().getCoord(), max.getY().getCoord(), max.getZ().getCoord())
        ).setEmission(emission).setMaterial(mat),
        new Square(
                new Point3D(max.getX().getCoord(), max.getY().getCoord(), min.getZ().getCoord()),
                new Point3D(max.getX().getCoord(), min.getY().getCoord(), min.getZ().getCoord()),
                new Point3D(min.getX().getCoord(),min.getY().getCoord(),min.getZ().getCoord()),
                new Point3D(min.getX().getCoord(), max.getY().getCoord(), min.getZ().getCoord())
        ).setEmission(emission).setMaterial(mat),
         new Square(
                new Point3D(max.getX().getCoord(), max.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(min.getX().getCoord(), max.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(min.getX().getCoord(),max.getY().getCoord(),min.getZ().getCoord()),
                new Point3D(max.getX().getCoord(), max.getY().getCoord(), min.getZ().getCoord())
        ).setEmission(emission).setMaterial(mat),
        new Square(
                new Point3D(max.getX().getCoord(), min.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(max.getX().getCoord(), min.getY().getCoord(), min.getZ().getCoord()),
                new Point3D(min.getX().getCoord(),min.getY().getCoord(),min.getZ().getCoord()),
                new Point3D(min.getX().getCoord(), min.getY().getCoord(), max.getZ().getCoord())
        ).setEmission(emission).setMaterial(mat),
        new Square(
                new Point3D(min.getX().getCoord(), max.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(min.getX().getCoord(), min.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(min.getX().getCoord(),min.getY().getCoord(),min.getZ().getCoord()),
                new Point3D(min.getX().getCoord(), max.getY().getCoord(), min.getZ().getCoord())
        ).setEmission(emission).setMaterial(mat),
        new Square(
                new Point3D(max.getX().getCoord(), max.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(max.getX().getCoord(), min.getY().getCoord(), max.getZ().getCoord()),
                new Point3D(max.getX().getCoord(),min.getY().getCoord(),min.getZ().getCoord()),
                new Point3D(max.getX().getCoord(), max.getY().getCoord(), min.getZ().getCoord())
        ).setEmission(emission).setMaterial(mat));
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


