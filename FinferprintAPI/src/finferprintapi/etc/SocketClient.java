/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finferprintapi.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import finferprintapi.etc.Fingerprint;
import android.net.wifi.WifiManager;

/**
 *
 * @author tiran
 */
public class SocketClient {
    Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    Fingerprint fp;
    WifiManager wifiManager;
    Thread Thread1 = null;
    Thread Thread2 = null;
    Thread Thread3 = null;
    private String ip;
    private int port;

    public SocketClient(Fingerprint fp,WifiManager wifiManager) {
        this.fp = fp;
        this.wifiManager = wifiManager;
    }
    
    
    public void Connect(String ip,int port){
        this.ip = ip;
        this.port = port;
        Thread1 = new Thread(new Thread1());
        Thread1.start();
     }

    public void Listen() throws IOException{
        Thread2 = new Thread(new Thread2());
        Thread2.start();
    }
    
    public String Respond(){
        String respond = "";
        fp.scanWifi(wifiManager);
        for (ApRSSI scanResult : fp.getArrayList()) {
            respond += scanResult.toString();
        }
        return respond;
    }

    class Thread1 implements Runnable {

        Thread1(){

        }
        public void run() {
        try {
            socket = new Socket(ip, port);
            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread2 = new Thread(new Thread2());
            Thread2.start();

            //new Thread(new Thread2()).start();
         } catch (IOException e) {
         e.printStackTrace();
         }
        }
    }

    class Thread2 implements Runnable {
       @Override
       public void run() {
          while (true) {
             try {
                final String message = input.readLine();
                if (message != null) {
                   Thread3 = new Thread(new Thread3(Respond()));
                } else {
                   Thread1 = new Thread(new Thread1());
                   Thread1.start();
                   return;
                }
             } catch (IOException e) {
                e.printStackTrace();
             }
          }
       }
    }
    
    class Thread3 implements Runnable {
       private String message;
       Thread3(String message) throws IOException {
          this.message = message;
       }
       @Override
       public void run() {
          output.write(message);
          output.flush();
          Thread2 = new Thread(new Thread2());
            Thread2.start();
       }
    }
}
