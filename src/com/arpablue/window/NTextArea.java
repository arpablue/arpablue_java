/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Ing. Socrates Augusto Flores Ayala
 */
public class NTextArea extends JScrollPane{
    protected JTextArea mEditor;

    public NTextArea(){
        super();
        mEditor = new JTextArea();
        mEditor.setLineWrap(true);
        mEditor.setWrapStyleWord(true);
        this.setViewportView(mEditor);
        this.setLocation(0,0);
        this.setSize(400,400);


    }
    /**
     * Add a string to the current text.
     */
    public void addText(String text){
        mEditor.setText(mEditor.getText()+text);
    }
    /**
     * Enable the component.
     */
    public void setEnable(boolean enable){
        super.setEnabled(enable);
        mEditor.setEnabled(enable);
    }
    /**
     * Set or not editable the component the component.
     */
    public void setEditable(boolean edit){
        mEditor.setEditable(edit);
    }
    /**
     * Especify the text.
     */
     public void setText(String text){
         mEditor.setText(text);
     }
     /**
      * Return the text in the TextArea.
      */
     public String getText(){
         return mEditor.getText();
     }
}
