package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;
import java.util.Random;

public class Render {
    private static final int RAYS = 50;
    ImageWriter imageWriter;
    public Camera camera;
    RayTracerBasic rayTracerBasic;

    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic = rayTracerBasic;
        return this;
    }

    public void renderImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: imageWriter resource is missing in renderer.", "ImageWriter", "");
        }
        if (camera == null) {
            throw new MissingResourceException("ERROR: camera resource is missing in renderer.", "Camera", "");
        }
        if (rayTracerBasic == null) {
            throw new MissingResourceException("ERROR: rayTracerBasic resource is missing in renderer.", "RayTracerBasic", "");
        }
        Ray current = null;
        Random rand = new Random();

        for(int i = 0; i < imageWriter.getNx() ; i++){
            for(int j = 0; j < imageWriter.getNy(); j++){
                Color sum = Color.BLACK;
                for(int x = 0; x < RAYS; x++) {
                        current = camera.constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(), (i - imageWriter.getNx()  / 2) + (Math.random()), (j - imageWriter.getNy()   / 2) + (Math.random()));
                        sum = sum.add(rayTracerBasic.traceRay(current));

                }
                sum = sum.reduce(RAYS);
                imageWriter.writePixel(i, j, sum);
            }
        }
    }

    public void printGrid(int interval, Color color){
        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: imageWriter resource is missing in renderer.", "ImageWriter", "");
        }

        for(int j = 0; j <imageWriter.getNy() ; j = j + 1) {
            for(int i = 0; i < imageWriter.getNx() ; i = i + interval){
                    imageWriter.writePixel(i, j, color);
            }
        }

        for(int j = 0; j < imageWriter.getNx() ; j = j + 1) {
            for(int i = 0; i < imageWriter.getNy() ; i = i + interval){
                imageWriter.writePixel(j, i, color);
            }
        }

    }

    public void writeToImage(){

        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: imageWriter resource is missing in renderer.", "ImageWriter", "");
        }
        imageWriter.writeToImage();
    }
}