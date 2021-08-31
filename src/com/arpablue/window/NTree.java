/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Administrator
 */
public class NTree extends JScrollPane {
    protected DefaultMutableTreeNode mRoot;
    protected DefaultTreeModel mModel;
    protected JTree mTree;
    protected boolean mExpandAll=false;
    protected TreeCellRenderer mCellRederer;
    protected String mTitle;
    public NTree(){
        this("");
    }
    public NTree(Object title) {
        super();
        mTitle = title.toString();
        mRoot = new DefaultMutableTreeNode(title);
        mModel = new DefaultTreeModel(mRoot);
        mTree = new JTree(mModel);
        /*
        //mTree.setEditable(true);
        //Construccion de los datos del arbol
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode(new JCheckBox("padre"));
        DefaultMutableTreeNode tio = new DefaultMutableTreeNode(new JCheckBox("tio"));

        //relacionamos los nodos ente si
        mModel.insertNodeInto(padre, mRoot, 0);
        mModel.insertNodeInto(tio, mRoot, 1);
        //repetimos el proceso para los hijos
        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(new JCheckBox("hijo"));
        DefaultMutableTreeNode hija = new DefaultMutableTreeNode(new JCheckBox("hija"));
        mModel.insertNodeInto(hijo, padre, 0);
        mModel.insertNodeInto(hija, padre, 1);
        //nos aseguramo de colocar un tamaño mínimo al JScrollPane, en caso que
        //usemos un BorderLayout*/
        this.setPreferredSize(new Dimension(150,100));
        this.setViewportView(mTree);
    }
    public NTree(String title) {
        super();
        mTitle = title;
        mRoot = new DefaultMutableTreeNode(mTitle);
        mModel = new DefaultTreeModel(mRoot);
        mTree = new JTree(mModel);
        /*
        //mTree.setEditable(true);
        //Construccion de los datos del arbol
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode(new JCheckBox("padre"));
        DefaultMutableTreeNode tio = new DefaultMutableTreeNode(new JCheckBox("tio"));

        //relacionamos los nodos ente si
        mModel.insertNodeInto(padre, mRoot, 0);
        mModel.insertNodeInto(tio, mRoot, 1);
        //repetimos el proceso para los hijos
        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(new JCheckBox("hijo"));
        DefaultMutableTreeNode hija = new DefaultMutableTreeNode(new JCheckBox("hija"));
        mModel.insertNodeInto(hijo, padre, 0);
        mModel.insertNodeInto(hija, padre, 1);
        //nos aseguramo de colocar un tamaño mínimo al JScrollPane, en caso que
        //usemos un BorderLayout*/
        this.setPreferredSize(new Dimension(150,100));
        this.setViewportView(mTree);
    }
    /**
     * Add a new node in the tree
     */
    public DefaultMutableTreeNode getNode(String path){
        if(path == null)
            path="";
        if(path.length()<1){
            return mRoot;
        }
        String [] v = path.split("/");
        return getNode(mRoot,v,0);
    }
    /**
     * Add a new node in to a specyfic path.
     */
    protected DefaultMutableTreeNode getNode(DefaultMutableTreeNode father,String [] path, int pos){
        if(pos<0){
            return null;
        }
        if(father==null){
            return null;
        }
        if(pos>path.length-1){
            return null;
        }
        DefaultMutableTreeNode aux=searchChild(father,path[pos]);
        if(pos==path.length-1){
           return aux;
        }
        if(aux==null){
            return null;
        }
        return getNode(aux,path,pos+1);
    }
    /**
     * Return the object int the node of the specifyc path.
     */
    public Object getNodeObject(String path){
        DefaultMutableTreeNode res = getNode(path) ;
        if(res==null)
            return null;
        return res.getUserObject();
    }
    /**
     * Add a new node in the tree
     */
    public int addNode(Object obj){
        return addNode(obj,"");
    }
    /**
     * Add a new node in the tree
     */
    public int addNode(Object obj,String path){
        if(path == null)
            path="";
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(obj);
        if(path.length()<1){
            return addChild(mRoot,child);
        }
        String [] v = path.split("/");
        return addNode(mRoot,child,v,0);
    }
    /**
     * Add a new node in to a specyfic path.
     */
    protected int addNode(DefaultMutableTreeNode father,DefaultMutableTreeNode child,String [] path, int pos){
        if(pos<0){
            return -1;
        }
        if(father==null){
            return -1;
        }
        if(child==null){
            return -1;
        }
        if(pos>path.length-1){
            return -1;
        }
        DefaultMutableTreeNode aux=searchChild(father,path[pos]);
        if(pos==path.length-1){
           return addChild(aux,child);
        }
        if(aux==null){
            return -1;
        }
        return addNode(aux,child,path,pos+1);
    }
    /**
     * Add a node into a specyfic father, return the number of the new child, if
     * the number is less that zero, then  any child has been added.
     */
    public int addChild(DefaultMutableTreeNode father, DefaultMutableTreeNode child){
        if( (father==null)||(child==null))
            return -1;
       int count = father.getChildCount();
       mModel.insertNodeInto(child, father, count);
       return count;
    }
    /**
     * Search a child with a string like to the specifyc string, this searh is uncasesensitive.
     */
    protected DefaultMutableTreeNode searchChild(DefaultMutableTreeNode node, String stringValue){
        int length=node.getChildCount();
        DefaultMutableTreeNode result=null;
        for(int i=0; i< length; i++){
           result= (DefaultMutableTreeNode)node.getChildAt(i);
           if(stringValue.equalsIgnoreCase(result.toString())){
               return (DefaultMutableTreeNode)result;
           }
        }
        return null;
    }
    /**
     * return the title of the tree or the title of the root node.
     */
    public String getTitle(){
        return mTitle;
    }
    /**
     * Return the current title.
     * @return
     */
    public JTree getTree(){
        return mTree;
    }
    /**
     * Specify the render for each node in the tree.
     */
    public void setCellRendered(TreeCellRenderer cellRenderer){
        if(mTree != null)
            mTree.setCellRenderer(cellRenderer);
    }
    /**
     * Specify the editor behavior for each node of the tree.
     */
    public void setCellEditor(TreeCellEditor cellEditor){
        if(mTree != null)
            mTree.setCellEditor(cellEditor);
    }
    /**
     * Return the current Cell editor.
     */
    public TreeCellEditor getCellEditor(){
        if(mTree != null)
            return mTree.getCellEditor();
        return null;
    }
    /**
     * Set the selection model from TreeSelectionMode class, de values are: <br>
     * static int CONTIGUOUS_TREE_SELECTION
     * Selection can only be contiguous.
     *
     * static int DISCONTIGUOUS_TREE_SELECTION
     * Selection can contain any number of items that are not necessarily contiguous.
     *
     * static int SINGLE_TREE_SELECTION
     * Selection can only contain one path at a time.
     *
     */
    public void setSelectionModel(int mode){
        if(mTree == null)
            return;
        mTree.getSelectionModel().setSelectionMode(mode);
    }
    /**
     * Expand all nodes in the tree.
     */
    public void expandAllNodes(){
        mExpandAll=true;
        for(int i=0; i<mTree.getRowCount(); i++)
            mTree.expandRow(i);
    }
    /**
     * collapse all nodes in the tree.
     */
    public void collapseAllNodes(){
        mExpandAll=false;
        for(int i=0; i<mTree.getRowCount(); i++)
            mTree.collapseRow(i);
    }
    
}
