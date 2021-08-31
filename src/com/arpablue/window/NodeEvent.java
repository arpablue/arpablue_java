/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.Component;

/**
 *
 * @author Administrator
 */
public interface NodeEvent {
    public void nodeSelected();
    public void nodeNotSelected();
    public void nodeExpanded();
    public void nodeNotExpanded();
    public void nodeHasFocus();
    public void nodeNotHasFocus();
    public void nodeLeaf();
    public void nodeNotLeaf();

}
