/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class NullPanel extends JPanel {
    
    public NullPanel()
    {
        super();
        setLayout(null);
        this.setSize(200,200);
        //setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }
    public Component append( Component comp){
        return this.add(comp);
    }  
}
