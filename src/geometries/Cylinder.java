package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube {

    double _height;


    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height < 0 || isZero(height))
        {
            throw new IllegalArgumentException("Cylinder can't have non-positive height!");
        }
        _height = height;
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray) {
        LinkedList<GeoPoint> intersections = super.findGeoIntersections(ray);

        Point3D p0 = getAxisRay().getP0();
        Vector dir = getAxisRay().getDir();

        if (intersections != null) {
            LinkedList<GeoPoint> temp = new LinkedList<GeoPoint>();

            for (GeoPoint g : intersections) {
                double pointHeight = alignZero(g.point.subtract(p0).dotProduct(dir));
                if (pointHeight > 0 && pointHeight < _height) {
                    temp.add(g);
                }
            }

            if (temp.isEmpty()) {
                intersections = null;
            }
            else {
                intersections = temp;
            }
        }

        LinkedList<GeoPoint> planeIntersection = new Plane(p0, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point.point.equals(p0) || alignZero(point.point.subtract(p0).lengthSquared() - getRadius() * getRadius()) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point.geometry = this;
                intersections.add(point);
            }
        }

        Point3D p1 = p0.add(dir.scale(_height));

        planeIntersection = new Plane(p1, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point.point.equals(p1) || alignZero(point.point.subtract(p1).lengthSquared() - getRadius() * getRadius()) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point.geometry = this;
                intersections.add(point);
            }
        }

        if (intersections != null) {
            for (GeoPoint g : intersections) {
                g.geometry = this;
            }
        }

        return intersections;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Plane pl1 = new Plane(getAxisRay().getP0(), getAxisRay().getDir());

        if (pl1.inPlane(p)) {
            return getAxisRay().getDir().scale(-1);
        }

        Plane pl2 = new Plane(getAxisRay().getP0().add(getAxisRay().getDir().scale(_height)), getAxisRay().getDir());

        if (pl2.inPlane(p)) {
            return getAxisRay().getDir();
        }

        return super.getNormal(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder._height, _height) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nheight = " + _height;
    }
}
