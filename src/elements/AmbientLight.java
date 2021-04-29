package elements;

import primitives.Color;

public class AmbientLight {
    Color intensity;

    public AmbientLight(Color Ia, double Ka){
        intensity = new Color(Ia.getColor().getRed() * Ka,Ia.getColor().getGreen() * Ka,Ia.getColor().getBlue() * Ka);
    }
    public Color getIntensity(){
        return intensity;
    }
}
