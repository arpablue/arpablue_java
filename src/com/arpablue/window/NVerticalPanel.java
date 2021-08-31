/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 *
 * @author Administrator
 */
public class NVerticalPanel extends JScrollPane{
    protected VerticalPanel mContent;
    protected int mHeightElem=25;
    protected int mWitdthElem=200;
    public NVerticalPanel(){
        super();
        mContent = new VerticalPanel();
        this.setViewportView(mContent);
    }
    public void setSizeElement(int witdh, int height){
        mHeightElem = height;
        mWitdthElem = witdh;
        resizeElements();
    }
    public void resizeElements(){
        mContent.setPreferredSize(
                new Dimension(mWitdthElem,
                mHeightElem*mContent.getComponentCount())
                );

    }
    public void addContentPane(Component comp){
        this.setVisible(false);
        mContent.add(comp);
        resizeElements();
        this.setVisible(true);
    }
    public Component getContentComponent(int index){
        return mContent.getComponent(index);
    }
    public int getContentComponentCount(){ return mContent.getComponentCount();}
}
