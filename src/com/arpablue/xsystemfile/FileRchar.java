/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

/**
 * It read a file character by character.
 * @author augusto flores
 */
public class FileRchar extends FileB{

    @Override
    public boolean open() {
        return false;
    }

    @Override
    public boolean close() {
        return true;
    }
    
    
}
