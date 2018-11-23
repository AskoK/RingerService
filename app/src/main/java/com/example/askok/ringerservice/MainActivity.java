package com.example.askok.ringerservice;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Handler;

import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import java.util.Set;


public class MainActivity extends AppCompatActivity  {


    Button bstart, bstop;
    private MediaPlayer mp;

    private RingService mSensorService;
    Intent mServiceIntent;

    Context ctx;

    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;
        mSensorService = new RingService(getCtx());
        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
        if (!isMyServiceRunning(mSensorService.getClass())) {
            startService(mServiceIntent);
        }

        //mp = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

        bstart = findViewById(R.id.btnstart);
        bstop = findViewById(R.id.btnstop);

        //bstart.setOnClickListener(this);
        //bstop.setOnClickListener(this);

        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI );
                mp.start();
            }
        });

        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });




        //Intent serviceToggle = new Intent(MainActivity.this, RingService.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        }, 10 * 1000);
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        stopService(mServiceIntent);
        super.onDestroy();

    }


    /*
    @Override
    public void onClick(View view) {
        if(view == bstart) {
            mp.start();
        } else if (view == bstop) {
            mp.stop();
            mp.reset();
        }
    } */


}
