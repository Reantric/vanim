package vanim.root;
import static vanim.planar.*;

import vanim.Planes.Plane;
import vanim.misc.Color;
import vanim.storage.Vector;

public abstract class VObject extends CanvasObject{

    protected Color color;
    protected long incrementor = 0;
    protected float mapPower = 1;
    protected int coordsSize = 0;

    //add mapper and points[] ?
    public VObject(Plane p, Vector<Float> pos, Vector<Float> dimensions, Color color){ // Plane constructor!
        super(p.getProcessingInstance(),p.getCanvas(),pos,dimensions);
        pos.multiplyAll(relScale.getScaleX(),relScale.getScaleY()); //just PVec(x,y) works!
        dimensions.multiplyAll(relScale.getScaleX(),relScale.getScaleY());
        this.color = color;
    }

    public VObject(Plane p, Vector<Float> pos, Color color){
        this(p,pos, new Vector<>(),color);
    }

    public VObject(Plane p, Vector<Float> pos,float s, Color color) {
        this(p,pos,new Vector<>(s,s,s),color);
    }

    public VObject(Plane p, Vector<Float> pos, Vector<Float> dimensions) {
        this(p,pos,dimensions,new Color());
    }

    public void setWidth(float nw){
        dimensions.setX(nw);
    }

    public void setHeight(float nh){
        dimensions.setY(nh);
    }

    public void setWidthHeight(float nw, float nh){
        dimensions.setX(nw);
        dimensions.setY(nh);
    }

    public void backgroundRect(){ // work on this tom it is now tom
        canvas.noStroke();
        canvas.fill(0,0,0,125); //125
        if (canvas.textAlign == LEFT){
            canvas.rectMode(CORNER);
            canvas.rect(pos.getX(),pos.getY()-dimensions.getY() + 14,dimensions.getX(),dimensions.getY());
        }
        else {
            canvas.rectMode(CENTER);
            canvas.rect(pos.getX(),pos.getY() - 14,dimensions.getX(),dimensions.getY());
        }

    }

    //rotate, translate, move, a lot more!
    public abstract boolean scale(float... obj); // Scale up the VObject
}