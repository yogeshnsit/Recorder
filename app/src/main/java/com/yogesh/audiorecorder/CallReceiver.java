package com.yogesh.audiorecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

/**
 * Created by Sushil on 26-03-2016.
 */
public class CallReceiver extends BroadcastReceiver {


    public static final String TAG="CALLRECEIVER";
    public static TelephonyManager tm;
    SharedPreferences myPrefs;
    MyPhoneStateListener listener;
    public static volatile boolean isActivated;

    String number;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG,"onReceive Called");
        myPrefs=context.getSharedPreferences(MainActivity.SharedPrefs,Context.MODE_PRIVATE);
        isActivated=myPrefs.getBoolean(MainActivity.IsActivated,false);
        Log.d(TAG,"IsActiavated:"+isActivated);

        if(isActivated){
            listener=MyPhoneStateListener.getInstance();
            tm=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }




    }


}

