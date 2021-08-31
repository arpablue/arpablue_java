/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arpablue.arpalib.graphics;

import java.awt.Point;

/**
 *
 * @author Administrator
 */
public class FPoint{
    public float x;
    public float y;
    /**
     * Default constructor.
     */
    public FPoint(){
        this(0.0f,0.0f);
    }
    /**
     * Create a point in the same location of the other
     * @param target
     */
    public FPoint(Point target){
        this(target.x,target.y);
    }
    /**
     * Create a point in the same location of the other
     * @param target
     */
    public FPoint(FPoint target){
        this(target.x,target.y);
    }
    /**
     * Create a point in the spacify point
     * @param x: coordinate X.
     * @param y: coordinate Y.
     */
    public FPoint(double x, double y){
        setPoint(x,y);
    }
    /**
     * Create a point in the spacify point
     * @param x: coordinate X.
     * @param y: coordinate Y.
     */
    public FPoint(int x, int y){
        setPoint(x,y);
    }

    /**
     * Create a point in the spacify point
     * @param x: coordinate X.
     * @param y: coordinate Y.
     */
    public FPoint(float x, float y){
        setPoint(x,y);
    }
    /**
     * return the distance bethween two fPoints.
     * @param p1
     * @param p2
     * @return
     */
    public static double distance(FPoint p1, FPoint p2){
        double x= (p1.x-p2.x)*(p1.x-p2.x);
        double y= (p1.y-p2.y)*(p1.y-p2.y);
        double h = Math.sqrt(x+y);
        return h;
    }
    /**
     * return the distance bethween two fPoints.
     * @param p1
     * @param p2
     * @return
     */
    public static double distance(Point p1, FPoint p2){
        double x= (p1.x-p2.x)*(p1.x-p2.x);
        double y= (p1.y-p2.y)*(p1.y-p2.y);
        double h = Math.sqrt(x+y);
        return h;
    }
    /**
     * return the distance bethween origin point and any fPoints.
     * @param p1
     * @param p2
     * @return
     */
    public static double distanceOrigin(FPoint p1){
        double x= (p1.x)*(p1.x);
        double y= (p1.y)*(p1.y);
        double h = Math.sqrt(x+y);
        return h;
    }
    /**
     * return the distance bethween origin point and any fPoints.
     * @param p1
     * @param p2
     * @return
     */
    public static double distanceOrigin(Point p1){
        double x= (p1.x)*(p1.x);
        double y= (p1.y)*(p1.y);
        double h = Math.sqrt(x+y);
        return h;
    }
    /**
     * return the distance bethween two fPoints.
     * @param p1
     * @param p2
     * @return
     */
    public static double distance(FPoint p1, Point p2){
        double x= (p1.x-p2.x)*(p1.x-p2.x);
        double y= (p1.y-p2.y)*(p1.y-p2.y);
        double h = Math.sqrt(x+y);
        return h;
    }
    /**
     * return the distance bethween two fPoints.
     * @param p1
     * @param p2
     * @return
     */
    public static double distance(Point p1, Point p2){
        double x= (p1.x-p2.x)*(p1.x-p2.x);
        double y= (p1.y-p2.y)*(p1.y-p2.y);
        double h = Math.sqrt(x+y);
        return h;
    }
    /**
     * Return the distance from origin point to another.
     */
    public double distance(){
        double x= (this.x)*(this.x);
        double y= (this.y)*(this.y);
        double h = Math.sqrt(x+y);
        return h;
    }
    /**
     * Return the distance from this point to another.
     */
    public double distance(FPoint p){
        return distance(this,p);
    }
    /**
     * Return the distance from this point to another.
     */
    public double distance(Point p){
        return distance(this,p);
    }
    /**
     * set the point in the specyfic location.
     */
    public void setPoint(double x, double y){
        this.setX(x);
        this.setY(y);
    }
    /**
     * set the point in the specyfic location.
     */
    public void setPoint(float x, float y){
        this.setX(x);
        this.setY(y);
    }
    /**
     * set the point in the specyfic location.
     */
    public void setPoint(int x, int y){
        this.setX(x);
        this.setY(y);
    }
    /**
     * set the point in the same point of the other.
     */
    public void setPoint(Point p){
            setPoint(p.x,p.y);
    }
    /**
     * set the point in the same point of the other.
     */
    public void setPoint(FPoint p){
            setPoint(p.x,p.y);
    }
    public Point toPoint(){
        return new Point((int)x, (int)y);
    }
    @Override
    public FPoint clone(){
        return new FPoint(x,y);
    }
    /**
     * Return the int value from the coordinate X.
     * @return
     */
    public int getX(){
        return (int)x;
    }
    /**
     * Return the int value from the coordinate Y.
     * @return
     */
    public int getY(){
        return (int)y;
    }
    /**
     * Specify the value of the coordinate X.
     */
    public void setX(float x){
        this.x = x;
    }
    /**
     * Specify the value of the coordinate X.
     */
    public void setX(double x){
        this.x = (float)x;
    }
    /**
     * Specify the value of the coordinate X.
     */
    public void setX(int x){
        this.x = (float)x;
    }
    /**
     * Specify the value of the coordinate Y.
     */
    public void setY(float y){
        this.y = y;
    }
    /**
     * Specify the value of the coordinate Y.
     */
    public void setY(int y){
        this.y = (float)y;
    }
    /**
     * Specify the value of the coordinate Y.
     */
    public void setY(double y){
        this.y = (float)y;
    }
    /**
     * Return the (x,y) value.
     * @return
     */
    @Override
    public String toString(){
        return "( "+x+" , "+y+" )";
    }

}
