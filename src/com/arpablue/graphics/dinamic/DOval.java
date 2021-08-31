/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.graphics.dinamic;

/**
 *
 * @author Administrator
 */
public class DOval extends DRectangle{
    /**
     * Draw lines of the border.
     */
    @Override
    public void drawBorder(){
        mGC.drawOval(mP0.getX(), mP0.getY(), mSize.width, mSize.height);
    }
    /**
     * Draw only the fill.
     */
    @Override
    public void drawFill(){
        mGC.fillOval(mP0.getX(), mP0.getY(), mSize.width, mSize.height);
    }

}
