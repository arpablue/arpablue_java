/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.physical;

/**
 *
 * @author Administrator
 */
public class Second {
    protected double mSeconds;


    public void setSeconds(double seconds){  mSeconds = seconds;  }
    public void setMiliSeconds(double miliseconds){  mSeconds = fromMiliSeconds(miliseconds);  }
    public void setNanoSeconds(double nanoseconds){  mSeconds = fromNanoSeconds(nanoseconds);  }
    public void setMinutes(double minutes){  mSeconds = minutes*60;  }

    public double getSeconds(){ return mSeconds;}
    public double getMiliSeconds(){ return mSeconds*1000.0;}
    public double getNanoSeconds(){ return mSeconds*1000000000.0;}
    public double getMinutes(){ return mSeconds/60.0;}
    public static double fromNanoSeconds(double nanoseconds){ return nanoseconds/1000000000.0;}
    public static double fromMiliSeconds(double miliseconds){ return miliseconds/1000.0;}
}
