package vanim.core;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import vanim.storage.Color;
import vanim.storage.vector.FVector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Applet extends PApplet {
    protected final String G2D = "vanim.core.Graphics2D";

    public Graphics2D createGraphics2D(int w, int h) {
        return this.makeGraphics2D(w, h);
    }

    protected Graphics2D makeGraphics2D(int w, int h) {

        try {
            Class<?> rendererClass = Class.forName(G2D);

            Constructor<?> constructor = rendererClass.getConstructor();
            Graphics2D pg = (Graphics2D) constructor.newInstance();

            pg.setParent(this);
            pg.setPrimary(false);

            pg.setSize(w, h);

            // everything worked, return it
            return pg;

        } catch (InvocationTargetException ite) {
            String msg = ite.getTargetException().getMessage();
            if ((msg != null) &&
                    (msg.contains("no jogl in java.library.path"))) {
                // Is this true anymore, since the JARs contain the native libs?
                throw new RuntimeException("The jogl library folder needs to be " +
                        "specified with -Djava.library.path=/path/to/jogl");

            } else {
                printStackTrace(ite.getTargetException());
                Throwable target = ite.getTargetException();
        /*
        // removing for 3.2, we'll see
        if (platform == MACOSX) {
          target.printStackTrace(System.out);  // OS X bug (still true?)
        }
        */
                throw new RuntimeException(target.getMessage());
            }

        } catch (ClassNotFoundException ignored) {
            throw new RuntimeException("The " + G2D +
                    " renderer is not in the class path.");

        } catch (Exception e) {
            if ((e instanceof IllegalArgumentException) ||
                    (e instanceof NoSuchMethodException) ||
                    (e instanceof IllegalAccessException)) {
                if (e.getMessage().contains("cannot be <= 0")) {
                    // IllegalArgumentException will be thrown if w/h is <= 0
                    // http://code.google.com/p/processing/issues/detail?id=983
                    throw new RuntimeException(e);

                } else {
                    printStackTrace(e);
                    String msg = G2D + " needs to be updated " +
                            "for the current release of Processing.";
                    throw new RuntimeException(msg);
                }
            } else {
        /*
        if (platform == MACOSX) {
          e.printStackTrace(System.out);  // OS X bug (still true?)
        }
        */
                printStackTrace(e);
                throw new RuntimeException(e.getMessage());
            }
        }

    }

    public void shape(PShape latex, FVector pos) {
        this.shape(latex, pos.getX(), pos.getY());
    }

    public void image(PImage pImage, FVector pos) {
        this.image(pImage, pos.getX(), pos.getY());
    }

    public void fill(Color color) {
        this.fill(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    public void tint(Color color) {
        this.tint(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    // public void shape(PShape latex, FVector pos, float size) {
    //    this.shape(latex,pos.getX(),pos.getY());
    // }
}
