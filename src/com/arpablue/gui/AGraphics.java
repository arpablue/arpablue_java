/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.gui;

/**
 *
 * @author 5v
 */
import java.awt.Graphics2D.*;
import java.awt.*;

public class AGraphics
{

    protected Graphics2D g2 = null;
    protected RenderingHints rHints = null;

    public AGraphics(Graphics g)
    {
        if (g == null)
        {
            return;
        }
        g2 = (Graphics2D) g;

        rHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHints(rHints);
    }
}
