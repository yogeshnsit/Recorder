package com.yogesh.audiorecorder;

import android.content.SharedPreferences;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Sushil on 28-03-2016.
 */
public class MyPhoneStateListener extends PhoneStateListener {


    public static final int IDLE=0;
    public static final int OUTGOING=1;
    public static final int INCOMING_RINGING=2;
    public static final int INCOMING_ANSWERED=3;
    public static final String TAG="CALLRECEIVER";

    private static volatile boolean isRecording=false;

    static  int currentstate=IDLE;



    private static MyPhoneStateListener myPhoneStateListener=new MyPhoneStateListener();

    private MyPhoneStateListener(){}

    public static MyPhoneStateListener getInstance(){
        return myPhoneStateListener;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        Log.d(TAG,"onCallStateChanged called");
        Log.d(TAG,"Current state is:"+currentstate);


        switch(state){
            case TelephonyManager.CALL_STATE_OFFHOOK:

                if(!CallReceiver.isActivated){
                    break;
                }



                //incoming call is answered. Start recording
                if(currentstate==INCOMING_RINGING){
                    Log.d(TAG,"CALL_STATE_OFFHOOK with new incoming call from "+incomingNumber);
                    RecordUtil.initialize();
                    RecordUtil.record();
                    isRecording=true;

                    currentstate=INCOMING_ANSWERED;
                }

                if(currentstate==IDLE){
                    //new outoing call, start recording
                    Log.d(TAG,"CALL_STATE_OFFHOOK with new outgoing call to number");
                    RecordUtil.initialize();
                    RecordUtil.record();
                    isRecording=true;


                    currentstate=OUTGOING;
                }

                break;

            case TelephonyManager.CALL_STATE_RINGING:

                Log.d(TAG,"CALL_STATE_RINGING from "+incomingNumber);

                //call is incoming. set state as incoming
                currentstate=INCOMING_RINGING;

                break;

            case TelephonyManager.CALL_STATE_IDLE:

                Log.d(TAG,"CALL_STATE_IDLE/ Call disconnected");
                //set state as IDLE and stop recording
                if(isRecording==true){
                    RecordUtil.stopRecord();
                    isRecording=false;
                }

                currentstate=IDLE;

                break;
        }
    }
}
