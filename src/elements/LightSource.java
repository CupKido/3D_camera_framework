package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {
    /**
     * returns light's intensity on point p
     * @param p
     * @return
     */
    public Color getIntensity(Point3D p);

    /**
     * returns vector from light to point p (normalized)
     * @param p
     * @return
     */
    public Vector getL(Point3D p);

    /**
     * returns distance from light's point to point p
     * @param p
     * @return
     */
    public double getDistance(Point3D p);
}
