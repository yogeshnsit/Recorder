package com.yogesh.audiorecorder;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    public static final String TAG="CALLRECEIVER";

    SharedPreferences myPrefs;

    public static final String SharedPrefs="Shared_Prefs";
    public static final String IsActivated="IsActivated";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.myToolbar));

        myPrefs=getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor=myPrefs.edit();
        editor.putBoolean(IsActivated,false);
        editor.commit();


    }




    public void turnON(View view) {
        myPrefs=getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor=myPrefs.edit();
        editor.putBoolean(IsActivated,true);
        editor.commit();


    }

    public void turnOFF(View view){
        myPrefs=getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor=myPrefs.edit();
        editor.putBoolean(IsActivated,false);
        editor.commit();

    }



}
