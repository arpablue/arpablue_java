/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.graphics.dinamic;

import com.arpablue.arpalib.graphics.Lienzo;
import com.arpablue.parallelism.Parallel;

/**
 *
 * @author Administrator
 */
public class DLienzo extends Lienzo implements Runnable,Parallel {
    protected Thread mThread;
    protected int mSleep;
    protected boolean mStop=true;
    protected boolean mPause=true;
    protected long mLastRefresh;
    public DLienzo(){
        super();
        mSleep=42;
        mThread = null;
        mLastRefresh = 0L;
    }
    public void add(DinamicObj fig){
        super.add(fig);
    }
    public void action(){
        for(int i=0; i<mFigures.size(); i++){
            DinamicObj e = (DinamicObj) mFigures.get(i);
            e.action(mLastRefresh);
        }
    }
    public void run() {
        long tini = 0L;
        mLastRefresh = 0L;
        while(!mStop){
            if(!mPause){
                tini=System.nanoTime();
                action();
                this.repaint();
                mLastRefresh=System.nanoTime()-tini;
            }
        }
    }
    public void sleep(){
        try {
            Thread.sleep(mSleep);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
    public void start() {
        if((mThread == null)||(!mThread.isAlive())){
            mThread = new Thread(this);
        }
        mStop=false;
        mPause=false;
        if(!mThread.isAlive())
            mThread.start();
    }

    public void kill() {
        mStop=true;
        mThread=null;
    }

    public void pause() {
        mPause=true;
    }
    public boolean isAlive(){
        return !mStop;
    }


}
