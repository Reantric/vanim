package vanim.text;

import processing.core.PShape;
import vanim.planes.Plane;
import vanim.storage.Color;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;
import vanim.util.Reason;
import vanim.util.tex.SVGConverter;

public class ImmutableLaTeX extends Text {
    PShape latex;
    FVector originalScale;
    static long livingEntities = 0;
    long ID;
    SVGConverter converter;

    /**
     * @param p             Plane that is to be drawn on
     * @param latex         String to be displayed
     * @param pos           The position of the object on the canvas (in absolute coordinates)
     * @param tSize         The size of the text (can be omitted)
     * @param color         The color of the object, in HSB
     * @param reasonCreated The reason this object was created
     */
    public ImmutableLaTeX(Plane p, String latex, FVector pos, float tSize, Color color, Reason reasonCreated) {
        super(p, latex, pos, tSize, color, reasonCreated);
        // File[] files = new File(".\\temp").listFiles();
        //  if () { TODO: later
        ImmutableLaTeX.livingEntities++;
        this.ID = livingEntities;
        this.color = color;
        converter = new SVGConverter(color); // TODO: Modify Later
        System.out.println("hello there m9!");
        converter.write(latex, ".\\temp\\" + this.ID + ".svg", tSize);
        this.latex = p.getProcessingInstance().loadShape(".\\temp\\" + this.ID + ".svg").getChild("eq");
        this.latex.disableStyle();
        originalScale = new FVector(this.latex.getWidth(), this.latex.getHeight());
        //     }
    }

    public ImmutableLaTeX(Plane p, String latex, FVector pos, float size) {
        this(p, latex, pos, size, new Color(0, 0, 255), Reason.USER_CREATED);
    }

    public ImmutableLaTeX(Plane p, String latex, FVector pos, float size, Color color) {
        this(p, latex, pos, size, color, Reason.USER_CREATED);
    }

    @Override
    public ImmutableLaTeX scale(Scale s) {
        super.scale(s);
        // convert absolute Scale to relative scale + planeScale (latex object pshape)
        latex.scale(originalScale.getX() * scale.getX() / latex.getWidth(), originalScale.getY() * scale.getY() / latex.getHeight());
        return this;
    }

    @Override
    public ImmutableLaTeX scale(float s) {
        super.scale(s);
        latex.scale(originalScale.getX() * scale.getX() / latex.getWidth(), originalScale.getY() * scale.getY() / latex.getHeight());
        return this;
    }

    @Override
    public boolean display(Object... obj) {
        processing.fill(color);
        processing.noStroke();
        processing.shape(latex, pos);
        return true;
    }
}
