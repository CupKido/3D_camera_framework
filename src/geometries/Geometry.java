package geometries;
import primitives.*;

public abstract class Geometry implements Intersectable {

    public Color getEmission() {
        return emission;
    }
    public Geometry setEmission(Color c){
        emission = c;
        return this;
    }
    protected Color emission = Color.BLACK;
    public abstract Vector getNormal(Point3D point);
}
