package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_stopwatch extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_stopwatch);
        runTimer();
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");

        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClick(View view){
        running=true;
    }

    public void onStop(View view){
        running=false;
    }

    public void onReset(View view){
        running =false;
        seconds =0;
    }

    public void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%d:%02d:%02d",hours,minutes,secs);

                timeView.setText(time);

                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }
}
