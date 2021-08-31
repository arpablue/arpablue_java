/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import javax.swing.tree.TreeCellEditor;

/**
 *
 * @author Administrator
 */
public class NTreeX extends NTree{
    protected boolean mEditable=false;
    protected TreeCellEditor mCellEditor=null;
    public NTreeX(){
        this("");
        mCellEditor=this.getCellEditor();
    }
    public NTreeX(String title){
        super(title);
        editorRenderer();
    }
    public NTreeX(Object title){
        super(title);
        editorRenderer();
    }
    protected void editorRenderer(){
        this.setNTreeXRenderer(new NTreeXRenderer());
    }
    public void setEditable(boolean editable){
        if(editable){
            mCellEditor=this.getCellEditor();
            this.setNTreeXEditor(new NTreeXEditor(this.mTree));
            return;
        }
        this.setCellEditor(mCellEditor);
        return;

    }
    public void setNTreeXEditor(NTreeXEditor editor){
        if(editor!=null){
            editor.setTree(this.mTree);
        }
        this.setCellEditor(editor);
    }
    public void setNTreeXRenderer(NTreeXRenderer render){
        this.setCellRendered(render);
    }
}
