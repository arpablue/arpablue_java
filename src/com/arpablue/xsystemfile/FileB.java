/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

import com.arpablue.interfaces.IOpenClose;
import com.arpablue.tools.StringManager;

/**
 * It is the base to use the FileR and FileW classes
 * @author Augusto Flores
 */
public abstract class FileB implements IOpenClose{
    protected String msPath;
    protected boolean mbOpen = false;
    /**
     * It specify the path file to be created.
     * @param pathFile It is the path file.
     */
    public void setFile( String pathFile ){
        if( pathFile != null){
            pathFile = StringManager.setFormatPath(pathFile);
            if( pathFile.length() < 1 ){
                pathFile = null;
            }
        }
        msPath = pathFile;
    }
    /**
     * It return the path file of the file.
     * @return It is the path file.
     */
    public String getFile(){
        return msPath;
    }
    /**
     * It return the current status of the file.
     * @return It is true if the file is opened.
     */
    @Override
    public boolean isOpen() {
        return mbOpen;
    }
    /**
     * It open the file to be processed.
     * @return It is true if the file has been opened without problems.
     */
    @Override
    public abstract boolean open();
    /**
     * It close the file, it is called after process the file.
     * @return It is true if the file has been closed without problems.
     */
    @Override
    public abstract boolean close();


}
