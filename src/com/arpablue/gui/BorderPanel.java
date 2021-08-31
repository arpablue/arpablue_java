/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.gui;

import java.awt.BorderLayout;
/**
 *
 * @author 5v
 */
public class BorderPanel extends APanel{
    static public String AFTER_LAST_LINE=BorderLayout.AFTER_LAST_LINE;
    static public String AFTER_LINE_ENDS=BorderLayout.AFTER_LINE_ENDS;
    static public String BEFORE_FIRST_LINE=BorderLayout.BEFORE_FIRST_LINE;
    static public String BEFORE_LINE_BEGINS=BorderLayout.BEFORE_LINE_BEGINS;
    static public String CENTER=BorderLayout.CENTER;
    static public String EAST=BorderLayout.EAST;
    static public String LINE_END=BorderLayout.LINE_END;
    static public String LINE_START=BorderLayout.LINE_START;
    static public String NORTH=BorderLayout.NORTH;
    static public String PAGE_END=BorderLayout.PAGE_END;
    static public String PAGE_START=BorderLayout.PAGE_START;
    static public String SOUTH=BorderLayout.SOUTH;
    static public String WEST=BorderLayout.WEST;
    public BorderPanel()
    {
        super();
        this.setLayout(new BorderLayout());

    }
  
}
