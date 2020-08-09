package vanim.util;

import vanim.util.map.MapEase;
import vanim.util.map.MapType;

import static processing.core.PApplet.*;
import static vanim.util.map.MapEase.*;

public class Mapper {
    /* The map2() function supports the following easing types */

    /*
     * A map() replacement that allows for specifying easing curves
     * with arbitrary exponents.
     *
     * value :   The value to map
     * start1:   The lower limit of the input range
     * stop1 :   The upper limit of the input range
     * start2:   The lower limit of the output range
     * stop2 :   The upper limit of the output range
     * type  :   The type of easing (see above)
     * when  :   One of EASE_IN, EASE_OUT, or EASE_IN_OUT
     */
    public static float map2(float value, float start1, float stop1, float start2, float stop2, MapType type, MapEase when) {
        float c = stop2 - start2;
        float t = value - start1;
        float d = stop1 - start1;
        float p = 0.5f;
        switch (type) {
            case LINEAR:
                return c * t / d + start2;
            case SQRT:
                if (when == EASE_IN) {
                    t /= d;
                    return c * pow(t, p) + start2;
                } else if (when == EASE_OUT) {
                    t /= d;
                    return c * (1 - pow(1 - t, p)) + start2;
                } else if (when == EASE_IN_OUT) {
                    t /= d / 2;
                    if (t < 1) return c / 2 * pow(t, p) + start2;
                    return c / 2 * (2 - pow(2 - t, p)) + start2;
                }
                break;
            case QUADRATIC:
                if (when == EASE_IN) {
                    t /= d;
                    return c * t * t + start2;
                } else if (when == EASE_OUT) {
                    t /= d;
                    return -c * t * (t - 2) + start2;
                } else if (when == EASE_IN_OUT) {
                    t /= d / 2;
                    if (t < 1) return c / 2 * t * t + start2;
                    t--;
                    return -c / 2 * (t * (t - 2) - 1) + start2;
                }
                break;
            case CUBIC:
                if (when == EASE_IN) {
                    t /= d;
                    return c * t * t * t + start2;
                } else if (when == EASE_OUT) {
                    t /= d;
                    t--;
                    return c * (t * t * t + 1) + start2;
                } else if (when == EASE_IN_OUT) {
                    t /= d / 2;
                    if (t < 1) return c / 2 * t * t * t + start2;
                    t -= 2;
                    return c / 2 * (t * t * t + 2) + start2;
                }
                break;
            case QUARTIC:
                if (when == EASE_IN) {
                    t /= d;
                    return c * t * t * t * t + start2;
                } else if (when == EASE_OUT) {
                    t /= d;
                    t--;
                    return -c * (t * t * t * t - 1) + start2;
                } else if (when == EASE_IN_OUT) {
                    t /= d / 2;
                    if (t < 1) return c / 2 * t * t * t * t + start2;
                    t -= 2;
                    return -c / 2 * (t * t * t * t - 2) + start2;
                }
                break;
            case QUINTIC:
                if (when == EASE_IN) {
                    t /= d;
                    return c * t * t * t * t * t + start2;
                } else if (when == EASE_OUT) {
                    t /= d;
                    t--;
                    return c * (t * t * t * t * t + 1) + start2;
                } else if (when == EASE_IN_OUT) {
                    t /= d / 2;
                    if (t < 1) return c / 2 * t * t * t * t * t + start2;
                    t -= 2;
                    return c / 2 * (t * t * t * t * t + 2) + start2;
                }
                break;
            case SINUSOIDAL:
                if (when == EASE_IN) {
                    return -c * cos(t / d * (PI / 2)) + c + start2;
                } else if (when == EASE_OUT) {
                    return c * sin(t / d * (PI / 2)) + start2;
                } else if (when == EASE_IN_OUT) {
                    return -c / 2 * (cos(PI * t / d) - 1) + start2;
                }
                break;
            case EXPONENTIAL:
                if (when == EASE_IN) {
                    return c * pow(2, 10 * (t / d - 1)) + start2;
                } else if (when == EASE_OUT) {
                    return c * (-pow(2, -10 * t / d) + 1) + start2;
                } else if (when == EASE_IN_OUT) {
                    t /= d / 2;
                    if (t < 1) return c / 2 * pow(2, 10 * (t - 1)) + start2;
                    t--;
                    return c / 2 * (-pow(2, -10 * t) + 2) + start2;
                }
                break;
            case CIRCULAR:
                if (when == EASE_IN) {
                    t /= d;
                    return -c * (sqrt(1 - t * t) - 1) + start2;
                } else if (when == EASE_OUT) {
                    t /= d;
                    t--;
                    return c * sqrt(1 - t * t) + start2;
                } else if (when == EASE_IN_OUT) {
                    t /= d / 2;
                    if (t < 1) return -c / 2 * (sqrt(1 - t * t) - 1) + start2;
                    t -= 2;
                    return c / 2 * (sqrt(1 - t * t) + 1) + start2;
                }
                break;
        }
        return 0;
    }

    /*
     * A map() replacement that allows for specifying easing curves
     * with arbitrary exponents.
     *
     * value :   The value to map
     * start1:   The lower limit of the input range
     * stop1 :   The upper limit of the input range
     * start2:   The lower limit of the output range
     * stop2 :   The upper limit of the output range
     * v     :   The exponent value (e.g., 0.5, 0.1, 0.3)
     * when  :   One of EASE_IN, EASE_OUT, or EASE_IN_OUT
     */
    public static float map3(float value, float start1, float stop1, float start2, float stop2, float v, MapEase when) {
        float c = stop2 - start2;
        float t = value - start1;
        float d = stop1 - start1;
        float out = 0;
        if (when == EASE_IN) {
            t /= d;
            out = c * pow(t, v) + start2;
        } else if (when == EASE_OUT) {
            t /= d;
            out = c * (1 - pow(1 - t, v)) + start2;
        } else if (when == EASE_IN_OUT) {
            t /= d / 2;
            if (t < 1) return c / 2 * pow(t, v) + start2;
            out = c / 2 * (2 - pow(2 - t, v)) + start2;
        }
        return out;
    }
}
