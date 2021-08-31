/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.interfaces;

public interface IChannel {
    /**
     * It open the communication channel
     * @return It is true if the channel has been open without problems.
     */
    boolean open();

    /**
     * It close the communication channel.
     * @return It is true if the channel has been close without problems.
     */
    boolean close();

    /**
     * It verify if the channel is opened.
     * @return
     */
    boolean isOpen();

}
