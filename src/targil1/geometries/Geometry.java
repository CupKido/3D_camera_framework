package targil1.geometries;

import targil1.primitives.*;

public interface Geometry {
    Vector getNormal();
    Vector getNormal(Point3D point);
}
