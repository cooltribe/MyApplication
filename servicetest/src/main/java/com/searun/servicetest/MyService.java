package com.searun.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG = "MyService";
    public MyBinder myBinder = new MyBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG,"onbind() executed");
        return mBinder;
    }
    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (null != str){
                return str.toUpperCase();
            }
            return null;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"onstartcommand() executed");
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "oncreate() executed");
//        try {
//            Thread.sleep(600000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Notification notification = new Notification(R.mipmap.ic_launcher,"有通知到来",System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
//        notification.setLatestEventInfo(this,"这是通知的标题","这是通知的内容",pendingIntent);
//        startForeground(1,notification);

    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"ondestroy() executed");
        super.onDestroy();
    }
    class MyBinder extends Binder{
        public void startDownload(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "startdownload executed");
                }
            }).start();

        }
    }
}
