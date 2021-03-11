package jct.taler.targils.targil1.geometries;

import jct.taler.targils.targil1.primitives.*;

public interface Geometry {
    Vector getNormal();
    Vector getNormal(Point3D point);
}
