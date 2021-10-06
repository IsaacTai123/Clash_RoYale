package com.example.clashroyale;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;


import com.example.clashroyale.controller.CardRandom;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_card extends Fragment {

    private static int currentElixir = 0;
    private static ProgressBar elixirBar;
    private static TextView elixirCount;
//    private final CardRandom cardRn;
    Handler handler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        elixirBar = view.findViewById(R.id.elixir);
        elixirCount = view.findViewById(R.id.currentElixir);


        startElixir();
        return view;
    }

    public void startElixir() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentElixir >= 1000) {
                    currentElixir += 0;
                } else {
                    currentElixir += 1;
                }
                elixirBar.setSecondaryProgress(currentElixir);
                if (currentElixir % 100 == 0) {
                    elixirBar.setProgress(currentElixir);
                    int i = currentElixir / 100;
                    handler.post(() -> {
                        elixirCount.setText(String.format("%d", i));
                    });
                }
            }
        }, 0, 20);
    }

    public static void reduceElixir(int elixir) {
        int elixir_100 = elixir * 100; //因為現實的1在這邊是100格
        if (currentElixir > elixir_100) {
            currentElixir -= elixir_100;

            if (currentElixir > 100) {
                int quotient = currentElixir / 100;
                elixirBar.setProgress(quotient * 100);
                elixirCount.setText(String.format("%d", quotient));
            } else {
                elixirBar.setProgress(0);
                elixirCount.setText(String.format("%d", 0));
            }
        }
    }
}
