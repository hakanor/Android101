package com.hakanor.android101;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int progress_value=0;
    private Button button_incr ;
    private Button button_start ;
    private Button deneme;
    private ProgressBar progress_bar;
    private TextView text_countdown;
    private CountDownTimer ct_timer;
    private long worktime = 1500000; // milisaniye
    public long TimeLeft=worktime;


    private void startTimer(){
        ct_timer = new CountDownTimer(TimeLeft,1000) {
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

            }

        }.start();
    }

    private void startTimer_work(){
        progress_bar.setMax(1500);
        ct_timer = new CountDownTimer(1500000,1000) {
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

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setTitle("Alarm!");
                alertDialog.setMessage("Çalışma süreniz dolmuştur. Mola Süresi için onaylayın.");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ONAYLA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Mola süreniz başladı.", Toast.LENGTH_SHORT).show();
                        progress_value=0;
                        updateProgressBar();
                        startTimer_rest();
                    }
                });
                alertDialog.show();
            }

        }.start();
    }

    private void startTimer_rest(){
        progress_bar.setMax(300);
        ct_timer = new CountDownTimer(300000,1000) {
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

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setTitle("Alarm!");
                alertDialog.setMessage("Mola süreniz dolmuştur. Mola Süresi için onaylayın.");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ONAYLA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Çalışma süreniz başladı.", Toast.LENGTH_SHORT).show();
                        progress_value=0;
                        updateProgressBar();
                        startTimer_work();
                    }
                });
                alertDialog.show();
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
        deneme=findViewById(R.id.deneme);

        // Start
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer_rest();
            }
        });


        // Deneme butonu (silinecek)
        button_incr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progress_value+=10;
                System.out.println(worktime);
                TimeLeft=10000;
                updateTimer();
            }
        });

        deneme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("-----------------------------------");
                System.out.println("worktime : "+ worktime);
                System.out.println("timeleft : "+TimeLeft);
                System.out.println("progress_value : "+progress_value);
                System.out.println("progress_max : "+progress_bar.getMax());
                System.out.println("-----------------------------------");

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alarm!");
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setMessage("Mola süreniz dolmuştur. Mola Süresi için onaylayın.");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ONAYLA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Çalışma süreniz başladı.", Toast.LENGTH_SHORT).show();

                    }
                });
                alertDialog.show();

            }
        });
    }
}