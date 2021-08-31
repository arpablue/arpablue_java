/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.interfaces;

/**
 * It is the interface used for all classes that need send meggage to alog output.
 * @author ASUS
 */
public interface ILogger {
    /**
     * It write a string line in a output log
     * @param text It is the message to be written in the log file.
     */
    void write(String text);
    void writeln();
    void writeln(String text );
    void setFile( String filePath );
}
