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

    public Color calcColor(GeoPoint P, Ray ray) {
        return P.geometry.getEmission().add(scene.ambientlight.getIntensity(), calcLocalEffects(P, ray));
    }

    private Color calcLocalEffects(GeoPoint P, Ray ray){
        Vector v = ray.getDir();
        Vector n = P.geometry.getNormal(P.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if(nv == 0) return Color.BLACK;
        Material mat = P.geometry.getMaterial();
        double nl = 0;
        Vector r;
        Vector l;
        Color color = Color.BLACK;
        Vector n2 = n.scale(2);
        for (LightSource light:
                scene.lights) {
            l = light.getL(P.point).normalized();
            nl = Util.alignZero(n.dotProduct(l));
            r = l.subtract(n2.scale(nl)).normalized();
            if(nv * nl > 0){
                Color lightIntensity = light.getIntensity(P.point);
                Color D = lightIntensity.scale(mat.getkD() * Math.abs(nl));
                Color S = lightIntensity.scale(mat.getkS() * pow(v.scale(-1).dotProduct(r), mat.getnShininess()));
                color = color.add(D, S);
            }
        }
        return color;
    }

    private double pow(double x, int y){
        double sum = 1;
        while(y > 0){
            sum = sum * x;
            y--;
        }
        return sum;
    }

}
