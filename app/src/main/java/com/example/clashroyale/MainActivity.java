package com.example.clashroyale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.clashroyale.controller.BattleStart;

public class MainActivity extends AppCompatActivity {

    BattleStart battleStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 進入遊戲畫面
        battleStart = new BattleStart(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        try {
            battleStart.moveCard();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}