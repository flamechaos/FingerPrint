/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finferprintapi;

import java.io.IOException;

/**
 *
 * @author tiran
 */
public class FinferprintAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //TestServer FpAPI = new TestServer();
        //FpAPI.ListenWebSocket();
        
        String ip = "127.0.0.1";
        int port = 5000;
        APIFingerPrint fp = new APIFingerPrint(ip,port);
        fp.scanWifi();
        
    }
    
    
}
