package vanim.root;
import static vanim.planar.*;
import static processing.core.PApplet.*;
import processing.core.*;
import vanim.misc.Color;

import java.util.*;

public abstract class VObject {
    public int frameCountInit;
    public int frameCountBuffer;
    protected PVector pos;
    protected float width;
    protected float height;
    protected PGraphics canvas;
    protected Color color;
    public PApplet processing;
    protected long incrementor = 0;
    protected float mapPower = 1;
    protected List<float[]> original = new ArrayList<>();
    protected List<float[]> coords = new ArrayList<>(); //[x,y]
    protected int coordsSize = 0;
    protected float[] scale = {1,1,1}; //cant hurt to do this amiright?

    //add mapper and points[] ?

    public VObject(PGraphics c, float x, float y, float w, float h, Color color){
        this(null,c,x,y,w,h,color);
    }

    public VObject(PApplet p, PGraphics c, float x, float y, float w, float h, Color color){
        //println("IS NULL? " + p);
        processing = p;
        canvas = c;
        pos = new PVector(x,y);
        width = w;
        height = h;
        this.color = color;
    }


    public VObject(PGraphics c, float x, float y, Color color){
        this(null,c,x,y,0,0,color);
    }

    public VObject(PApplet p,PGraphics c,float x, float y,float s, Color color) {
        this(null,c,x,y,s,s,color);
    }

    public VObject(PApplet p, PGraphics c, float x, float y, float w, float h) {
        this(p,c,x,y,w,h,new Color());
    }

    public VObject(PGraphics c, float x, float y, float size, Color color) {
        this(null,c,x,y,size,size,color);
    }

    public void setWidth(float nw){
        width = nw;
    }

    public void setHeight(float nh){
        height = nh;
    }

    public void setWidthHeight(float nw, float nh){
        width = nw;
        height = nh;
    }

    public void backgroundRect(){ // work on this tom it is now tom
        canvas.noStroke();
        canvas.fill(0,0,0,125); //125
        if (canvas.textAlign == LEFT){
            canvas.rectMode(CORNER);
            canvas.rect(pos.x,pos.y-height + 14,width,height);
        }
        else {
            canvas.rectMode(CENTER);
            canvas.rect(pos.x,pos.y - 14,width,height);
        }

    }

    //rotate, translate, move, a lot more!
    public abstract boolean scale(float... obj); // Scale up the VObject
    public abstract boolean display(Object... obj); // Display the VObject
}