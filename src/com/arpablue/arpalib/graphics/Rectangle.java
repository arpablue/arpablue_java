/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.arpalib.graphics;

import com.arpablue.arpalib.graphics.FigureObj;
import java.awt.Dimension;

/**
 *
 * @author Administrator
 */
public class Rectangle extends FigureObj{
    Dimension mSize;
    boolean mFill = false;
    public Rectangle(){
    
        super();
        mSize = new Dimension(100,100);
        mFill = false;
    }
    /**
     * Draw lines of the border.
     */
    public void drawBorder(){
        mGC.drawRect(mP0.getX(), mP0.getY(), mSize.width, mSize.height);
    }
    /**
     * Draw only the fill.
     */
    public void drawFill(){
        mGC.fillRect(mP0.getX(), mP0.getY(), mSize.width, mSize.height);
    }
    @Override
    public void draw() {
        if(isFill()){
            drawFill();
            return;
        }
        drawBorder();
    }
    /**
     * Especify if draw if draw the border or the fill.
     * @param fill
     */
    public void setFill(boolean fill){
        mFill = fill;
    }
    /**
     * return the type of the draw.
     * @return
     */
    public boolean isFill(){
        return mFill;
    }
    /**
     * Specify the size of the figure
     * @param size
     */
    public void setSize(Dimension size){
        mSize = size;
    }
    /**
     * Specify the size of the figure
     * @param size
     */
    public void setSize(int width, int height){
        if(mSize == null)
            mSize = new Dimension();
        mSize.width=width;
        mSize.height=height;
    }
    /**
     * Return the size of the figure.
     */
     public Dimension setSize(){
         return mSize;
     }
}
