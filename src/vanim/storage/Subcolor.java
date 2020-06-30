package vanim.storage;

import vanim.util.Mapper;

public class Subcolor {
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

    public float getValue() {
        return value;
    }

    public boolean interp(float bound, int interpType, float time) {
        if (interpolationComplete) {
            prevVal = value;
            incrementor = 0;
        }
        //start1 and stop1 just signify how fast the thing should happen!
        value = Mapper.map2(incrementor++, 0, Math.abs(bound - prevVal) * time, prevVal, bound, interpType, Mapper.EASE_IN_OUT);
        interpolationComplete = Math.abs(bound - value) < EPSILON;
        //   System.out.println("Color: " + value + " prevval: " + prevVal + " INC: " + incrementor + " bound: " + bound);
        if (interpolationComplete || Float.isNaN(value)) {
            value = bound;
        }

        return interpolationComplete;
    }

    public boolean interp(float bound) {
        return this.interp(bound, Mapper.QUADRATIC, 1);
    }

    public boolean interp(float bound, float speed) {
        return this.interp(bound, Mapper.QUADRATIC, speed);
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
