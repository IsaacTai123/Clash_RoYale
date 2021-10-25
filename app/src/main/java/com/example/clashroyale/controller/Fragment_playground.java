package com.example.clashroyale.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.clashroyale.R;

public class Fragment_playground extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance) {
        View view = inflater.inflate(R.layout.fragment_playground, container, false);
        return view;
    }
}
