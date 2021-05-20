package elements;

import primitives.Color;

class Light {
    public Color getIntensity() {
        return intensity;
    }

    private Color intensity;
    protected Light(Color _intensity)
    {
        intensity = _intensity;
    }
}
