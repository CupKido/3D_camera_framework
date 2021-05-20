package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public interface Intersectable {
    LinkedList<Point3D> findIntersections(Ray ray); //Finds intersections between a ray and a body
    LinkedList<GeoPoint> findGeoIntersections(Ray ray);

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }
}



