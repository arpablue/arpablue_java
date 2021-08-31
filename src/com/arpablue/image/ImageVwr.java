/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author augusto
 */
public class ImageVwr extends Canvas{
    BufferedImage mImg=null;
    protected Color mBorderColor = Color.BLACK;
    protected Point mPosImg = new Point(1,1);
    
    @Override
    public void update(Graphics g)
    {
        paint(g);
    }
    @Override
    public void paint(Graphics g)
    {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        if(mImg!=null)
            g2.drawImage(mImg, mPosImg.x,mPosImg.y, this);
        if(mBorderColor!=null)
        {
            g2.setColor(mBorderColor);
            g2.drawRect(0,0,this.getSize().width-1,this.getSize().height-1);
        }
        
    }
    /////////////SEETERTS AND GETTERS
    
    public void setBorderColor(Color c){ mBorderColor = c; }
    public Color getBorderColor() { return mBorderColor; }
    public void setImage(ImageX img){ mImg = img.getImage(); }
    public void setImage(BufferedImage img){ mImg = img; }
    public BufferedImage getImage(){ return mImg; }
    public Point getImageLocation(){ return mPosImg;}
    public void setImageLocation(Point location){ 
        mPosImg = location; 
        if(mPosImg == null)
        {
            mPosImg = new Point(1,1);
        }
    }
    public void setImageLocation(int x, int y){ mPosImg.setLocation(x, y); }
            
    
}
