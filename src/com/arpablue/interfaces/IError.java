/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.interfaces;


/**
 *
 * @author engau
 */
public interface IError {
    /**
     * It collect an error message, the empty and null message are not collected.
     * @param msg It is the message to be collected.
     */
    void setError( String msg );
    /**
     * It return the last error collected.
     * @return 
     */
    String getError();
    /**
     * It return an error message from a specific position.
     * @param index It is the position of the error message.
     * @return It is the error message of the position, it is null if the error message not exists.
     */
    String getError(int index);
    /**
     * REturn all error messages in a string.
     * @return 
     */
    String getErrors();
    /**
     * It remove all error message collected.
     */
    void clearErrors();
    /**
     * It verify if error messages has been collected.
     * @return It is true if errors message has been collected
     */
    boolean isError();
    /**
     * It is the quantity of error messages has been collected.
     * @return It is the quantity of error message has been collected.
     */
    int sizeErrors();
    
}
