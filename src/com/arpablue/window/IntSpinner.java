/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.window;

import com.arpablue.window.PanelBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Administrator
 */
public class IntSpinner extends PanelBorder{
    protected JSpinner mSpinner;
    protected JLabel mTitle;
    protected SpinnerNumberModel mModel ;
    public IntSpinner(String title,int initValue, int min, int max,int step){
        super();
        mSpinner = new JSpinner();
        this.addCenter(mSpinner);
        mTitle = new JLabel(title);
        this.addLeft(mTitle);

        mModel =
        new SpinnerNumberModel(initValue, //initial value
                               min, //min
                               max, //max
                               step);//step
        mSpinner.setModel(mModel);

    }
    public IntSpinner(String title){ this(title,50,0,100,1);}


    public void setTitle(String title){
        mTitle.setText(title);
    }
    public void setMaximun(int max){
        mModel.setMaximum(max);
    }
    public void setMinimun(int min) {
        mModel.setMinimum(min);
    }
    public void setValue(int value){
        mModel.setValue(value);
    }
    public void setStepSize(int value){
        mModel.setStepSize(value);
    }
    public int getValue(){
        return Integer.parseInt(mModel.getValue().toString());
    }
}
