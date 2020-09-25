package vanim.text;

import org.scilab.forge.jlatexmath.DefaultTeXFont;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration;
import org.scilab.forge.jlatexmath.greek.GreekRegistration;
import processing.core.PImage;
import vanim.root.builder.TextBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LaTeX extends Text {
    BufferedImage image;
    JPanel jl = new JPanel();
    TeXFormula formula;
    TeXIcon icon;

    public LaTeX(TextBuilder builder) {
        super(builder);
        this.formula = new TeXFormula(str);
        this.icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, tSize);
        this.setLaTeX(str);
        doImage();
        // Now we have a BufferedImage that we can modify!
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
