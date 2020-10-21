package vanim.geometry.twodim;

import vanim.geometry.GeometricSpace;
import vanim.root.builder.GeometricSpaceBuilder;
import vanim.storage.color.Color;
import vanim.storage.color.ColorType;
import vanim.text.Text;

public abstract class Grid extends GeometricSpace {
    protected Color textColor;

    /**
     * @param builder The VObject builder needed to build this VObject
     *                Contains Plane p, FVector pos, FVector dimensions, Color color, Reason reasonCreated
     */
    public Grid(GeometricSpaceBuilder builder) {
        super(builder);
        textColor = new Color(ColorType.WHITE);
    }

    /**
     * @return The color of the text marking the axes
     */
    public Color getTextColor() {
        return this.textColor;
    }

    /**
     * Add text to Map<Text,Boolean> where Text is the text to be added
     * and Boolean is whether it has displayed (doesn't call display)
     *
     * @param text The text to be added
     * @return If that text object has been displayed
     */
    public boolean displayText(Text text) {
        texts.putIfAbsent(text, false);
        return texts.get(text);
    }
}
