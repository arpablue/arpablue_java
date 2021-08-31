/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.window;

import com.arpablue.gui.AFrame;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Panel;

/**
 *
 * @author user-pc
 */
public class PanelXList extends NullPanel{
    protected BoxLayout mActiveLayout;
    protected JPanel mPanel;
    public PanelXList(){
        super();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(boxLayout);

        
    }
}
