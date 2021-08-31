/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.web;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author engau
 */
public class FileDownloader {

    public static boolean download(String search, String path) {

        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                URL url = new URL(search);
                String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
                URLConnection con = url.openConnection();
                con.setRequestProperty("User-Agent", USER_AGENT);
                inputStream = con.getInputStream();
                outputStream = new FileOutputStream(path);
                byte[] buffer = new byte[2048];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
            } catch (Exception ex) {
                System.out.println("ERROR 01: "+ex.getMessage());
                return false;
            }

            outputStream.close();

            inputStream.close();
        } catch (Exception e) {
            System.out.println("ERROR 02: "+e.getMessage());
            return false;
        }
        return true;
    }

}
