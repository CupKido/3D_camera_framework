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

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    private Material material = new Material();
    protected Color emission = Color.BLACK;
    public abstract Vector getNormal(Point3D point);
}
