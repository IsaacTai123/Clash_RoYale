package com.example.clashroyale.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.clashroyale.R;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_timer extends Fragment {

    private Timer timer;
    private TextView countdowntimer;
    private int startTime = 180;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaneState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        TextView text = view.findViewById(R.id.timerClock);

        countDown(text);
        return view;
    }

    public void countDown(TextView textView) {
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
                getActivity().runOnUiThread(() -> countdowntimer.setText(timeDisplay));
                if(startTime <= 0 ) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(task,1000,1000);
    }
}
