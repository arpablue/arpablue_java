/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class PanelNull extends JPanel {
    
    public PanelNull()
    {
        super();
        setLayout(null);
        this.setSize(200,200);
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }
}
