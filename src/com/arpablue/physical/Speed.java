/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.physical;

import com.arpablue.arpalib.graphics.FPoint;
import com.arpablue.tools.XTime;

/**
 *
 * @author Administrator
 */
public class Speed extends Acceleration {

    public Speed() {
        super();
    }
    public Speed(int f) {
        super(f);
    }
    public Speed(float f) {
        super(f);
    }
    public Speed(double f) {
        super(f);
    }
    @Override
    public String toString(){
        return getIntValue()+"m/t";
    }
    public double getSpeedX(double degree){
        return value*Math.cos(value);
    }
    /**
     * Apply a acceleration to the current value with a specific miliseconds time.
     * @param a
     * @param miliSeconds
     */
    public void applyAccelerationMili(Acceleration a, double miliSeconds){

         value = value + XTime.miliSecondsToSeconds(miliSeconds)*a.value;
    }
    /**
     * Apply a acceleration to the current value with a specific miliseconds time.
     * @param a
     * @param miliSeconds
     */
    public void applyAccelerationMili(double acceleration, double miliSeconds){
         value = value + XTime.miliSecondsToSeconds(miliSeconds)*acceleration;
    }
    /**
     * Apply a acceleration to the current value with a specific miliseconds time.
     * @param a
     * @param miliSeconds
     */
    public void applyAccelerationNano(Acceleration a, double nanoSeconds){

         value = value + XTime.nanoSecondsToSeconds(nanoSeconds)*a.value;
    }
    /**
     * Apply a acceleration to the current value with a specific miliseconds time.
     * @param a
     * @param miliSeconds
     */
    public void applyAccelerationNano(double acceleration, double nanoSeconds){
         value = value + XTime.nanoSecondsToSeconds(nanoSeconds)*acceleration;
    }
    /**
     * Apply a acceleration to the current value with a specific miliseconds time.
     * @param a
     * @param miliSeconds
     */
    public void applyAcceleration(Acceleration a, double seconds){

         value = value + seconds*a.value;
    }
    /**
     * Apply a acceleration to the current value with a specific miliseconds time.
     * @param a
     * @param miliSeconds
     */
    public void applyAcceleration(double acceleration, double seconds){
         value = value + seconds*acceleration;
    }
   /**
    * return the velocity in the axe X
    */
    public double getVx(Degree d){
        return value * Math.cos(d.getRadians());
    }
   /**
    * return the velocity in the axe Y
    */
    public double getVy(Degree d){
        return value * Math.sin(d.getRadians());
    }
}
