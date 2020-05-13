/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finferprintapi.etc;

/**
 *
 * @author tiran
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

public class Fingerprint {
    private WifiManager _wifiManager;
    private ArrayList<ApRSSI> arrayList;
    private Object Context;

    public Fingerprint(Context context) {
        arrayList = new ArrayList<>();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        context.registerReceiver(wifiReceiver, intentFilter);
    }

    public ArrayList<ApRSSI> scanWifi(WifiManager wifiManager) {
        _wifiManager = wifiManager;
        arrayList.clear();


        wifiManager.startScan();

        return arrayList;
        //if (!wifiManager.isWifiEnabled())
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> results;
            results = _wifiManager.getScanResults();
            context.unregisterReceiver(this);

            for (ScanResult scanResult : results) {
                arrayList.add(new ApRSSI(scanResult.level,scanResult.BSSID));
            }
        };
    };

    public ArrayList<ApRSSI> getArrayList() {
        return arrayList;
    }

    public ArrayList<String> getArrayListToString() {
        ArrayList<String> arraystring = new ArrayList<String>();
        for (ApRSSI apresult :  arrayList) {
            arraystring.add(apresult.toString());
        }
        return arraystring;
    }

}