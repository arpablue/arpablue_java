/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.interfaces;

/**
 * This interface is used for object that is necessary action to open or close, and made some actions during these processes.
 * @author Augusto Flores
 */
public interface IOpenClose {
    /**
     * It make actions in the moment that the open is opened.
     * @return It is true if no problems to open the object.
     */
    public boolean open();
    /**
     * It take action to close the object.
     * @return It is true if no problems exists in the moment that object is closed.
     */
    public boolean close();
    /**
     * It return the current state of the object.
     * @return It is true if the object is opened.
     */
    public boolean isOpen();
    
}
