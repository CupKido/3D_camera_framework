package geometries;

import primitives.*;

import java.util.List;

public interface Intersectable {
    List<Point3D> findIntersections(Ray ray); //Finds intersections between a ray and a body
}
