/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import javax.swing.BoxLayout;

/**
 *
 * @author Administrator
 */
public class PanelHorizontal extends PanelNull {
    public PanelHorizontal(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    }
}
