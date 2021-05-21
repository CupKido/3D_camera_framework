package renderer;

import elements.LightSource;
import primitives.Color;
import primitives.Material;
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
        return calcColor(closest, R);
    }

    public Color calcColor(GeoPoint P, Ray ray)
    {
        Color res = P.geometry.getEmission().add(scene.ambientlight.getIntensity());




        return res;
    }

    private Color calcLocalEffects(GeoPoint P, Ray ray){
        Color res = Color.BLACK;
        Material mat = P.geometry.getMaterial();
        for (LightSource light:
                scene.lights) {
            res += mat.getkD() * Math.abs(light.getL(P.point).dotProduct(P.geometry.getNormal(P.point))) +
                   mat.getkS() * Math.max(0, ray.getDir().scale(-1).dotProduct())
        }
    }
}
