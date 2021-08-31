/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.window;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrator
 */
public class NTreeXEditor extends AbstractCellEditor implements TreeCellEditor{

    protected JTree mTree; //Recibimos el árbol que vamos a vigilar
    protected Component mCmp; //el componente checkbox que vamos a editar
    protected NTreeXRenderer mRender = new NTreeXRenderer();

    public NTreeXEditor(){}
    public NTreeXEditor(JTree tree) {
        setTree(tree);
    }
    public void setTree(JTree tree){
        if(tree!=null){
            tree.setEditable(true); //lo ponemos en modo de edición, algo primordial
        }
        mTree=tree;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (mTree == null) {
            return false;
        }
        if (event == null) {
            return false;
        }
        if (event instanceof MouseEvent) { //verificamos si tenemos el evento del mouse
            MouseEvent mouseEvent = (MouseEvent) event; //realizamos el cast respectivo
            //en base a la posicion del evento obtenemos el path completo del nodo seleccionado
            TreePath path = mTree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
            if (path != null) { //verificamos que el path existe
                Object node = path.getLastPathComponent();//obtenemos el ultimo componente
                //verificamos que sea un nodo del árbol
                if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
                    //realizamos el cast respectivo
                    DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
                    //obtenemos el objeto guardado en el nodo
                    Object userObject = treeNode.getUserObject();
                    //preguntamos si es un objeto que nos interesa
                    if (userObject != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row) {
        //Mantenemos la manera en cómo se dibuja por defecto la celda.
        Component result = mRender.getTreeCellRendererComponent(tree, value, true, expanded, leaf, row, true);
        Object userObject;
        //verificamos si existe el nodo y si es del tipo que nos intereza
        if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
            //Obtenemos el objeto almacenado en el nodo
            userObject = ((DefaultMutableTreeNode) value).getUserObject();
            //verificamos que sea del tipo que nos intereza
            if (userObject != null) {
                //ralizamos el cast respectivo
               result = getComponent((Component) userObject);
            }
        }
        if (result == null) //verificamos que exista el objeto
        {
            mCmp = null; //si no existe ponemos nuestro almacenamiento en vacio
        } else {
            mCmp = result; //si existe almacenamos el objeto.
        }
        if((result!=null)&&(result instanceof NodeEvent)){
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

    public Object getCellEditorValue() {
        return mCmp;
    }
    /**
     * This function should be override to return the correct component.
     * @param cmp
     * @return
     */
    public Component getComponent(Component cmp){
        return cmp;
    }

}
