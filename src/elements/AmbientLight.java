package elements;

import primitives.Color;

public class AmbientLight extends Light{

    public AmbientLight(Color Ia, double Ka){
        super(new Color(Ia.getColor().getRed() * Ka,Ia.getColor().getGreen() * Ka,Ia.getColor().getBlue() * Ka));
    }

    public AmbientLight(){
        super(Color.BLACK);
    }
}
