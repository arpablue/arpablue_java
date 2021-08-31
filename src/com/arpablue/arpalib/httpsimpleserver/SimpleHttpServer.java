/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.httpsimpleserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpServer {

    protected boolean mbActive = false;
    protected HttpServer mServer;
    public void stop() {
        if( !isActive() ){
            return;
        }
        
    }
    public boolean isActive(){
        return mbActive;
    }
    
    public void start() throws Exception {
        if( isActive() ){
            stop();
        }
        mServer = HttpServer.create(new InetSocketAddress(8000), 0);
        mServer.createContext("/info", new InfoHandler());
        mServer.createContext("/get", new GetHandler());
        mServer.createContext("/info2", new InfoHandler2());
        mServer.createContext("/get2", new GetHandler2());
        mServer.setExecutor(null); // creates a default executor
        mServer.start();
        mbActive = true;
    }

    static class InfoHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            String response = "Use /get to download a PDF";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
  // http://localhost:8000/info
  static class InfoHandler2 implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
      String response = "Use /get?hello=word&foo=bar to see how to handle url parameters";
      SimpleHttpServer.writeResponse(httpExchange, response.toString());
    }
  }

    static class GetHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {

            // add the required response header for a PDF file
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "application/pdf");

            // a PDF (you provide your own!)
            File file = new File("c:/temp/doc.pdf");
            byte[] bytearray = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray, 0, bytearray.length);

            // ok, we are ready to send the response.
            t.sendResponseHeaders(200, file.length());
            OutputStream os = t.getResponseBody();
            os.write(bytearray, 0, bytearray.length);
            os.close();
        }
    }


  static class GetHandler2 implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
      StringBuilder response = new StringBuilder();
      Map <String,String>parms = SimpleHttpServer.queryToMap(httpExchange.getRequestURI().getQuery());
      response.append("<html><body>");
      response.append("hello : " + parms.get("hello") + "<br/>");
      response.append("foo : " + parms.get("foo") + "<br/>");
      response.append("</body></html>");
      SimpleHttpServer.writeResponse(httpExchange, response.toString());
    }
  }

  public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
    httpExchange.sendResponseHeaders(200, response.length());
    OutputStream os = httpExchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }


  /**
   * returns the url parameters in a map
   * @param query
   * @return map
   */
  public static Map<String, String> queryToMap(String query){
    Map<String, String> result = new HashMap<String, String>();
    for (String param : query.split("&")) {
        String pair[] = param.split("=");
        if (pair.length>1) {
            result.put(pair[0], pair[1]);
        }else{
            result.put(pair[0], "");
        }
    }
    return result;
  }    
}
