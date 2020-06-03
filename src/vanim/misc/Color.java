package vanim.misc;

public class Color {
    float hue, saturation, brightness;
    float prevHue,prevSat,prevBright;
    long incrementor = 0, prevIncrementor = 0;
    boolean interpolation = false;

    public Color(float hue){
        this(hue,255,255);
    }

    public Color(float hue, float saturation){
        this(hue,saturation,255);

    }
    public Color(float hue, float saturation, float brightness){
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    public Color() {
        this(0,0,0);
    }

    public float getHue(){
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getBrightness() {
        return brightness;
    }

    public void interpHue(float bound,int interpType){
        if (!interpolation) {
            prevHue = hue;
            prevSat = saturation;
            prevBright = brightness;
            prevIncrementor = incrementor;
        }
        hue = Mapper.map2(incrementor++,0,250,prevIncrementor,bound,Mapper.QUADRATIC,Mapper.EASE_IN_OUT);
        interpolation = Math.abs(bound-hue) < 0.01f;

    }


    public boolean hue255() {
        return Math.abs(255-hue) < 0.01;
    }
}
