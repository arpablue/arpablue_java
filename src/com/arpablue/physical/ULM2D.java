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
public class ULM2D extends UM{

    public void apply() {
        setULM(getLocation(), getSpeed(), getDegree(), getSeconds());
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static  void setULM(FPoint location, Speed speed, Degree degree,double seconds){
        location.x = (float) (location.x + speed.getVx(degree)*seconds);
        location.y = (float) (location.y + speed.getVy(degree)*seconds);
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static  void setULMMili(FPoint location, Speed speed, Degree degree,double miliseconds){
        miliseconds *= 1000.0;
        location.x = (float) (location.x + speed.getVx(degree)*miliseconds);
        location.y = (float) (location.y + speed.getVy(degree)*miliseconds);
    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static  void setULMNano(FPoint location, Speed speed, Degree degree,double nanoseconds){
        nanoseconds =  nanoseconds/1000000000.0;
        double inc = speed.getVx(degree)*nanoseconds;
        location.x =  (float) (location.x + inc);
        inc = speed.getVy(degree)*nanoseconds;
        location.y = (float) (location.y + inc);

    }
    /**
     * Apply the Uniform Linear Motion to the position.
     * @param location
     * @param speed
     * @param degree
     */
    public static void setULM(FPoint location, Speed speed, Degree degree,Second seconds){
        double inc = speed.getVx(degree)*seconds.getSeconds();
        location.x =  (float) (location.x + inc);
        inc = speed.getVy(degree)*seconds.getSeconds();
        location.y =  (float) (location.y + inc);
    }

}
