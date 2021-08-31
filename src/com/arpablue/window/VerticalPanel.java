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
public class VerticalPanel extends NullPanel {
    public VerticalPanel(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

}
