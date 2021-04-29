package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene _scene){
        super(_scene);
    }

    @Override
    public Color traceRay(Ray R){
        LinkedList<Point3D> L = scene.geometries.findIntersections(R);
        if(L == null)
        {
            return scene.background;
        }
        Point3D closest = R.findClosestPoint(L);
        return calcColor(closest);
    }

    public Color calcColor(Point3D P)
    {
        return scene.ambientlight.getIntensity();
    }
}
