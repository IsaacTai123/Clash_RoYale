package com.example.clashroyale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.clashroyale.R;
import com.example.clashroyale.controller.BattleStart;
import com.example.clashroyale.controller.InitEventListener;
import com.example.clashroyale.controller.MoveActionLogic;
import com.example.clashroyale.db.MysqlCon;
import com.example.clashroyale.db.RedisCon;
import com.example.clashroyale.models.Archor;
import com.example.clashroyale.view.InitViewElement;

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

        battleStart.moveCard();

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}