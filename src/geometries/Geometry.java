package geometries;

import primitives.*;

public interface Geometry {
    Vector getNormal();
    Vector getNormal(Point3D point);
}
