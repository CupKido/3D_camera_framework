package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.LinkedList;
import java.util.MissingResourceException;

public class RayTracerBasic extends RayTracerBase{


    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;
    private static final double DIS = 0.005;


    public RayTracerBasic(Scene _scene){
        super(_scene);
    }

    @Override
    public Color traceRay(Ray R){
        GeoPoint closestPoint = findClosestIntersection(R);
        return closestPoint == null ? scene.background : calcColor(closestPoint, R);
    }

    private Color calcColor(GeoPoint P, Ray ray) {
        return calcColor(P, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientlight.getIntensity());

    }

    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.getkR();
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.getkR(), kkr);
        double kkt = k * material.getkT();
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.getkT(), kkt));
        return color;

    }



    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v.subtract(n.scale(n.dotProduct(v)).scale(2)), n);

    }
    private Color calcLocalEffects(GeoPoint P, Ray ray, double k) {
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
            l = light.getL(P.point);
            nl = Util.alignZero(n.dotProduct(l));
            r = l.subtract(n2.scale(nl)).normalized();
            if(nv * nl > 0){
                double ktr;
                try {
                    ktr = softShadowsTransparency(light, l, n, P, 5);
                }catch (Exception e){
                    ktr = 0.0;
                }

                if (ktr * k > MIN_CALC_COLOR_K) {

                    Color lightIntensity = light.getIntensity(P.point).scale(ktr);
                    Color D = lightIntensity.scale(mat.getkD() * Math.abs(nl));
                Color S = lightIntensity.scale(mat.getkS() * pow(v.scale(-1).dotProduct(r), mat.getnShininess()));
                color = color.add(D, S);
                }
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

    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n); // refactored ray head move
        LinkedList<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null) return true;
        double lightDistance = light.getDistance(gp.point);
        for (GeoPoint p : intersections) {
            if (Util.alignZero(gp.point.distance(p.point) - lightDistance) <= 0 && p.geometry.getMaterial().getkT() == 0)
                return false;
        }
        return true;
    }

    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (Util.alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

    private GeoPoint findClosestIntersection(Ray ray){
        LinkedList<GeoPoint> L =scene.geometries.findGeoIntersections(ray);
        return L == null ? null : ray.findClosestGeoPoint(L);
    }

    public Color traceRays(LinkedList<Ray> rays) {
        Color res = Color.BLACK;
        for (Ray ray:
             rays) {
            res = res.add(traceRay(ray));
        }
        return res.reduce(rays.size());
    }

    private double softShadowsTransparency(LightSource light, Vector l, Vector n, GeoPoint geopoint, int GridSize) throws Exception {
         // from point to light source

    //    creating way to move on light plane
        Vector someD;
        Vector someDOther;
    try {
        someD = new Vector(l.getDotEquals0X(1, 1), 1, 1).normalized();
        someDOther = l.crossProduct(someD).normalized();
    }catch (Exception e){
        try {
            someD = new Vector(1, l.getDotEquals0Y(1, 1), 1).normalized();
            someDOther = l.crossProduct(someD).normalized();
        }catch (Exception r){
            try {
                someD = new Vector(1, 1, l.getDotEquals0Z(1, 1)).normalized();
                someDOther = l.crossProduct(someD).normalized();
            }catch (Exception t){
                return 0.0;
            }
        }
    }

        double sum = 0;
        Vector nV;
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                nV = l;
                if(i != 0){
                    nV = nV.add(someD.scale(i * DIS));
                }
                if(j != 0){
                    nV = nV.add(someDOther.scale(j * DIS));
                }
                sum += otherForSS(light, nV, n, geopoint.point);
            }
        }

        return sum/ (GridSize * GridSize);
    }

    private double otherForSS(LightSource light, Vector l, Vector n, Point3D point){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(point, lightDirection, n);
        double lightDistance = light.getDistance(point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (Util.alignZero(gp.point.distance(point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }
}
