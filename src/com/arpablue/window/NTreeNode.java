/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Administrator
 */
public abstract class NTreeNode extends PanelBorder implements TreeCellRenderer,NodeEvent{
    public NTreeNode(){
        this.setBorder(null);
        this.setOpaque(false);
    }

    public Component getTreeCellRendererComponent(JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus)
    {
        if(hasFocus){
           hasFocus();
        }else{
            nodeNotHasFocus();
        }
        if(selected){
            nodeSelected();
        }else{
            nodeNotSelected();
        }
        if(expanded){
            nodeExpanded();
        }else{
            nodeNotExpanded();
        }
        if(leaf){
            nodeLeaf();
        }else{
            nodeNotLeaf();
        }
        return this;
    }

    
}
