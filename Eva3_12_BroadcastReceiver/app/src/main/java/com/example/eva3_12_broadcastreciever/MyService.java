package com.example.eva3_12_broadcastreciever;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                    Intent inMsg2 = new Intent("MI_SERVICIO");
                    inMsg2.putExtra("msg", "Larry la ardilla que tiembla");
                    sendBroadcast(inMsg2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Intent inMsg = new Intent("MI_SERVICIO");
        inMsg.putExtra("msg", "onCreate");
        sendBroadcast(inMsg);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Intent inMsg = new Intent("MI_SERVICIO");
        inMsg.putExtra("msg", "onStart");
        sendBroadcast(inMsg);

        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent inMsg = new Intent("MI_SERVICIO");
        inMsg.putExtra("msg", "onDestroy");
        sendBroadcast(inMsg);
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
