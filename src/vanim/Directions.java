package vanim;

import processing.core.PApplet;
import vanim.directions.Scene;
import vanim.storage.Scale;
import vanim.util.Mapper;
import vanim.util.Useful;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static vanim.planar.PI;

public class Directions {
    public static boolean[] sceneStep = new boolean[100];
    public static Set<Scene> allScenes = new HashSet<>();
    public static int destinationInc = 1; // be consistent with mapInc initialization!
    // ^^ Also the reason that destinationInc-1 is used and not +1
    public static final Float[] destinationOnCircle = {-PI, 0f, PI / 4, PI / 2}; // A sad necessity
    // ^^ See if this could become a circular linked list? Along with destinationOnCircleLabel
    public static float incTrack = 0, inc = 0, mapInc = Mapper.map2(inc, 0f, 2f, -destinationOnCircle[0], -destinationOnCircle[1], Mapper.SINUSOIDAL, Mapper.EASE_IN_OUT);
    public static float globalIncrementor = 0.01f;
    public static int frameCountTrack = 0;
    // public static Line d = new Line(2);
    //Cosmetics

    public static void init(PApplet window) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File[] files = new File(".\\src\\vanim\\directions\\subscene").listFiles();

        if (files != null)
            for (File file : files) {
                if (file.isFile()) { // Assuming folders?
                    Class<?> c = Class.forName("vanim.directions.subscene." + Useful.removeExtension(file.getName()));
                    if (Scene.class.isAssignableFrom(c))
                        allScenes.add((Scene) c.getDeclaredConstructor(PApplet.class).newInstance(window));
                }
            }

        System.out.println(allScenes);
    }

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
    public static Scale sinus = new Scale(1, 1);

    public static void directions() {
        //call the scenes here
        for (Scene s : allScenes) {
            if (!s.execute())
                break;
        }

    }
}
