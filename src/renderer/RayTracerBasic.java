package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.LinkedList;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene _scene){
        super(_scene);
    }

    @Override
    public Color traceRay(Ray R){
        LinkedList<GeoPoint> L = scene.geometries.findGeoIntersections(R);
        if(L == null)
        {
            return scene.background;
        }
        GeoPoint closest = R.findClosestGeoPoint(L);
        return calcColor(closest);
    }

    public Color calcColor(GeoPoint P)
    {
        return P.geometry.getEmission().add(scene.ambientlight.getIntensity());
    }
}
