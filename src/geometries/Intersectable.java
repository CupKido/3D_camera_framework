package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

public interface Intersectable {
    LinkedList<Point3D> findIntersections(Ray ray); //Finds intersections between a ray and a body
}
