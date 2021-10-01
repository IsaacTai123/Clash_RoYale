package com.example.clashroyale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.clashroyale.controller.BattleStart;

import org.json.JSONException;

import java.util.HashMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class MainActivity extends AppCompatActivity {

    BattleStart battleStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設定全螢幕顯示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // 進入遊戲畫面
        battleStart = new BattleStart(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        try {
            battleStart.addEventListener();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}