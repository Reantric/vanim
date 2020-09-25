package vanim.text;

import processing.core.PShape;
import vanim.root.builder.TextBuilder;
import vanim.storage.Scale;
import vanim.storage.vector.FVector;
import vanim.util.tex.SVGConverter;

public class ImmutableLaTeX extends Text {
    PShape latex;
    FVector originalScale;
    static long livingEntities = 0;
    long ID;
    SVGConverter converter;

    /**
     *
     */
    public ImmutableLaTeX(TextBuilder builder) {
        super(builder);
        // File[] files = new File(".\\temp").listFiles();
        //  if () { TODO: later
        ImmutableLaTeX.livingEntities++;
        this.ID = livingEntities;
        this.color = color;
        converter = new SVGConverter(color); // TODO: Modify Later
        System.out.println("hello there m9!");
        converter.write(str, ".\\temp\\" + this.ID + ".svg", tSize);
        this.latex = plane.getProcessingInstance().loadShape(".\\temp\\" + this.ID + ".svg").getChild("eq");
        this.latex.disableStyle();
        originalScale = new FVector(this.latex.getWidth(), this.latex.getHeight());
        //     }
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
