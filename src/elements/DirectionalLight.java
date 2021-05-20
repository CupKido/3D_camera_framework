package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    public DirectionalLight(Color _intensity, Vector _direction){
        super(_intensity);
        direction = _direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction;
    }


}
