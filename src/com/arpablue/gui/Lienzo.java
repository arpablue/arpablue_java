/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.gui;

/**
 *
 * @author 5v
 */
import java.awt.Canvas;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics2D.*;
import java.awt.Graphics.*;

public class Lienzo extends Canvas implements MouseListener, MouseMotionListener
{

    protected Graphics2D g2 = null;
    protected RenderingHints rHints = null;

    public Lienzo()
    {
        super();
        this.setBackground(java.awt.Color.WHITE);
        this.setSize(200, 200);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    protected void quality(Graphics g)
    {
        if (g2 == null)
        {
            g2 = (Graphics2D) this.getGraphics();

            rHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            rHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            g2.setRenderingHints(rHints);
        }
    }
    @Override
    public final void update(Graphics g)
    {
        paint(g);
    }
    @Override
    public final void paint(Graphics g)
    {
        quality(g);
        paint2(g);
    }
    public void paint2(Graphics g)
    {
    }

    public void mouseExited(MouseEvent me)
    {
    }

    public void mouseEntered(MouseEvent me)
    {
    }

    public void mousePressed(MouseEvent me)
    {
    }

    public void mouseReleased(MouseEvent me)
    {
    }

    public void mouseClicked(MouseEvent me)
    {
    }

    public void mouseMoved(MouseEvent me)
    {
    }

    public void mouseDragged(MouseEvent me)
    {
    }
}
