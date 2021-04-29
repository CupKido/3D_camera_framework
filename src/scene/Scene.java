package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    public String name = "";
    public Color background = Color.BLACK;
    public AmbientLight ambientlight = new AmbientLight(Color.BLACK, 0);
    public Geometries geometries;

    public Scene(String _name)
    {
        name = _name;
        geometries = new Geometries();
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
