package vanim.mfunc;

import vanim.core.Graphics2D;
import vanim.root.CanvasObject;
import vanim.storage.vector.FVector;
import vanim.text.Text;

import java.util.ArrayList;
import java.util.List;

import static vanim.planar.LEFT;
import static vanim.planar.println;

public class Narrator extends CanvasObject {
    List<Text> allText = new ArrayList<>();

    public Narrator(Graphics2D c) {
        super(c, new FVector(0, 0), new FVector());
        setupNarrator();
    }


    public void setupNarrator(){
      //  allText.add(new Text(new TextBuilder(plane, "But how do we find the slope of the tangent line?", new FVector(-630, -480), 80, new Color(30))));
        //  allText.add(new TextVObject(canvas,"Vanim",-660,-320,130,77));
    }


    public boolean narration() {
        boolean b = true;
        canvas.textAlign(LEFT);
        canvas.textSize(70);
        for (Text m : allText) {
            m.setDisplayRect(false);
            if (!m.display())
                b = false;
        }

        return b;
    }

    public boolean afterNarration() {
        return true;
    }

    public boolean narrate() {
        narration();
        afterNarration();
        return narration() & afterNarration();
    }

    public void test() {
        println("I exist!");
    }

    /**
     * @param obj Varargs to display the object at coordinates
     * @return If the operation was a success
     */
    @Override
    public boolean display(Object... obj) {
        return false;
    }
}