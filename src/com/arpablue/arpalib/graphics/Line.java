/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.arpalib.graphics;

import com.arpablue.arpalib.graphics.FigureObj;
import com.arpablue.arpalib.graphics.FPoint;


/**
 *
 * @author Administrator
 */
public class Line extends FigureObj{
    protected FPoint mP1;

    public Line(){
        super();
        mP1 = new FPoint(100,100);
    }
    //----------setters------------------
    /**
     * Specify the origin FPoint.
     * @param FPoint
     */
    public void setFPointOrigin(FPoint FPoint){
        mP0 = FPoint;
    }
    public void setFPointDestiny(FPoint FPoint){
        mP0 = FPoint;
    }
    public void draw(){
        mGC.drawLine(mP0.getX(), mP0.getY(), mP1.getX(), mP1.getY());
    }
}
