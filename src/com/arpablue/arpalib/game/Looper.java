/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.game;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Administrator
 */
public abstract class Looper implements Runnable{
    private Thread mThread;
    private boolean mIsRunning;
    private int mFPS;
    private Canvas mCanvas;
    public Looper(){
        mIsRunning = false;
        setFPS( 60 );
    }
    ///////////////////////////////////////////////////////////////////////////
    public void setFPS(int fps){
        mFPS = fps;
    }
    public int getFPS(){
        return mFPS;
    }
    public synchronized void setCanvas(Canvas canvas){
        mCanvas = canvas;
    }
    public synchronized Canvas getCanvas(){
        return mCanvas;
    }
    public synchronized final boolean isRunning(){
        return mIsRunning;
    }
    protected synchronized final void setRunning( boolean isRunning ){
        mIsRunning = isRunning;
    }
    //////////////////////////////////////////////////////////////////////////
    @Override
    public final void run() {

        
        double timePerTick = 1000000000 / getFPS();
        double delta = 0;
        long now = 0;
        long lastTime = System.nanoTime();
        
        long timer = 0;
        int ticks = 0;
        before();
        while( isRunning()){
            now = System.nanoTime();
            timer += now - lastTime;
            delta += (now - lastTime)/timePerTick;
            lastTime = now;
            
            if(delta >= 1){
                tick();
                render();
                /////////////////
                ticks++;
                delta--;
             }
            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
            
         }
            
            
            ////////////////////////
        kill();
        after();
    }
    ////////////////////////////////////////////////////////////////////////
    public synchronized void start(){
        if( isRunning()){
            return;
        }
            
      this.mThread = new Thread(this);
      this.mIsRunning = true;       
      this.mThread.start();
    }
    
    public synchronized void stop(){
        if( !this.isRunning()){
            return;
        }
        this.mIsRunning = false;
    }
    public synchronized void kill(){
        try{
            if(this.mThread == null){
                return;
            }
            if(!isRunning()){
                return;
            }
            this.mIsRunning = false;
            this.mThread.join();
        }catch(Exception e){
            System.out.println("ERROR (Looper - kill): "+e.getMessage());
        }
    }
            
    public synchronized void render(){
        if(getCanvas() == null){
            return;
        }
        BufferStrategy bs = getCanvas().getBufferStrategy();
        if( bs == null){
            if(getCanvas().isVisible() ){
                try{
                getCanvas().createBufferStrategy( 3 );
                }catch(Exception e){}
            }
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
        // 
        render( (Graphics2D) g);
        //
        g.dispose();
        bs.show();

    }
    /////////////////////////////////////////////////////////////////////////
    /**
     * It is executed before start the loop to draw.
     */
    public abstract void before();
    /**
     * It is executed after the loop is finished.
     */
    public abstract void after();
    /**
     * It is the method to call before to draw each frame.
     */
    public abstract void tick();
    /**
     * It is called to draw the current frame.
     * @param g It is the graphics context where the frame will be draw.
     */
    public abstract void render(Graphics2D g);
    
}
