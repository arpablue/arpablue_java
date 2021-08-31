/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import com.arpablue.window.PanelBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */
public class NCheckBox extends PanelBorder{
    protected JLabel mText;
    protected JCheckBox mCheck;
    protected String mValue;
    public NCheckBox(String text, String value){
        super();
        this.setSize(100, 20);
        mText = new JLabel();
        mCheck = new JCheckBox();

        this.addLeft(mCheck);
        this.addCenter(mText);
        setText(text);
        mValue = value;
    }
    public NCheckBox(){ this(null,null);}
    public NCheckBox(String text){ this(text,null);}
    public void setValue(String value){ mValue = value;}
    public String getValue(){ return mValue;}
    public void setText(String text)
    {
        mText.setText(text);
    }
    public String getText(String text)
    {
        return mText.getText();
    }
    public void setSelected(boolean value)
    {
        mCheck.setSelected(value);
    }
    public boolean isSelected(){
        return mCheck.isSelected();
    }

}
