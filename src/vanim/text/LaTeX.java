package vanim.text;

import org.scilab.forge.jlatexmath.DefaultTeXFont;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration;
import org.scilab.forge.jlatexmath.greek.GreekRegistration;
import processing.core.PImage;
import vanim.planes.Plane;
import vanim.storage.Color;
import vanim.storage.vector.FVector;
import vanim.util.Reason;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LaTeX extends Text {
    BufferedImage image;
    JPanel jl = new JPanel();
    TeXFormula formula;
    TeXIcon icon;

    /**
     * @param p             Plane that is to be drawn on
     * @param latex         String to be displayed
     * @param pos           The position of the object on the canvas (in absolute coordinates)
     * @param tSize         The size of the text (can be omitted)
     * @param color         The color of the object, in HSB
     * @param reasonCreated The reason this object was created
     */
    public LaTeX(Plane p, String latex, FVector pos, float tSize, Color color, Reason reasonCreated) {
        super(p, latex, pos, tSize, color, reasonCreated);
        this.formula = new TeXFormula(latex);
        this.icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, tSize);
        this.setLaTeX(latex);
        doImage();
        // Now we have a BufferedImage that we can modify!
    }

    public LaTeX(Plane plane, String latex, FVector pos, float tSize, Color color) {
        this(plane, latex, pos, tSize, color, Reason.USER_CREATED);
    }

    public LaTeX setLaTeX(String latex) {
        DefaultTeXFont.registerAlphabet(new CyrillicRegistration());
        DefaultTeXFont.registerAlphabet(new GreekRegistration());
        this.formula = new TeXFormula(latex);
        this.icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, tSize);
        return this;
    }

    private void doImage() {
        image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        icon.setInsets(new Insets(5, 5, 5, 5));
        Graphics g2 = image.getGraphics();
        java.awt.Color javColor = color.toJavaRGB();
        g2.setColor(javColor);
        jl.setForeground(javColor);
        icon.paintIcon(jl, g2, 0, 0);
    }

    //   @Override
    //   public LaTeX scale(float s){
    //       super.scale(s);
    //       image.scale(originalScale.getX() * scale.getX() / latex.getWidth(), originalScale.getY() * scale.getY() / latex.getHeight());
    //       return this;
    // //      image.
    //   }

    @Override
    public boolean display(Object... obj) {
        doImage();
        processing.tint(255, color.getAlpha().getValue());
        processing.image(new PImage(image), pos);
        processing.noTint();
        return true;
    }
}
