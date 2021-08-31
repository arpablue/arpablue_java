/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.BorderLayout;
import java.awt.Component;

/**
 *
 * @author Administrator
 */
public class BorderPanel extends NullPanel
{
    public final static String RIGHT = BorderLayout.EAST;
    public final static String LEFT = BorderLayout.WEST;
    public final static String TOP = BorderLayout.NORTH;
    public final static String BOTTOM = BorderLayout.SOUTH;
    public final static String CENTER = BorderLayout.CENTER;
    public BorderPanel()
    {
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
    }
    public void addNorth(Component comp)
    {
        this.add(comp,BorderLayout.NORTH);
    }
    public void addTop(Component comp)
    {
        this.add(comp,BorderLayout.NORTH);
    }

    public void addSouth(Component comp)
    {
        this.add(comp,BorderLayout.SOUTH);
    }
    public void addDown(Component comp)
    {
        this.add(comp,BorderLayout.SOUTH);
    }

    public void addEast(Component comp)
    {
        this.add(comp,BorderLayout.EAST);
    }
    public void addRight(Component comp)
    {
        this.add(comp,BorderLayout.EAST);
    }

    public void addWest(Component comp)
    {
        this.add(comp,BorderLayout.WEST);
    }
    public void addLeft(Component comp)
    {
        this.add(comp,BorderLayout.WEST);
    }

    public void addCenter(Component comp)
    {
        this.add(comp,BorderLayout.CENTER);
    }
}
