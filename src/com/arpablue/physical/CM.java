/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.physical;

import com.arpablue.arpalib.graphics.FPoint;
import com.arpablue.graphics.dinamic.DPolygon;
import com.arpablue.graphics.dinamic.DinamicObj;
import java.util.Vector;

/**
 * This the class for Circular Motion.
 * @author Administrator
 */
public class CM extends UM{



    @Override
    public void apply() {
        setCM(getLocation(), getSpeed(), getDegree(), getSeconds());
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static double setCM(FPoint location, Speed speed,double seconds){
        double degree=Degree.getDegree(location);
        double degreeInc=speed.value*seconds;
        degree+=degreeInc;
        double dist = FPoint.distanceOrigin(location);
        location.setPoint(Degree.getPoint(degree, dist));
        return degreeInc;
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static double setCMMili(FPoint location, Speed speed,double miliseconds){
        miliseconds = Second.fromMiliSeconds(miliseconds);
        double degree=Degree.getDegree(location);
        double degreeInc=speed.value*miliseconds;
        degree+=degreeInc;
        double dist = FPoint.distanceOrigin(location);
        location.setPoint(Degree.getPoint(degree, dist));
        return degreeInc;
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static double setCMNano(FPoint location, Speed speed,double nanoseconds){
        nanoseconds = Second.fromNanoSeconds(nanoseconds);
        double degree=Degree.getDegree(location);
        double degreeInc=speed.value*nanoseconds;
        degree+=degreeInc;
        double dist = FPoint.distanceOrigin(location);
        location.setPoint(Degree.getPoint(degree, dist));
        return degreeInc;
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static  void setCM(FPoint location, Speed speed, Degree degree,double seconds){
        degree.setDegree(degree.getDegree()+speed.value*seconds);
        double dist = FPoint.distanceOrigin(location);
        location.setPoint(Degree.getPoint(degree, dist));
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static  void setCMMili(FPoint location, Speed speed, Degree degree,double miliseconds){
        miliseconds =Second.fromMiliSeconds(miliseconds);
        degree.setDegree(degree.getDegree()+speed.value*miliseconds);
        double dist = FPoint.distanceOrigin(location);
        location.setPoint(Degree.getPoint(degree, dist));
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static  void setCMNano(FPoint location, Speed speed, Degree degree,double nanoseconds){
        nanoseconds =  Second.fromNanoSeconds(nanoseconds);
        degree.setDegree(degree.getDegree()+speed.value*nanoseconds);
        double dist = FPoint.distanceOrigin(location);
        location.setPoint(Degree.getPoint(degree, dist));

    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static void setCM(FPoint location, Speed speed, Degree degree,Second seconds){

        degree.setDegree(degree.getDegree()+speed.value*seconds.getSeconds());
        double dist = FPoint.distanceOrigin(location);
        location.setPoint(Degree.getPoint(degree, dist));
    }
    /**
     * Apply Circular motion to each point of the Polygon.
     */
    public static void setCM(DPolygon p, Speed speed, Degree degree,Second seconds){
        if(p==null)
            return;
        Vector points = p.getPoints();
        FPoint e=null;
        for(int i=0; i<points.size(); i++){
            e=(FPoint)points.get(i);
            setCM(e,speed,degree,seconds);
        }
    }
    /**
     * Apply Circular motion to each point of the Polygon.
     */
    public static double setCM(DPolygon p, Speed speed,double seconds){
        double res = 0.0;
        if(p==null)
            return res;
        Vector points = p.getPoints();
        FPoint e=null;
        for(int i=0; i<points.size(); i++){
            e=(FPoint)points.get(i);
            res=setCM(e,speed,seconds);
        }
        return res;
    }
    /**
     * Apply Circular motion to each point of the Polygon.
     */
    public static double setCMNano(DinamicObj p, Speed speed,double nanoseconds){
        double res=0.0;
        if(p==null)
            return res;
        Vector points = p.getPoints();
        FPoint e=null;
        for(int i=0; i<points.size(); i++){
            e=(FPoint)points.get(i);
            res=setCMNano(e,speed,nanoseconds);
        }
        p.getDegree().setDegree(p.getDegree().getDegree()+res);
        //System.out.println("="+degree.getDegree());
        return res;
    }
    /**
     * Apply Circular motion to each point of the Polygon.
     */
    public static double setCMMili(DPolygon p, Speed speed,double miliseconds){
        double res=0.0;
        if(p==null)
            return res;
        Vector points = p.getPoints();
        FPoint e=null;
        for(int i=0; i<points.size(); i++){
            e=(FPoint)points.get(i);
            res=setCMMili(e,speed,miliseconds);
        }
        return res;
    }
}
