/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.image;

import com.arpablue.xsystemfile.LogFile;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * It class manage images.
 * @author augusto
 */
public class ImageX 
{
    /**
     * It specify the type for PNG iamges.
     */
    public final static int IMAGE_TYPE_PNG=0;
    /**
     * It specify the type for JPG iamges.
     */
    public final static int IMAGE_TYPE_JPG=1;
    /**
     * It specify the type for GIF iamges.
     */
    public final static int IMAGE_TYPE_GIF=2;
    /**
     * It is the image loaded.
     */
    BufferedImage mImg = null;
    /**
     * Load a image from a specific path, if the image bnot exist then return null;
     * @param path It is the path of the image.
     * @return It is the image loaded.
     */
    public static BufferedImage loadImage(String path)
    {
        BufferedImage res = null;
        try{
            File f = new File(path);
            if((!f.exists())||(!f.isFile()) )
                return null;
            res = ImageIO.read(f);
        }catch(Exception e){
            return null;
        }
        return res;
    }
    /**
     * It load a image from a specifyc path.
     * @param path It is the path of the image.
     * @return It is true if the image has been loadded without problems,
     */
    public boolean load(String path)
    {
        mImg = ImageX.loadImage(path);
        return mImg!=null;
    }
    /**
     * It save the image in an specifyc file, if a image existe in the same path then this image is deleted, by default the image will be saved in JPG format.
     * @param path It is the oath of the file where will be save.
     * @return It is true if the image has been saved without problems.
     */
    public boolean save(String path)
    {
        return save(path,ImageX.IMAGE_TYPE_JPG);
    }
    /**
     * It save the image in an specifyc file, if a image existe in the same path then this image is deleted, by default the image will be saved in JPG format.
     * @param path It is the oath of the file where will be save.
     * @param typeFormat It is the format used to save the iamge, 0 for PNG, 1 for JPG and 2 for GIF.
     * @return It is true if the image has been saved without problems.
     */
    public boolean save(String path,int typeFormat)
    {
        try{
            File outputfile = new File(path);
            if(outputfile.exists())
                outputfile.delete();
            String type = "jpg";
            if(typeFormat==IMAGE_TYPE_PNG)
                type="png";
            else if(typeFormat==IMAGE_TYPE_GIF)
                type="gif";
            if(mImg==null)
                return false;
            ImageIO.write(mImg, type, outputfile); // Write the Buffered Image into an output file
        }catch(Exception e){
            return false;
        }
        return true;
    }
    /**
     * It specify the image to be used.
     * @param img It is a image object.
     */
    public void setImage(Image img) 
    { 
        if (img == null)
        {
            mImg = null;
            return;
        }
        mImg = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
        mImg.getGraphics().drawImage(img, 0, 0, null);
    }
    
    /**
     * It return the cuerrent image used.
     * @return It is the image.
     */
    public BufferedImage getImage()
    { 
        return mImg ;
    }
}
