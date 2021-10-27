package com.example.clashroyale;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;

        import com.example.clashroyale.controller.BattleStart;

public class BattleActivity extends AppCompatActivity {

    BattleStart battleStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設定全螢幕顯示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_battle);

        // 進入遊戲畫面
        try {
            battleStart = new BattleStart(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    public void callFirstActivity(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
