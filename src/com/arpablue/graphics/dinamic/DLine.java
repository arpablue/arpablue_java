/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.graphics.dinamic;

import com.arpablue.arpalib.graphics.FPoint;

/**
 *
 * @author Administrator
 */
public class DLine extends DPolygon{
    @Override
    public void draw() {
       FPoint aux = mP0;
       FPoint e=null;
       mGC.drawOval(mP0.getX()-2, mP0.getY()-2, 5, 5);
       for(int i=1; i<mPoints.size(); i++){
            aux=(FPoint)mPoints.get(i-1);
            e=(FPoint)mPoints.get(i);
            mGC.drawLine(mP0.getX()+aux.getX(), mP0.getY()+aux.getY(), mP0.getX()+e.getX(), mP0.getY()+e.getY());
            aux = e;
       }
    }
}
