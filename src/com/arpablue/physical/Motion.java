/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.physical;

import com.arpablue.arpalib.graphics.FPoint;
import java.awt.Point;

/**
 *
 * @author Administrator
 */
public abstract class Motion {
    protected FPoint mLocation;
    public Motion(){
            mLocation = new FPoint(50,50);
    }
    public Motion(FPoint p){
        mLocation = p;
    }
    public Motion(Point p){
        mLocation=new FPoint(p);
    }

    public abstract void apply();

    /**
     * @return the mLocation
     */
    public FPoint getLocation() {
        return mLocation;
    }

    /**
     * @param mLocation the mLocation to set
     */
    public void setLocation(FPoint mLocation) {
        this.mLocation = mLocation;
    }
}
