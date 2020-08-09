package vanim.specific.rational;

import java.util.AbstractMap;
import java.util.Map;

public class Fraction {
    public static long gcd(long num1, long num2) {
        long large = Math.max(num1, num2);
        long small = Math.min(num1, num2);

        while (small > 0) {
            long temp = small;
            small = large % small;
            large = temp;
        }

        return large;
    }

    public static Map.Entry<Long, Long> reduce(long numerator, long denominator) {
        long commonFactor = gcd(Math.abs(numerator), denominator);
        numerator /= commonFactor;
        denominator /= commonFactor;
        return new AbstractMap.SimpleEntry<>(numerator, denominator);
    }
}
