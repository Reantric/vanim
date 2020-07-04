package vanim.storage;

import vanim.root.modular.Interpolatable;
import vanim.util.MapConstant;
import vanim.util.Mapper;

import static vanim.util.MapConstant.EASE_IN_OUT;
import static vanim.util.MapConstant.QUADRATIC;


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

    public float getValue() {
        return value;
    }

    @Override
    public boolean interpolate(Float bound, MapConstant interpType, float time) {
        if (interpolationComplete) {
            prevVal = value;
            incrementor = 0;
        }
        //start1 and stop1 just signify how fast the thing should happen!
        value = Mapper.map2(incrementor++, 0, Math.abs(bound - prevVal) * time, prevVal, bound, interpType, EASE_IN_OUT);
        interpolationComplete = Math.abs(bound - value) < EPSILON;
        //   System.out.println("Color: " + value + " prevval: " + prevVal + " INC: " + incrementor + " bound: " + bound);
        if (interpolationComplete || Float.isNaN(value)) {
            value = bound;
        }

        return interpolationComplete;
    }

    public boolean interpolate(int bound, MapConstant interpType, float time) {
        return interpolate((float) bound, interpType, time);
    }

    public boolean interpolate(float bound) {
        return this.interpolate(bound, QUADRATIC, 1);
    }

    public boolean interpolate(float bound, float speed) {
        return this.interpolate(bound, QUADRATIC, speed);
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
