package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight{

    public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ){
        super(_intensity, _position, _kC, _kL, _kQ);
        direction = _direction.normalized();
    }

    private Vector direction;

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(getL(p).normalized())));
    }
}
