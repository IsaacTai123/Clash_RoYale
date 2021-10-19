package com.example.clashroyale;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;


import com.example.clashroyale.controller.CardDeck;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_card extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        return view;
    }

}
