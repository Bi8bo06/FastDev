package com.liangliang.android.core.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * 网络广播
 */
public class ConnectBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null){
            return;
        }
        String action = intent.getAction();
        if (action == null){
            return;
        }
        try {
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkManager.get().updateNet(manager);
                NetworkManager.get().notifyNetworkListeners();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
