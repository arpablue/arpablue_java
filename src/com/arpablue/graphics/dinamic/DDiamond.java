/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.graphics.dinamic;

import com.arpablue.arpalib.graphics.FPoint;
import java.awt.Dimension;

/**
 *
 * @author Administrator
 */
public class DDiamond extends DRectangle{
    public DDiamond(){
        super(100,100,200,200);
    }
    public DDiamond(FPoint p,Dimension size){
        super(p.x,p.y,size.width,size.height);
    }
    public DDiamond(int x, int y){
        super(x,y,200,200);
    }
    public DDiamond(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public DDiamond(float x, float y, int width, int height){
        super(x,y,width,height);
    }
    /**
     * Calculate the other points from start point and the size.
     */
    @Override
    protected void calculatePoints(){
        FPoint e=null;
        FPoint start = (FPoint)mPoints.get(0);
        if(mPoints.size()<4){
            mPoints.removeAllElements();
            mPoints.add(start);
            for(int i=0;i<3;i++)
                mPoints.add(new FPoint());

        }
        e = (FPoint)mPoints.get(0);
        e.setPoint(0,-1*(mSize.height/2));
        e = (FPoint)mPoints.get(1);
        e.setPoint((mSize.width/2),0);
        e = (FPoint)mPoints.get(2);
        e.setPoint(0,(mSize.height/2));
        e = (FPoint)mPoints.get(3);
        e.setPoint(-1*(mSize.width/2),0);

    }

}
