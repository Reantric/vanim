package vanim.storage;

public class Color {
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
        return brightness;
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


}
