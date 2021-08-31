/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.physical;

/**
 *
 * @author Administrator
 */
public class Acceleration {
    public double value;

    public Acceleration(){
        setValue(10);
    }
    public Acceleration(int value){
        setValue(value);
    }
    public Acceleration(float value){
        setValue(value);
    }
    public Acceleration(double value){
        setValue(value);
    }
    @Override
    public String toString(){
        return getValue()+"m/t2";
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }
    /**
     * @return the value
     */
    public int getIntValue() {
        return (int)value;
    }
    /**
     * @return the value
     */
    public long getLongValue() {
        return (long)value;
    }
    /**
     * @return the value
     */
    public float getFloatValue() {
        return (float)value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(float value) {
        this.value = value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
