package vanim.root.builder;

import vanim.geometry.GeometricSpace;
import vanim.geometry.twodim.Grid;
import vanim.storage.color.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

public class TextBuilder extends VObjectBuilder {
    String str;
    float tSize;

    /**
     * @param g             Grid/Geo space that is to be drawn on
     * @param s             String to be displayed
     * @param pos           The position of the object on the canvas (in absolute coordinates)
     * @param tSize         The size of the text (can be omitted)
     * @param color         The color of the object, in HSB
     * @param reasonCreated The reason this object was created
     */
    public TextBuilder(GeometricSpace g, String s, FVector pos, float tSize, Color color, Reason reasonCreated) {
        super(g, pos, color, reasonCreated);
        this.color = color;
        str = s;
        this.tSize = tSize;
        g.getCanvas().textSize(this.tSize);
        dimensions.setXY(g.getCanvas().textWidth(str), this.tSize);
    }

    public TextBuilder(Grid p, String s, FVector pos, Color color, Reason reasonCreated) {
        this(p, s, pos, p.getCanvas().textSize, color, reasonCreated);
    }

    public TextBuilder(Grid p, String s, FVector pos, float tSize, Color color) {
        this(p, s, pos, tSize, color, Reason.USER_CREATED);
    }

    public TextBuilder(GeometricSpace g, String s, FVector pos, float tSize, Color color) {
        this(g, s, pos, tSize, color, Reason.USER_CREATED);
    }


    public String getText() {
        return str;
    }

    public float getTextSize() {
        return tSize;
    }
}
