/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.arpalib.graphics;

import com.arpablue.arpalib.graphics.FigureObj;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class Lienzo extends Canvas implements ComponentListener{
    protected Image mImgBase;
    protected int mTypeImg = BufferedImage.TYPE_INT_RGB;
    protected Graphics2D mGC;
    protected Vector mFigures;
    protected boolean mHQ=true;
    public Lienzo(){
        super();
        initStart();
        this.addComponentListener(this);
    }
    /**
     * set the graphics context in Hight Quality.
     *
     */
    public void setHightQuality(boolean q){
        mHQ=q;
    }
    /**
     * return true if the context graphics is applied.
     * @return
     */
    public boolean isHightQuality(){
        return mHQ;
    }
    public void add(FigureObj fig){
        mFigures.add(fig);
    }
    public void remove(FigureObj fig){
        mFigures.remove(fig);
    }
    public void removeAllElements(){
        mFigures.removeAllElements();
    }
    /**
     * Initialize the dimension and locati=on of the object
     **/
    protected void initStart(){
        mFigures = new Vector();
        this.setSize(400,400);
        this.setLocation(20, 20);
        this.setBackground(Color.white);
    }
    /**
     * create a new image for the Lienzo,
     * @param width
     * @param height
     */
    protected void initImage(int width, int height){
        mImgBase = new BufferedImage(width,height,mTypeImg);
        mGC = (Graphics2D)mImgBase.getGraphics();
        clear();
    }
    protected void clear(){
        mGC.setColor(this.getBackground());
        mGC.fillRect(0, 0, this.getWidth(), this.getWidth());
    }
    protected void draw(){
        if(mGC==null)
            return;
        FigureObj e = null;
        clear();
        if(mHQ)
            FigureObj.setHightQuality(mGC);
        for(int i=0; i<mFigures.size(); i++){
            e = (FigureObj)mFigures.get(i);
            e.paint(mGC);
        }
    }
    //--------------------getters------------------
    public Graphics2D getGraphicsContext(){
        return mGC;
    }
    //----Overrides----------------------------------------------------------
    @Override
    public void update(Graphics g){
        paint(g);
    }
    @Override
    public void paint(Graphics g){
        draw();
        g.drawImage(mImgBase, 0, 0, this);

    }

    //------Implementations--------------------
    /**
     * Related with the change of the dimension of the component.
     * @param e
     */
    public void componentResized(ComponentEvent e) {
        initImage(e.getComponent().getWidth(),e.getComponent().getHeight());

    }

    public void componentMoved(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {}

    public void componentHidden(ComponentEvent e) {}
}
