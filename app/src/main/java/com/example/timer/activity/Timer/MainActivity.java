package com.example.timer.activity.Timer;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.timer.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnReset;
    private Button btnOpen;
    private Button btnClose;
    private EditText setTime;
    private EditText leftTime;
    private CheckBox lockDrawer;


    private Timer timer;
    private TimerTask timerTask;
    private DrawerLayout drawerLayout;
    private int settime;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //버튼 찾아라---------------------------------------------------------------//
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnOpen = findViewById(R.id.openDrawer);
        btnClose = findViewById(R.id.closeDrawer);
        lockDrawer = findViewById(R.id.lockDrawer);
        //-------------------------------------------------------------------------//
        //타이머 Task 설정----------------------------------------------------------//
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.e("Left Time : ", String.valueOf(counter));
                leftTime.setText(String.valueOf(counter), TextView.BufferType.NORMAL);
                counter--;
                if (counter == 0) {
                    counter = settime;
                }
            }
        };
        //------------------------------------------------------------------------//
        //타이머 주기 설정----------------------------------------------------------//

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setTime = findViewById(R.id.setTime);
                leftTime = findViewById(R.id.leftTime);
                settime = Integer.parseInt(setTime.getText().toString());
                counter = settime;
                timer = new Timer();
                timer.schedule(timerTask, 1000, 1000);
            }
        });
        //------------------------------------------------------------------------//
        //타이머 종료--------------------------------------------------------------//
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                counter = settime;
            }
        });
        //------------------------------------------------------------------------//
        //드로우 레이아웃 Open------------------------------------------------------//
        btnOpen.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.openDrawer(Gravity.LEFT); // 중력이 왼쪽에 있으면 왼쪽으로 잡아 당기는 건가??
                }
            }
        });
        //------------------------------------------------------------------------//
        //드로우 레이아웃 Close------------------------------------------------------//
        btnClose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });
        //------------------------------------------------------------------------//
        //드로우 레이아웃 움직임 Lock------------------------------------------------//
        lockDrawer.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lockDrawer.isChecked()) {  // 원문 내용과 다르게 작성함! 오류 나도 몰라!!
                    if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        drawerLayout.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_OPEN);
                    } else {
                        drawerLayout.setDrawerLockMode(drawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    }       // 드로우 레이아웃이 열렸을 때만 잠그는가 보다
                } else {
                        drawerLayout.setDrawerLockMode(drawerLayout.LOCK_MODE_UNLOCKED);
                }
            }
        });
        //------------------------------------------------------------------------//

    }

}
