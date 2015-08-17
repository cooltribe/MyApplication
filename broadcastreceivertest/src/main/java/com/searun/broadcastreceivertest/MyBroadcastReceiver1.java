package com.searun.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyBroadcastReceiver1 extends BroadcastReceiver {
    public static final String TAG = "MyBroadcastReceiver1";
    public static  int m = 1;
    public MyBroadcastReceiver1() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG,"intent" + intent);
        String name = getResultExtras(true).getString("name");
        Log.w(TAG,"name:" + name + "m=" + m);
        m++;
        Bundle bundle = new Bundle();
        bundle.putString("name",name + "@MyBroadcastReceiver2");
        setResultExtras(bundle);
    }
}
