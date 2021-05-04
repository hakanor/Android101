package com.hakanor.android101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int progress_value=0;
    private Button button_incr ;
    private Button button_start ;
    private ProgressBar progress_bar;
    private TextView text_countdown;
    private CountDownTimer ct_timer;
    private long worktime = 1500000; // milisaniye
    private long TimeLeft=worktime;


    private void startTimer(){
        ct_timer = new CountDownTimer(worktime,1000) {
            @Override
            public void onTick(long l) {
                    TimeLeft=l;
                    if (((TimeLeft / 1000 )%60)%1 == 0){
                        progress_value+=1;
                        updateProgressBar();}
                    updateTimer();
            }

            @Override
            public void onFinish() {
                if(worktime==1500000){
                    worktime=300000;
                    TimeLeft=300000;
                    updateTimer();
                    progress_value=0;
                    updateProgressBar();
                    progress_bar.setMax(300);
                    startTimer();
                }
                if(worktime==300000){
                    worktime=1500000;
                    TimeLeft=1500000;
                    updateTimer();
                    progress_value=0;
                    updateProgressBar();
                    progress_bar.setMax(1500);
                    startTimer();
                }

            }
        }.start();
    }

    public void updateTimer(){
        int minutes= (int) (TimeLeft / 1000)/60;
        int seconds= (int) (TimeLeft / 1000 )%60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        text_countdown.setText(timeLeftFormatted);
    }

    private void updateProgressBar(){
        progress_bar.setProgress(progress_value);
    }


    /*                                  ON CREATE                                    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress_bar=findViewById(R.id.progress_bar);
        text_countdown=findViewById(R.id.text_countdown);
        button_start=findViewById(R.id.button_start);
        button_incr = findViewById(R.id.button_incr);


        // Start
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });


        // Deneme butonu (silinecek)
        button_incr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_value+=10;
                updateProgressBar();
            }
        });

    }
}