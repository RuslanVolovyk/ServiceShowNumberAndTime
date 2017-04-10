package com.softgroup.serviceshownumberandtime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyReceiver extends BroadcastReceiver {
    public static final String TAG = MyReceiver.class.getName();
    public static final String FILE_NAME = "MyPhoneNumbers.txt";

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);


        File file;
        FileOutputStream outputStream;
        try {
            // file = File.createTempFile("MyCache", null, getCacheDir());
            file = new File(getCacheDir(), "MyCache");

            outputStream = new FileOutputStream(file);
            outputStream.write(incomingNumber.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FileOutputStream outputStream;
//        try {
//            outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            outputStream.write(incomingNumber.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Log.i(TAG, incomingNumber);
    }

}