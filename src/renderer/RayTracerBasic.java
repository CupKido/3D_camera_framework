package renderer;

import elements.LightSource;
import primitives.*;
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
       return P.geometry.getEmission().add(scene.ambientlight.getIntensity()).add(calcLocalEffects(P, ray));





    }

    private Color calcLocalEffects(GeoPoint P, Ray ray){
        Vector v = ray.getDir();
        Vector n = P.geometry.getNormal(P.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if(nv == 0) return Color.BLACK;
        Material mat = P.geometry.getMaterial();
        int Shininess = mat.getnShininess();
        double kd = mat.getkD(), ks = mat.getkS();

        Color color = Color.BLACK;
        for (LightSource light:
                scene.lights) {
            Vector l = light.getL(P.point).normalized();
            Vector r = l.subtract(n.scale(n.dotProduct(l)).scale(2)).normalized();
            double nl = Util.alignZero(n.dotProduct(l));
            if(nv * nl > 0){
                Color lightIntensity = light.getIntensity(P.point);
                Color D = lightIntensity.scale(kd * Math.abs(nl));
                Color S = lightIntensity.scale(ks * Math.pow(v.scale(-1).dotProduct(r), Shininess));
                color = color.add(D).add(S);
            }
        }
        return color;
    }
}
