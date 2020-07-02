package vanim.core;

import processing.opengl.PGraphics2D;
import vanim.storage.Color;
import vanim.storage.Subcolor;
import vanim.storage.vector.FVector;


public class Graphics2D extends PGraphics2D {

    public Graphics2D() {
        super();
    }

    public void point(FVector coord) {
        this.point(coord.getX(), coord.getY());
    }

    public void line(FVector start, FVector end) {
        this.line(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public void stroke(Color color) {
        this.stroke(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    public void stroke(Subcolor hue, Subcolor saturation, Subcolor brightness, Subcolor alpha) {
        this.stroke(hue.getValue(), saturation.getValue(), brightness.getValue(), alpha.getValue());
    }

    public void stroke(Subcolor hue, Subcolor saturation, Subcolor brightness) {
        this.stroke(hue.getValue(), saturation.getValue(), brightness.getValue(), 255);
    }

    public void fill(Color color) {
        this.fill(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    public void fill(Subcolor hue, Subcolor saturation, Subcolor brightness, Subcolor alpha) {
        this.fill(hue.getValue(), saturation.getValue(), brightness.getValue(), alpha.getValue());
    }

    public void fill(Subcolor hue, Subcolor saturation, Subcolor brightness) {
        this.fill(hue.getValue(), saturation.getValue(), brightness.getValue(), 255);
    }

}
