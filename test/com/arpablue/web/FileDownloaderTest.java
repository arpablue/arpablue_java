/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.web;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author engau
 */
public class FileDownloaderTest {
    
    public FileDownloaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test_FileDownloadImage() throws Exception {
        System.out.println("downloadImage");
        String sourceUrl = "https://commons.apache.org/images/commons-logo.png";
        String targetDirectory = new File("target.png").getAbsolutePath();
        if ( !FileDownloader.download(sourceUrl, targetDirectory ) ){
            fail("It is not possible download the file.");
        }
    }
 
}
