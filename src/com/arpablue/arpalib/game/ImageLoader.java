/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author August
 */
public class ImageLoader {
    public static String strReplace(String target, String oldstr, String newstr){
        if( target == null ){
            return null;
        }
        while( target.indexOf(oldstr) > -1){
            target = target.replace(oldstr, newstr);
        }
        return target;
    }
//    public static BufferedImage loadImageFromResource(String path){
//        return loadImage(ImageLoader.class.getR);
//    }
    public static BufferedImage loadImage(String path){
        try{
            if(path == null){
                System.out.println("Error(ImageLoader - loadImage): The ["+path+"] path file can not be null.");
                System.exit(1);
                return null;
            }
            path = strReplace(path, "\\", "/");
            //URL in = null;
            //in = ImageLoader.class.getResource(path);
            InputStream in = ImageLoader.class.getResourceAsStream(path);
            if( in == null){
                // path = "./res/"+path;
                path = path.replace("//", "/");
                File f = new File(path);
                if(!f.exists()){
                    System.out.println("Error(ImageLoader - loadImage): The ["+path+"] file not exists.");
                    System.exit(1);
                    return null;
                }
                if(!f.isFile()){
                    System.out.println("Error(ImageLoader - loadImage): The ["+path+"] image file reference is not a file.");
                    System.exit(1);
                    return null;
                }
                System.out.println("Image loaded: "+path);
                return ImageIO.read(f);
            }
            System.out.println("Image loaded: "+path);
            return ImageIO.read( in );
        }catch( IOException e){
            System.out.println("Error(ImageLoader - loadImage): "+e.getMessage());
            System.exit(1);
        }
        return null;
    }
    public static BufferedImage crop(BufferedImage target, int x, int y, int width, int height){
        if( target == null){
            return null;
        }
        return target.getSubimage(x, y, width, height);
    }    
}
