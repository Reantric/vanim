package vanim.storage;

import vanim.root.modular.Interpolatable;
import vanim.util.MapConstant;

import static vanim.util.MapConstant.QUADRATIC;

public class Color implements Interpolatable<Color> {
    Subcolor hue, saturation, brightness, alpha;

    public Color(float hue) {
        this(hue, 255, 255, 255);
    }

    public Color(float hue, float saturation) {
        this(hue, saturation, 255, 255);

    }

    public Color(float hue, float saturation, float brightness) {
        this(hue, saturation, brightness, 255);
    }

    public Color(float hue, float saturation, float brightness, float alpha) {
        this.hue = new Subcolor(hue);
        this.saturation = new Subcolor(saturation);
        this.brightness = new Subcolor(brightness);
        this.alpha = new Subcolor(alpha);
    }

    public Color() {
        this(0, 0, 0);
    }

    public Color(Subcolor hue, Subcolor saturation, Subcolor brightness, Subcolor alpha) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        this.alpha = alpha; // Storing reference
    }

    public Subcolor getHue() {
        return hue;
    }

    public Subcolor getSaturation() {
        return saturation;
    }

    public Subcolor getBrightness() {
        return brightness;
    }

    public Subcolor getAlpha() {
        return alpha;
    }

    public void setHue(float newHue) {
        hue.setValue(newHue);
    }

    public void setSaturation(float newSat) {
        saturation.setValue(newSat);
    }

    public void setBrightness(float newBri) {
        brightness.setValue(newBri);
    }

    public void setAlpha(float newAlpha) {
        alpha.setValue(newAlpha);
    }

    public java.awt.Color toJavaRGB() {
        return java.awt.Color.getHSBColor(hue.getValue() / 255, saturation.getValue() / 255, brightness.getValue() / 255);
    }

    @Override
    public boolean interpolate(Color color, MapConstant interpType, float speed) {
        return this.getHue().interpolate(color.getHue().getValue(), interpType, speed) &
                this.getSaturation().interpolate(color.getSaturation().getValue(), interpType, speed) &
                this.getBrightness().interpolate(color.getBrightness().getValue(), interpType, speed) &
                this.getAlpha().interpolate(color.getAlpha().getValue(), interpType, speed);

    }

    public boolean interpolate(Color color) {
        return this.interpolate(color, QUADRATIC, 1);
    }

    public String toString() {
        return String.format("[ %s, %s, %s, %s ]", hue, saturation, brightness, alpha);
    }

}
