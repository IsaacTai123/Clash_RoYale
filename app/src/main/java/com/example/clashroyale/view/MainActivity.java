package com.example.clashroyale.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.clashroyale.R;
import com.example.clashroyale.db.MysqlCon;
import com.example.clashroyale.db.RedisCon;

public class MainActivity extends AppCompatActivity {

    TextView textView, textResid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);
        textResid = findViewById(R.id.textRedis);

        new Thread(new Runnable() {

            @Override
            public void run() {
                MysqlCon con = new MysqlCon();
                con.run();
                final String data = con.getData();

                RedisCon rCon = new RedisCon();
                final String redisData = rCon.getRedisData();

                Log.v("OK", data);
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(data);
                        textResid.setText(redisData);
                    }
                });
            }
        }).start();
    }
}