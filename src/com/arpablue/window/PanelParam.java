/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author augusto
 */
public class PanelParam extends PanelNull implements LayoutManager
{
    public static int LABEL_WIDTH=100;
    public static int PARAM_HEIGHT=-1;
    
    public JLabel mTitle;
    public JTextField mValue;
    
    protected int mTitleSize = 100;
    protected int mPadding = 5;
    
    public PanelParam()
    {
        mTitle = new JLabel("None :");
        mTitle.setVerticalAlignment( SwingConstants.CENTER);
        mTitle.setHorizontalAlignment( SwingConstants.RIGHT);
        mValue = new JTextField();
        
        setLayout(this);
        
    }
    public void setTitleSize(int width)
    {
        if(width<0)
            width*=-1;
        mTitleSize=width;
    }
    protected void reLocate()
    {
        int pad=mPadding*2;
        mTitle.setSize(mTitleSize-mPadding-mPadding, this.getSize().height-mPadding);
        mValue.setSize(this.getSize().width-mTitleSize-mPadding, mTitle.getSize().height-mPadding);
        
        
        mTitle.setLocation(mPadding,mPadding);
        mValue.setLocation(mTitle.getLocation().x+mTitle.getSize().width+mPadding, mPadding);
        
        this.add(mTitle);
        this.add(mValue);
    }
    
    //********************************Getters and Setters
    public void setTitle(String title)
    {
        mTitle.setText(title);
    }
    public void setValue(String value)
    {
        mValue.setText(value);
    }
    public String getTitle()
    {
        return mTitle.getText();
    }
    public String getValue()
    {
        return mValue.getText();
    }
    //****************************Layout
    public void addLayoutComponent(String name, Component comp) {}

    public void removeLayoutComponent(Component comp) {}

    public Dimension preferredLayoutSize(Container parent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Dimension minimumLayoutSize(Container parent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void layoutContainer(Container parent) {
       
        this.reLocate();
    }
    
}


