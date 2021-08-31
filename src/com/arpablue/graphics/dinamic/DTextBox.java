/*
 * To change super template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.graphics.dinamic;

import com.arpablue.arpalib.graphics.FPoint;
import com.arpablue.arpalib.graphics.FigureObj;
import com.arpablue.physical.Degree;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import javax.xml.crypto.dsig.Transform;

/**
 *
 * @author Administrator
 */
public class DTextBox extends DRectangle {
    protected String mText="Es una prueba\r\n\tjajajaja\r\nmerd";
    protected boolean mRotate=true;
    public DTextBox(){
        this(100,100,200,200);
    }
    public DTextBox(FPoint p,Dimension size){
        this(p.x,p.y,size.width,size.height);
    }
    public DTextBox(int x, int y){
        super(x,y,200,200);
    }
    public DTextBox(int x, int y, int width, int height){
        this((float)x,(float)y,width,height);
    }
    public DTextBox(float x, float y, int width, int height){
        super(x,y,width,height);
    }
    /**
     * if the conteiner can be rotated.
     */
    public void setRotate(boolean rotate){
        mRotate = true;
    }
    /**
     * return if the container can be rotate.
     */
    public boolean getRotate(){
        return mRotate;
    }
    @Override
    public void draw(){
        super.draw();
        drawText();
    }
    protected void drawText(){
        AffineTransform original=null;
        if(mText==null)
            return;
        if(mGC==null)
            return;
        mGC.setColor(mColorBorder);
        if(mRotate){
            original = mGC.getTransform();
            mGC.translate(mP0.x, mP0.y);
            mGC.rotate(getDegree().getRadians());

        }

        FigureObj.drawTextOnCenter(mGC, mText,0,0);
        if(mRotate){
            mGC.setTransform(original);
        }
        mGC.drawRect(0, 0, 10, 10);


    }
}
