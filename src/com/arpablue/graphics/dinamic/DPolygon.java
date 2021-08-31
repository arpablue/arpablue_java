/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.graphics.dinamic;

import com.arpablue.arpalib.graphics.FPoint;
import com.arpablue.arpalib.graphics.FigureObj;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

/**
 *
 * @author ROMMEL
 */
public class DPolygon extends DinamicObj {
    protected Color mColorBorder;
    protected boolean mFill=true;
    protected boolean mBorder=true;
    public DPolygon(){
        this(new Vector());
        setFill(true);
        setBorder(true);
        
    }
    public DPolygon(Vector points){
        super();
        mPoints = points;
        mColorBorder = mColor;
        mColor=new Color(0,255,0);
    }
    /**
     * Specify the color to draw the border of the figure.
     */
    public void setBorderColor(Color c){
        mColorBorder = c;
    }
    /**
     * return the current border color.
     * @return
     */
    public Color getColorBorder(){
        return mColorBorder;
    }
    /**
     * Add a new point to the Polygon.
     */
    public void addPoint(float x, float y){
        mPoints.add(new FPoint(x,y));
    }
    /**
     * Add a new point to the Polygon.
     */
    public void addPoint(int x, int y){
        mPoints.add(new FPoint(x,y));
    }
    /**
     * Add a new point to the Polygon.
     */
    public void addPoint(FPoint target){
        mPoints.add(new FPoint(target));
    }
    /**
     * Add a new point to the Polygon.
     */
    public void addPoint(Point target){
        mPoints.add(new FPoint(target));
    }
    /**
     * Specify if the object will be draw the border or the fill
     */
    public void setFill(boolean fill){
        this.mFill = fill;
    }
    /**
     * Return if the Object is draw the fill or the border.
     * @return
     */
    public boolean isFill(){
        return this.mFill;
    }
    /**
     * Specify if the object will be draw the border or the border.
     */
    public void setBorder(boolean border){
        this.mBorder = border;
    }
    /**
     * Return if the Object is draw the fill or the border.
     * @return
     */
    public boolean isBorder(){
        return this.mBorder;
    }
    /**
     * draw all polygon points.
     */
    protected void drawPoints(){
        FPoint e=null;
        FPoint aux=new FPoint();
        for(int i=0; i<mPoints.size(); i++){
            e = (FPoint) mPoints.get(i);
            aux.x=mP0.x+e.x;
            aux.y=mP0.y+e.y;
            FigureObj.drawPoint(aux, mGC);
        }
    }
    /**
     * remove a all points.
     */
    public void removeAllPoints(){
        mPoints.removeAllElements();
    }
    /**
     * remove a specyfic point.
     * @param p
     */
    public void removePoint(FPoint p){
        mPoints.remove(p);
    }
    /**
     * remove a specyfic point.
     * @param p
     */
    public void removePoint(int i){
        mPoints.remove(i);
    }
    public int [][] getPointsArray(){
        int [][] res=null;
        if(mPoints == null)
            return res;
        res = new int[2][mPoints.size()];
        FPoint e=null;
        for(int i=0; i<mPoints.size(); i++){
            e = (FPoint) mPoints.get(i);
            res[0][i]=(int) (mP0.x + e.x);
            res[1][i]=(int) (mP0.y + e.y);
        }
        return res;
    }
    @Override
    public void draw() {
        if(this.isFill())
            drawFill();
        if(this.isBorder())
            drawBorder();
        if(this.isDrawCenter())
            drawPoints();
    }
    public void drawBorder(){
        mGC.setColor(mColorBorder);
        int [][]xy = getPointsArray();
        mGC.drawPolygon(xy[0],xy[1],xy[0].length);
    }
    public void drawFill(){
        mGC.setColor(mColor);
        int [][]xy = getPointsArray();
        mGC.fillPolygon(xy[0],xy[1],xy[0].length);
    }

}
