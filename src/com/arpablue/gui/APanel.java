/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.gui;

/**
 *
 * @author 5v
 */
import javax.swing.JPanel;
import java.awt.LayoutManager;
import javax.swing.border.EtchedBorder;
public class APanel extends JPanel{
    public APanel()
    {
        super();
        conf();
        
    }
    protected void conf()
    {
        this.setBorder(new EtchedBorder());
        this.setSize(200,200);
    }

}
