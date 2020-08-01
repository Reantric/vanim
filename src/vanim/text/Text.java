package vanim.text;

import vanim.planes.Plane;
import vanim.root.vobjects.AbsoluteVObject;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.LEFT;
import static vanim.planar.CENTER;

/**
 * @author protonlaser91
 */
public class Text extends AbsoluteVObject { // Create superclass called Text
    // and make this one FancyText with the interp
    public String str;
    float tSize;
    int align = CENTER;
    boolean displayRect = true;
    boolean initializable = true;
    protected Color color;

    /**
     * @param p             Plane that is to be drawn on
     * @param s             String to be displayed
     * @param pos           The position of the object on the canvas (in absolute coordinates)
     * @param tSize         The size of the text (can be omitted)
     * @param color         The color of the object, in HSB
     * @param reasonCreated The reason this object was created
     */
    public Text(Plane p, String s, FVector pos, float tSize, Color color, Reason reasonCreated) {
        super(p, pos, color, reasonCreated);
        this.color = color;
        str = s;
        this.tSize = tSize;
        canvas.textSize(this.tSize);
        dimensions.setXY(canvas.textWidth(str), this.tSize);
    }

    public Text(Plane p, String s, FVector pos, Color color, Reason reasonCreated) {
        this(p, s, pos, p.getCanvas().textSize, color, reasonCreated);
    }

    public Text(Plane p, String s, FVector pos, float tSize, Color color) {
        this(p, s, pos, tSize, color, Reason.USER_CREATED);
    }

    public Text(Plane p, String s, FVector pos, Color color) {
        this(p, s, pos, p.getCanvas().textSize, color);
    }

    /**
     * @param ALIGN Set alignment of text using Processing's textAlign modes
     */
    public Text setTextAlign(int ALIGN) {
        align = ALIGN;
        return this;
    }

    /**
     * @param init If the TextVObject should fade into 255 alpha or not
     */
    public Text setInit(boolean init) {
        initializable = init;
        return this;
    }

    /**
     * oh, if you want to display the background rectange, true. else, false
     *
     * @param tf
     */
    public Text setDisplayRect(boolean tf) {
        displayRect = tf;
        return this;
    }

    /**
     * TODO: Scale!
     * Scale everything!!
     *
     * @param s The new Scale object that will replace the original Scale object.
     * @return
     */
    @Override
    public Text scale(Scale s) {
        // Scale later!
        this.scale = s;
        return this;
    }

    /**
     * Display the text along with a background rectangle to allow the text to be seen more easily
     * @param obj Varargs to display the object at coordinates
     * @return When the text is fully visible (transparency > 255)
     */
    @Override
    public boolean display(Object... obj){
        if (initializable) { // Continuously run every time an instance of TextVObject
            // is created and runs this method, which causes it to speed up (BAD).
            color.getAlpha().interpolate(255);
            initializable = !color.getAlpha().is255();
        }

        if (displayRect)
            this.backgroundRect();

        canvas.textSize(tSize);
        canvas.fill(color);

        if (canvas.textAlign != align)
            canvas.textAlign(align);

        canvas.text(str, pos);

        return !initializable;
    }

    /**
     * Create background rectangle around VObject with 125 alpha
     * TODO: Add color options and alpha options
     */
    public Text backgroundRect() {
        canvas.noStroke();
        canvas.fill(0, 0, 0, color.getAlpha().getValue() / 2); //125
        if (canvas.textAlign == LEFT) {
            canvas.rectMode(CORNER);
            canvas.rect(pos.getX(), pos.getY() - dimensions.getY() + 14, dimensions.getX(), dimensions.getY());
        } else {
            canvas.rectMode(CENTER);
            canvas.rect(pos.getX(), pos.getY() - 14,dimensions.getX(),dimensions.getY());
        }
        return this;
    }

}