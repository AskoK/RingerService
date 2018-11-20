package com.example.askok.ringerservice;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    Button bstart, bstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bstart = findViewById(R.id.btnstart);
        bstop = findViewById(R.id.btnstop);

        bstart.setOnClickListener(this);
        bstop.setOnClickListener(this);

        Intent serviceToggle = new Intent(MainActivity.this, RingService.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        }, 10 * 1000);
    }


    @Override
    public void onClick(View view) {
        if(view == bstart) {
            startService(new Intent(this, RingService.class));
        } else if (view == bstop) {
            stopService(new Intent(this, RingService.class));
        }
    }


}
