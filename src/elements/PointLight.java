package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    public PointLight(Color _intensity, Point3D _position){
        super(_intensity);
        position = _position;
    }

    public PointLight(Color _intensity, Point3D _position, double _kC, double _kL, double _kQ){
        super(_intensity);
        position = _position;
        kC = _kC;
        kL = _kL;
        kQ = _kQ;
    }
    public double getDistance(Point3D p){
        return p.distance(position);
    }
    private Point3D position;

    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    private double kC = 1, kL = 0, kQ = 0;


    @Override
    public Color getIntensity(Point3D p) {
        double d2 = position.distanceSquared(p);
        double d = Math.sqrt(d2);
        return getIntensity().reduce(kC + kL * d + kQ * d2);
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position).normalized();
    }
}
