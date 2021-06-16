package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Intersectable {
    
    default List<Point3D> findIntersections(Ray ray) {

        /**
         * finds intersections between an intersectable and a ray and returns the points without their geometry's info
         */
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }

    //Finds intersections between a ray and a body
//    LinkedList<GeoPoint> findGeoIntersections(Ray ray);

    /**
     * finds intersections between an intersectable and a ray and returns the points with their geometry's info
     * @param ray
     * @return
     */
    List<GeoPoint> findGeoIntersections(Ray ray);

    /**
     * builds a bounding box for every intersection
     * @return
     */
    default BoundingBox CreateBox(){
        return null;
    }

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



