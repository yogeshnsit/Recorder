package com.yogesh.audiorecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sushil on 25-03-2016.
 */
public class RecordUtil {
    private static MediaRecorder recorder;

    public static boolean initialize(){
        if(recorder==null){
            recorder= new MediaRecorder();
        }
        recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        File directory=new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()+File.separator+"Recorder");
        boolean dir=directory.mkdirs();
        String time=Calendar.getInstance().getTime().toString();
        //SimpleDateFormat df=new SimpleDateFormat("ddMMYYYY");
        //String date=df.format(Calendar.getInstance().getTime());
        Log.d("Recorder_Yogesh:","directory is created"+dir);
        String outpath=directory.getAbsolutePath()+File.separator+time+".mp3";
        File file=new File(outpath);
        if(!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        recorder.setOutputFile(outpath);

        return true;
    }

    public static void record(){
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Recorder_Yogesh:","Could not prepare");
        }
        recorder.start();
    }

    public static void stopRecord(){
        recorder.stop();
        recorder.reset();
        recorder.release();
    }
}
