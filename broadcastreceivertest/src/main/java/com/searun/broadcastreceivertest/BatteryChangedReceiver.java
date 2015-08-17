package com.searun.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryChangedReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryChangedReceiver";
    public BatteryChangedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        int currLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        int total = intent.getIntExtra(BatteryManager.EXTRA_SCALE,1);
        int percent = currLevel * 100 / total;
        Log.d(TAG,"battery" + percent + "%");
    }
}
