package vanim.storage;

import vanim.root.modular.Interpolatable;
import vanim.util.Mapper;
import vanim.util.map.MapEase;
import vanim.util.map.MapType;

import static vanim.util.map.MapType.QUADRATIC;


public class Subcolor implements Interpolatable<Float> {
    float value, prevVal;
    int incrementor;
    boolean interpolationComplete = true;
    final double EPSILON = 0.1;

    public Subcolor(float val) {
        this.value = val;
    }

    public Subcolor() {
        this(0);
    }

    public Subcolor(Subcolor s) {
        this.value = s.getValue();
        this.prevVal = s.prevVal;
        this.incrementor = s.incrementor;
        this.interpolationComplete = s.interpolationComplete;
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean interpolate(Float bound, MapType interpType, float time, MapEase easing) {
        if (interpolationComplete) {
            prevVal = value;
            incrementor = 0;
        }
        //start1 and stop1 just signify how fast the thing should happen!
        value = Mapper.map2(incrementor++, 0, Math.abs(bound - prevVal) * time, prevVal, bound, interpType, easing);
        interpolationComplete = Math.abs(bound - value) < EPSILON;
        //   System.out.println("Color: " + value + " prevval: " + prevVal + " INC: " + incrementor + " bound: " + bound);
        if (interpolationComplete || Float.isNaN(value)) {
            value = bound;
        }

        return interpolationComplete;
    }

    public boolean interpolate(int bound, MapType interpType, float time) {
        return interpolate((float) bound, interpType, time, MapEase.EASE_IN_OUT);
    }

    public boolean interpolate(float bound) {
        return this.interpolate(bound, QUADRATIC, 1, MapEase.EASE_IN_OUT);
    }

    public boolean interpolate(float bound, float speed) {
        return this.interpolate(bound, QUADRATIC, speed, MapEase.EASE_IN_OUT);
    }

    public boolean interpolate(float bound, MapEase easing) {
        return this.interpolate(bound, QUADRATIC, 1, easing);
    }

    public boolean is255() {
        return Math.abs(255 - value) < EPSILON;
    }

    public void setValue(float newValue) {
        this.value = newValue;
    }

    public boolean is0() {
        return Math.abs(0 - value) < EPSILON;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
