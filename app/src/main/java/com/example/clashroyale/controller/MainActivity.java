package com.example.clashroyale.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clashroyale.R;

public class MainActivity extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //設定全螢幕顯示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.battle);
        bt.setOnClickListener(this::callSecondActivity);

    }

    public void callSecondActivity(View view) {
        Intent i = new Intent(getApplicationContext(), BattleActivity.class);
        i.putExtra("value1", "hello");
        startActivity(i);
    }
}
