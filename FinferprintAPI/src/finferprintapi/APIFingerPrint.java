/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finferprintapi;

import androidx.appcompat.app.AppCompatActivity;
import finferprintapi.etc.ApRSSI;
import finferprintapi.etc.Fingerprint;
import finferprintapi.etc.SocketClient;
import java.util.ArrayList;
import finferprintapi.etc.SocketClient;
import finferprintapi.etc.Fingerprint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import android.content.Context;
import android.net.wifi.WifiManager;
/**
 *
 * @author tiran
 */
public class APIFingerPrint {
    private WifiManager wifiManager;
    private Fingerprint fp;
    private SocketClient sc;
    private ArrayList<String> arrayList = new ArrayList<>();
    
    APIFingerPrint(String ip,int port){
        try {
            wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            fp = new Fingerprint(getApplicationContext());
            
            sc = new SocketClient(fp,wifiManager);
            sc.Connect(ip, port);
            sc.Listen();
        } catch (IOException ex) {
            Logger.getLogger(APIFingerPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> scanWifi() {
        //This function is to get the fingerprints of the Wifi
        // returns array of string
        
        arrayList.clear();
        fp.scanWifi(wifiManager);

        for (ApRSSI scanResult : fp.getArrayList()) {
            arrayList.add(scanResult.toString());
        }
        
        return arrayList;
    }
    
    
}
