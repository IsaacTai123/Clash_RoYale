package com.example.clashroyale.controller;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.clashroyale.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class TimerCountDown {

    private Timer timer;
    private TextView countdowntimer;
    private int startTime = 180;

    public TimerCountDown(TextView textView, MainActivity main) {
        countdowntimer = textView;
        timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                startTime--;
                int minutes = startTime / 60;
                String minutesText = minutes + "";
                int seconds = startTime % 60;
                String secondsText = seconds + "";
                if(seconds < 10) {
                    secondsText = "0" + seconds;
                }
                String timeDisplay = minutesText + ":" + secondsText;
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countdowntimer.setText(timeDisplay);
                    }
                });
                if(startTime <= 0 ) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(task,1000,1000);
    }
}
