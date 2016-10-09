package com.example.m1320.express;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by m1320 on 2016/8/7.
 */
public class WebConnectType {
    private String DISCONNECT="DISCONNECT";
    private String TYPE_WIFI="WIFI";
    private String TYPE_MOBI="MOBLE";
    private String OTHERS="OHTERS";

    public String checkNetworkType(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info == null) return DISCONNECT;
        else if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return TYPE_WIFI;
        else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            return TYPE_MOBI;
        }
        else return OTHERS;
    }
}
