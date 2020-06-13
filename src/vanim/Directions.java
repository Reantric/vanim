package vanim;

import processing.core.PApplet;
import vanim.storage.Scale;
import vanim.util.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static vanim.planar.PI;

public class Directions {
    public static boolean[] step = new boolean[100];
    public static int destinationInc = 1; // be consistent with mapInc initialization!
    // ^^ Also the reason that destinationInc-1 is used and not +1
    public static final Float[] destinationOnCircle = {-PI, 0f, PI / 4, PI / 2}; // A sad necessity
    // ^^ See if this could become a circular linked list? Along with destinationOnCircleLabel
    public static float incTrack = 0, inc = 0, mapInc = Mapper.map2(inc, 0f, 2f, -destinationOnCircle[0], -destinationOnCircle[1], Mapper.SINUSOIDAL, Mapper.EASE_IN_OUT);
    public static float globalIncrementor = 0.01f;
    public static int frameCountTrack = 0;
   // public static Line d = new Line(2);
    //Cosmetics
    public static final List<String> destinationOnCircleLabel = Arrays.stream(destinationOnCircle).map(x -> {
        if (String.valueOf(x).length() > 3)
            return switch (String.valueOf(x).substring(0, 4)) {
                case "3.14", "-3.1" -> "PI";
                case "1.57" -> "PI/2";
                case "-1.5" -> "-PI/2";
                case "0.78" -> "PI/4";
                case "-0.7" -> "-PI/4";
                default -> String.valueOf(x).substring(0, 4);
            };
       return String.valueOf(x);
   }).collect(Collectors.toList());
    public static Scale sinus;

    public static void directions(PApplet window) {


    }
}
