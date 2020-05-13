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
public class ApRSSI {
    private int RSSI;
    String Mac;


    public ApRSSI() {
    }

    public ApRSSI(int RSSI, String mac) {
        this.RSSI = RSSI;
        Mac = mac;
    }

    public int getRSSI() {
        return RSSI;
    }

    public String getMac() {
        return Mac;
    }

    public void setRSSI(int RSSI) {
        this.RSSI = RSSI;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

    @Override
    public String toString() {
        return  "{" +
                "Mac='" + Mac +
                ";RSSI=" + RSSI +
                '\'' + '}';
    }

}
