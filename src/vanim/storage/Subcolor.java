package vanim.storage;

import vanim.util.Mapper;

public class Subcolor {
    float value, prevVal;
    int incrementor;
    boolean interpolationComplete;

    public Subcolor(float val) {
        this.value = val;
    }

    public float getValue() {
        return value;
    }

    public boolean interp(float bound, int interpType, float speed) {
        if (!interpolationComplete) {
            prevVal = value;
        } else {
            incrementor = 0;
            value = bound;
        }

        //start1 and stop1 just signify how fast the thing should happen!
        value = Mapper.map2(incrementor++, 0, 255 * speed, prevVal, bound, interpType, Mapper.EASE_IN_OUT);
        interpolationComplete = Math.abs(bound - value) < 0.01f;
        return interpolationComplete;
    }

    public boolean interp(float bound) {
        return this.interp(bound, Mapper.QUADRATIC, 1);
    }

    public boolean interp(float bound, float speed) {
        return this.interp(bound, Mapper.QUADRATIC, speed);
    }

    public boolean is255() {
        return Math.abs(255 - value) < 0.01;
    }

    public void setValue(float newValue) {
        this.value = newValue;
    }
}
