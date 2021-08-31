/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 * Have the objetive of render each component in the
 * @author Administrator
 */
public class NTreeXRenderer implements TreeCellRenderer{
    protected DefaultTreeCellRenderer mDefaultRender = new DefaultTreeCellRenderer();
    /**this functions should be override to work with the component*/

    public Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf,int row, boolean hasFocus)
    {
        //Mantenemos la manera en como se dibuja por defecto la celda.
        Component result = mDefaultRender.getTreeCellRendererComponent(tree,
                value, selected, expanded, leaf, row, hasFocus);
        Object userObject=null;
        //preguntamos si existe un valor y si es del nodo del arbol
        if (value != null){
            if(value instanceof DefaultMutableTreeNode) {
                //obtenemos el objeto que contiene el nodo, un JCheckBox
                userObject = ((DefaultMutableTreeNode) value).getUserObject();
                if (userObject instanceof String){
                    return result;
                }
                if(userObject != null){
                    result = (Component)userObject;
                }
            }
        }

        if((result!=null)&&(result instanceof NodeEvent)){
            if(hasFocus){
               ((NodeEvent)result).nodeHasFocus();
            }else{
                ((NodeEvent)result).nodeNotHasFocus();
            }
            if(selected){
                ((NodeEvent)result).nodeSelected();
            }else{
                ((NodeEvent)result).nodeNotSelected();
            }
            if(expanded){
                ((NodeEvent)result).nodeExpanded();
            }else{
                ((NodeEvent)result).nodeNotExpanded();
            }
            if(leaf){
                ((NodeEvent)result).nodeLeaf();
            }else{
                ((NodeEvent)result).nodeNotLeaf();
            }
        }
        return result;
    }


}
