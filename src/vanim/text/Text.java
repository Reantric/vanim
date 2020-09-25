package vanim.text;

import vanim.root.builder.TextBuilder;
import vanim.root.vobjects.AbsoluteVObject;
import vanim.storage.Scale;

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

    public Text(TextBuilder builder) {
        super(builder);
        str = builder.getText();
        this.tSize = builder.getTextSize();
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