package renderer;

import primitives.*;
import scene.Scene;

public class RayTracerBase {
    protected Scene scene;
    public RayTracerBase(Scene _scene)
    {
        scene = _scene;
    }

    //gets Ray and return a color
    public Color traceRay(Ray R){
        return Color.BLACK;
    }
}
