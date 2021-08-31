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
public class DRectangle extends DPolygon{
    Dimension mSize = new Dimension(200,200);
    public DRectangle(){
        this(100,100,200,200);
    }
    public DRectangle(FPoint p,Dimension size){
        this(p.x,p.y,size.width,size.height);
    }
    public DRectangle(int x, int y){
        this(x,y,200,200);
    }
    public DRectangle(int x, int y, int width, int height){
        this((float)x,(float)y,width,height);
    }
    public DRectangle(float x, float y, int width, int height){
        super();
        setLocation(x,y);
        setSize(width,height);
    }
    /**
     * Calculate all points for start point and the size.
     */
    protected void calculate(){
        calculateCenter();
        calculatePoints();
    }

    /**
     * Calculate the center point with the start point and the size.
     */
    protected void calculateCenter(){
        if(mP0==null)
            mP0=new FPoint();
        FPoint start = (FPoint)mPoints.get(0);
        mP0.setX(start.x+(mSize.width/2));
        mP0.setY(start.y+(mSize.height/2));

    }
    /**
     * Calculate the other points from start point and the size.
     */
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
        e.setPoint(-1*(mSize.width/2),-1*(mSize.height/2));
        e = (FPoint)mPoints.get(1);
        e.setPoint((mSize.width/2),-1*(mSize.height/2));
        e = (FPoint)mPoints.get(2);
        e.setPoint((mSize.width/2),(mSize.height/2));
        e = (FPoint)mPoints.get(3);
        e.setPoint(-1*(mSize.width/2),(mSize.height/2));

    }
    @Override
    public void setLocation(float x, float y){
        //super.setLocation(x,y);
        if(mPoints.size()==0)
            mPoints.add(new FPoint(x,y));
        FPoint e = (FPoint) mPoints.get(0);
        e.setPoint(x,y);
        calculate();
    }
    /**
     * Specify the size of the figure
     * @param size
     */
    public void setSize(Dimension size){
        setSize(size.width,size.height);
    }
    /**
     * Specify the size of the figure
     * @param size
     */
    public void setSize(int width, int height){
        if(mSize == null)
            mSize = new Dimension();
        if(mP0==null)
            mP0=new FPoint();
        FPoint start = (FPoint)mPoints.get(0);
        start.x=mP0.x-(mSize.width/2);
        start.y=mP0.y-(mSize.height/2);

        mSize.width=width;
        mSize.height=height;

        calculate();
    }
    /**
     * Return the size of the figure.
     */
     public Dimension setSize(){
         return mSize;
     }


}
