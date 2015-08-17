package com.searun.broadcastreceivertest;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(this);
        MyBroadcastReceiver receiver = new MyBroadcastReceiver();
        MyBroadcastReceiver1 receiver1 = new MyBroadcastReceiver1();
        MyBroadcastReceiver2 receiver2 = new MyBroadcastReceiver2();
        IntentFilter intentFilter = new IntentFilter("com.searun.broadcastreceivertest.MY_BROAD");
        IntentFilter intentFilter1 = new IntentFilter("com.searun.broadcastreceivertest.MY_BROAD");
        IntentFilter intentFilter2 = new IntentFilter("com.searun.broadcastreceivertest.MY_BROAD");
        intentFilter.setPriority(999);
        intentFilter1.setPriority(998);
        intentFilter2.setPriority(997);
        registerReceiver(receiver, intentFilter);
        registerReceiver(receiver1, intentFilter1);
        registerReceiver(receiver2, intentFilter2);
        Intent battertIntent = getApplicationContext().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int currLevel = battertIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int total = battertIntent.getIntExtra(BatteryManager.EXTRA_SCALE,1);
        int percent = currLevel * 100 / total;
        Log.i("电量","battery" + percent + "%");
    }
    private void sendBroadcast(){
        Intent intent = new Intent("com.searun.broadcastreceivertest.MY_BROAD");
        intent.putExtra("name","hello receiver");
        sendOrderedBroadcast(intent,"scott.permission.MY_BROADCAST_PERMISSION");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                Log.i("send broadcast","send broadcast");
                sendBroadcast();
                break;
        }
    }
}
