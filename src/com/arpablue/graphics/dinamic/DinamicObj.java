/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.graphics.dinamic;

import com.arpablue.arpalib.graphics.FPoint;
import com.arpablue.arpalib.graphics.FigureObj;
import com.arpablue.physical.CM;
import com.arpablue.physical.Degree;
import com.arpablue.physical.Speed;
import java.awt.Point;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public abstract class DinamicObj extends FigureObj{
    public Speed mSpeed;
    public Degree mDegree;
    public Vector mPoints;



    public DinamicObj(){
        mSpeed = new Speed(10.0f);
        mDegree = new Degree(0.0f);
    }
    /**
     * Return all points of the Polygon.
     */
    public Vector getPoints(){
        return mPoints;
    }
    /**
     * Cambia la situacion del poligono.
     */
    public void setLocation(float x, float y){
        if(mP0==null){
            mP0=new FPoint(x,y);
            return;
        }
        mP0.x = x;
        mP0.y = y;
    }
    /**
     * Cambia la situacion del poligono a partir de un punto.
     */
    public void setLocation(FPoint target){
        setLocation(target.x,target.y);
    }
    /**
     * Cambia la situacion del poligono a partir de un punto.
     */
    public void setLocation(Point target){
        setLocation((float)target.x,(float)target.y);
    }

    public void action(long lastRefresh){
        CM.setCMNano(this, getSpeed(), (double)lastRefresh);
    }
    @Override
    public abstract void draw();

    /**
     * @return the mSpeed
     */
    public Speed getSpeed() {
        return mSpeed;
    }

    /**
     * @param mSpeed the mSpeed to set
     */
    public void setSpeed(Speed mSpeed) {
        this.mSpeed = mSpeed;
    }

    /**
     * @return the mDegree
     */
    public Degree getDegree() {
        return mDegree;
    }

    /**
     * @param mDegree the mDegree to set
     */
    public void setDegree(Degree mDegree) {
        this.mDegree = mDegree;
    }

}
