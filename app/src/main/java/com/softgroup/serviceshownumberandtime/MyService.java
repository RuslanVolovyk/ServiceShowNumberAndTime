package com.softgroup.serviceshownumberandtime;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    final String TAG = MyService.class.getName();

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        getNumber();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    void getNumber() {
        Intent intent = new Intent();
        sendBroadcast(intent);

    }

}
