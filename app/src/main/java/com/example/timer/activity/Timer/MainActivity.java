package com.example.timer.activity.Timer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timer.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnReset;
    private EditText setTime;
    private EditText leftTime;

    private Timer timer;
    private TimerTask timerTask;
    private int settime;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //타이머 Task 설정----------------------------------------------------------//
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.e("Left Time : ", String.valueOf(counter));
                leftTime.setText(String.valueOf(counter), TextView.BufferType.NORMAL);
                counter--;
                if (counter == 0)
                {
                    counter = settime;
                }
            }
        };
        //끝 타이머 Task 설정-------------------------------------------------------//
        //타이머 주기 설정----------------------------------------------------------//
        btnStart = (Button) findViewById(R.id.btnStart);
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
        //끝 타이머 주기 설정-------------------------------------------------------//
        //타이머 종료--------------------------------------------------------------//
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                counter = settime;
            }
        });
        //끝 타이머 종료------------------------------------------------------------//
    }

}
