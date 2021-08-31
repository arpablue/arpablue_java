/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.physical;

import com.arpablue.arpalib.graphics.FPoint;

/**
 *
 * @author Administrator
 */
public class Degree {
    public double mRadians;

    public Degree(int f) {
        setDegree(f);
    }
    public Degree(float f) {
        setDegree(f);
    }
    public Degree(double f) {
        setDegree(f);
    }

    public void setDegree(double degree){
        degree%=360.0;
        mRadians = Math.toRadians(degree);
    }
    public void setDegree(float ang){
        setDegree((double)ang);
    }
    public void setDegree(int ang){
        setDegree((double)ang);
    }
    public void setRadians(float rad){
        mRadians=(double)rad;
    }
    public void setRadians(double rad){
        mRadians=rad;
    }
    public void setRadians(int rad){
        mRadians=(double)rad;
    }
    public double getDegree(){
        return Math.toDegrees(mRadians);
    }
    public double getRadians(){
        return mRadians;
    }
    public static double getRadians(FPoint p){

        return Math.toRadians(getDegree(p));
    }
    public static double getDegree(FPoint p){
        //return Math.toDegrees(Math.atan(p.y/p.x));
        double res=0.0;
        if(p.x>=0)
            res = getDegree0ToPi(p);
        else
            res = getDegreePiTo2Pi(p);
        return res;
    }
    public static double getDegree0ToPi(FPoint p){
        double res=0.0;
        if((p.x>0)&&(p.y>=0))
            return Math.toDegrees(Math.atan(p.y/p.x));
        else if((p.x>0)&&(p.y<0))
            return Math.toDegrees(Math.atan(p.y/p.x)+2*Math.PI);
        else if((p.x==0)&&(p.y>0))
            return Math.toDegrees(Math.PI/2);
        else if((p.x==0)&&(p.y<0))
            return Math.toDegrees(3*Math.PI/2);
        //for x<0
        return Math.toDegrees(Math.atan(p.y/p.x)+Math.PI);
    }
    public static double getDegreePiTo2Pi(FPoint p){
        double res=0.0;
        if((p.x<0)&&(p.y>=0))
            return Math.toDegrees(Math.atan(p.y/p.x)+Math.PI);
        else if((p.x<0)&&(p.y<0))
            return Math.toDegrees(Math.atan(p.y/p.x)+Math.PI);
        else if((p.x==0)&&(p.y>0))
            return Math.toDegrees(Math.PI/2);
        else if((p.x==0)&&(p.y<0))
            return Math.toDegrees(-1*Math.PI/2);
        //for x>0
        return Math.toDegrees(Math.atan(p.y/p.x));
    }
    @Override
    public String toString(){
        return this.getDegree()+"°";
    }
    /**
     * return the FPoint from ofigin point (0,0), specified by a degree and a distance.
     */
    public static FPoint getPoint(Degree degree, double distance){
        FPoint res = new FPoint();
        res.x = (float)(distance*Math.cos(degree.getRadians()));
        res.y = (float)(distance*Math.sin(degree.getRadians()));
        return res;
    }
    /**
     * return the FPoint from ofigin point (0,0), specified by a degree and a distance.
     */
    public static FPoint getPoint(double degree, double distance){
        FPoint res = new FPoint();
        res.x = (float)(distance*Math.cos(Math.toRadians(degree)));
        res.y = (float)(distance*Math.sin(Math.toRadians(degree)));
        return res;
    }
}
