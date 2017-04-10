package com.softgroup.serviceshownumberandtime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MyReceiver extends BroadcastReceiver {
    public static final String TAG = MyReceiver.class.getName();
    public static final String FILE_NAME = "MyPhoneNumbers.txt";

    @Override
    public void onReceive(final Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                Log.d(TAG, "incomingNumber : " +  incomingNumber);
                FileOutputStream outputStream;
                try {
                    outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    outputStream.write(incomingNumber.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String readNumber = "";

                try {
                    InputStream inputStream = context.openFileInput(FILE_NAME);

                    if ( inputStream != null ) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";
                        StringBuilder stringBuilder = new StringBuilder();
                        while ( (receiveString = bufferedReader.readLine()) != null ) {
                            stringBuilder.append(receiveString);
                        }
                        inputStream.close();
                        readNumber = stringBuilder.toString();
                    }
                }
                catch (FileNotFoundException e) {
                    Log.e(TAG, "File not found: " + e.toString());
                } catch (IOException e) {
                    Log.e(TAG, "Can not read file: " + e.toString());
                }
                Log.d(TAG, "readNumber : " +  readNumber);

            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }

}