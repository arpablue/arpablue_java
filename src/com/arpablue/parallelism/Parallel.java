/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.parallelism;

/**
 *
 * @author Administrator
 */
public interface Parallel {
    /**
     * Start the execution of the thread.
     */
    public void start();
    /**
     * Stop the execution of the thread.
     */
    public void kill();
    /**
     * Delay the execution of the thread.
     */
    public void pause();
    /**
     * Sleep the thread.
     */
    public void sleep();
    /**
     * return true if the thread is alive.
     */
    public boolean isAlive();
}
