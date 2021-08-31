/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.physical;

/**
 * this class is for Uniformal Motion.
 * @author Administrator
 */
public abstract class UM extends Motion{
    protected Speed mSpeed;
    protected Degree mDegree;
    protected Second mSeconds;

    /**
     * @return the mSpeed
     */
    public Speed getSpeed() {
        return mSpeed;
    }

    /**
     * @param speed the mSpeed to set
     */
    public void setSpeed(Speed speed) {
        this.mSpeed = speed;
    }

    /**
     * @return the mDegree
     */
    public Degree getDegree() {
        return mDegree;
    }

    /**
     * @param degree the mDegree to set
     */
    public void setDegree(Degree degree) {
        this.mDegree = degree;
    }

    /**
     * @return the mSeconds
     */
    public Second getSeconds() {
        return mSeconds;
    }

    /**
     * @param seconds the mSeconds to set
     */
    public void setSeconds(Second seconds) {
        this.mSeconds = seconds;
    }
}
