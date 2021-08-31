/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.arpalib.graphics;

import com.arpablue.arpalib.graphics.FPoint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author Administrator
 */
public abstract class FigureObj {
    protected Graphics2D mGC;
    protected Color mColor;
    protected FPoint mP0;
    protected boolean mDrawCenter = false;
    public static boolean SHOW_COORDINATE_POINT = false;
    public FigureObj(){
        mP0 = new FPoint(50,50);
        mColor = new Color(0,0,0);
    }
    public void setDrawCenter(boolean drawCenter){
        mDrawCenter = drawCenter;
    }
    public static void drawPoint(FPoint p,Graphics g){
        Color aux = g.getColor();
        g.setColor(Color.red);
        g.drawOval(p.getX()-3, p.getY()-3, 7, 7);
        g.setColor(Color.GREEN);
        g.drawOval(p.getX()-1, p.getY()-1, 3, 3);
        g.setColor(Color.black);
        g.drawLine(p.getX()-3, p.getY(), p.getX()+3, p.getY());
        g.drawLine(p.getX(), p.getY()-3, p.getX(), p.getY()+3);
        if(SHOW_COORDINATE_POINT)
            g.drawString(p.toString(),p.getX()+3,p.getY());
    }
    public boolean isDrawCenter(){return mDrawCenter;}
    protected void drawCenter(){
        if(mGC == null)
            return;
        FigureObj.drawPoint(mP0, mGC);
    }
    public void setPointOrigin(FPoint p){
        mP0=p;
    }
    public void setPointOrigin(int x, int y){
        if(mP0 == null)
            mP0 = new FPoint();
        mP0.x=x;
        mP0.y=y;
    }
    /**
     * Draw the figure object.
     */
    public void paint(Graphics2D g){
        setGraphicContext(g);
        paint();
    }
    /**
     * Draw the figure object.
     */
    public void paint(Graphics g){
        setGraphicContext(g);
        paint();
    }
    /**
     * Draw the figure object.
     */
    public void paint()
    {
        if(mGC == null)
            return;
        Color aux = mGC.getColor();
        mGC.setColor(mColor);
        draw();
        if(this.isDrawCenter())
            drawCenter();
        mGC.setColor(aux);
    }
    /**
     * Draw the figure object.
     */
    public abstract void draw();
    /**
     * @return the Gaphic Context
     */
    public Graphics2D getGraphicContext() {
        return mGC;
    }

    /**
     * @param mGC the Gaphic Context to set
     */
    public void setGraphicContext(Graphics2D g) {
        this.mGC = g;
    }
    /**
     * @param mGC the Gaphic Context to set
     */
    public void setGraphicContext(Graphics g) {
        this.mGC = (Graphics2D)g;
    }
    public void setColor(Color color){
        mColor = color;
        if(mGC!=null)
            mGC.setColor(color);
    }
    public Color getColor(){
        return mColor;
    }
    /**
     * This funtion is to make a hight graphics quality.
     */
    public static void setHightQuality(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
//            RenderingHints rh = new RenderingHints(
//                RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON
//            );
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                    );

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                    );



    }
    /**
     * This funtion is to make a hight graphics quality.
     */
    public static Dimension getFontSize(Graphics g, String text){
        Dimension res = new Dimension(0,0);
        if(g==null)
            return res;
        if(text==null)
            return res;
        // get metrics from the graphics
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // get the height of a line of text in this font and render context
        int hgt = metrics.getHeight();
        // get the advance of my text in this font and render context
        int adv = metrics.stringWidth(text);
        // calculate the size of a box to hold the text with some padding.
        res.setSize(adv, hgt);

        return res;
    }
    /**
     * get size for multiple lines.
     */
    public static Dimension getTextSize(Graphics g, String text){
        Dimension res = new Dimension(0,0);
        if(g==null)
            return res;
        if(text==null)
            return res;
        text = text.replace("\r", "");
        String [] v = text.split("\n");

        // get metrics from the graphics
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // get the height of a line of text in this font and render context
        int height = metrics.getHeight();
        int width = -1;
        int k=0;
        for (int i = 0; i < v.length; i++) {
            String string = v[i];
            height+=i*metrics.getHeight();
            // get the advance of my text in this font and render context
            k = metrics.stringWidth(string);
            if(k>width)
                width=k;
        }
        // calculate the size of a box to hold the text with some padding.
        res.setSize(width, height);

        return res;
    }
    /**
     * Draw a string but each new string is draw in the next line.
     */
    public static void drawText(Graphics g,String text, int x, int y){
        if(g==null)
            return;
        if(text==null)
            return;
        // get metrics from the graphics
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        String []v =text.replace("\r", "").split("\n");
        for (int i = 0; i < v.length; i++) {
            String string = v[i];
            g.drawString(string,
                    x,
                    y+(i*(metrics.getHeight()))
                    );
        }
        
    }
    /**
     * Draw a string but each new string is draw in the next line.
     */
    public static void drawTextOnCenter(Graphics g,String text, int x, int y){
        if(g==null)
            return;
        if(text==null)
            return;
        // get metrics from the graphics
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        Dimension size = getTextSize(g,text);
        String []v =text.replace("\r", "").split("\n");
        x=x-(size.width/2);
        y=y-(size.height/2);
        y=y+metrics.getHeight();
        
        for (int i = 0; i < v.length; i++) {
            String string = v[i];
            g.drawString(string,
                    x,
                    y+(i*(metrics.getHeight()))
                    );
        }

    }

}
