package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

public class RayTracerBasic extends RayTracerBase{


    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;
    private static final double DIS = 0.01;




    private boolean partialAdaptive = true;
    private int SHADOWRAYS = 7;


    public void setPartialAdaptive(boolean partialAdaptive) {
        this.partialAdaptive = partialAdaptive;
    }

    public void setSHADOWRAYS(int SHADOWRAYS) {
        this.SHADOWRAYS = SHADOWRAYS;
    }

    public RayTracerBasic(Scene _scene){
        super(_scene);
    }

    /**
     * traces one ray, basically return its color after finding closest intersection
     * @param R
     * @return
     */
    @Override
    public Color traceRay(Ray R){
        GeoPoint closestPoint = findClosestIntersection(R);
        if(closestPoint != null){
        if(closestPoint.geometry.getEmission().equals(new Color(110, 80, 15))){
            int x = 3;
        }}
        return closestPoint == null ? scene.background : calcColor(closestPoint, R);
    }

    /**
     * finds color, light, reflection and transparency of P, while r is the ray from the camera
     * @param P
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint P, Ray ray) {
        return calcColor(P, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientlight.getIntensity());

    }

    /**
     * starts recursion of finding global color effects, such as transparency and reflections
     * @param intersection
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * recursion of finding global color effects, such as transparency and reflections
     * @param gp
     * @param v
     * @param level
     * @param k
     * @return
     */
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


    /**
     * recursion of finding global color effects, such as transparency and reflections
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    /**
     * constructs refracted ray
     * @param point
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * constructs reflected ray
     * @param point
     * @param v
     * @param n
     * @return
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v.subtract(n.scale(n.dotProduct(v)).scale(2)), n);

    }

    /**
     * find local effects of color, such as shadow and light
     * @param P
     * @param ray
     * @param k
     * @return
     */
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
            if(nl != 0) {
                r = l.subtract(n2.scale(nl)).normalized();
                if (nv * nl > 0) {
                    double ktr = softShadowsTransparency(light, l, n, P, SHADOWRAYS);

                    if (ktr * k > MIN_CALC_COLOR_K) {

                        Color lightIntensity = light.getIntensity(P.point).scale(ktr);
                        Color D = lightIntensity.scale(mat.getkD() * Math.abs(nl));
                        Color S = lightIntensity.scale(mat.getkS() * pow(v.scale(-1).dotProduct(r), mat.getnShininess()));
                        color = color.add(D, S);
                    }
                }
            }
        }
        return color;
    }

    private double pow(double x, int y){
        if(y < 0){
            return 1/pow(x, -y);
        }
        double sum = 1;
        while(y > 0){
            sum = sum * x;
            y--;
        }
        return sum;
    }

    /**
     * finds if theres a shadow, and its intensity
     * creates only harsh shadows
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
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
        List<GeoPoint> L =scene.geometries.findGeoIntersections(ray);
        return L == null ? null : ray.findClosestGeoPoint(L);
    }

    /**
     * returns an average of the color of all the rays
     * if partialAdaptive is true then itll check 4 corners and if they are all in the same color then it'll return that color
     * @param rays
     * @return
     */
    public Color traceRays(LinkedList<Ray> rays) {
        if(rays.size() == 1){
            return traceRay(rays.getFirst());
        }
        Color res = Color.BLACK;
        int outOfRays = 0;

        if(partialAdaptive) {

            Color leftup = Color.BLACK;
            Color rightup = Color.BLACK;
            Color leftdown = Color.BLACK;
            Color rightdown = Color.BLACK;

            int gridsize = (int) Math.sqrt(rays.size());

            leftup = traceRay(rays.get(0));
            leftdown = traceRay(rays.get(gridsize - 1));
            rightup = traceRay(rays.get(rays.size() - gridsize));
            rightdown = traceRay(rays.get(rays.size() - 1));
            if (leftup.equals(rightup) && rightup.equals(rightdown) && rightdown.equals(leftdown)) {
                return rightup;
            }

            res.add(leftdown, leftup, rightdown, rightup);
            rays.remove(rays.get(0));
            rays.remove(rays.get(gridsize - 1));
            rays.remove(rays.get(rays.size() - gridsize));
            rays.remove(rays.get(rays.size() - 1));
            outOfRays = 4;
        }
        for (Ray ray:
                rays) {
            res = res.add(traceRay(ray));
        }
        return res.reduce(rays.size() + outOfRays);
    }

    private double softShadowsTransparency(LightSource light, Vector l, Vector n, GeoPoint geopoint, int GridSize) {
        if(GridSize == 1){
            return transparency(light, l , n, geopoint);
        }
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

        int grid2 = (GridSize - 1) / 2;
        double sum = 0;
        Vector nV;
        for (int i = -grid2; i <= grid2; i++) {
            for (int j = -grid2; j <= grid2; j++) {
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

    public void CreateBVH(){
        scene.geometries.CreateBVH();
    }
}