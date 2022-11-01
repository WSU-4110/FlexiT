package com.example.flexit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
/*
    I have added new functionality to this file, users can now set a time to countdown from,
    a very useful ability for workouts.
    Some bloat was removed for a better UI.
    - Ryan Kelsey
 */

public class MainWatch extends AppCompatActivity {
    Button Stop, Start;
    Boolean started;
    Chronometer timer;
    Chronometer lapTime;
    Animation round, stoparrow;
    /* Ryan Kelsey official certified content*/
    EditText hours, mins;         // inputs for hours and mins
    Button go;                    // Start/Reset timer button
    TextView remaining, t1, t2, laps;   // Some text
    CountDownTimer ryan;          // Timer object
    /* End of Ryan Kelsey official certified content*/
    long lap;
    long time;
    int lapcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        /* Ryan Kelsey official certified improvements*/
        // Elements are initialized here
        started=false;

        t1 = (TextView)findViewById(R.id.t1);
        t2 = (TextView)findViewById(R.id.t2);
        t1.setText("Hours");
        t2.setText("Minutes");
        hours = (EditText) findViewById(R.id.hours);
        mins  = (EditText) findViewById(R.id.mins);
        hours.setText("0");
        mins.setText("0");
        go  = (Button) findViewById(R.id.button_go);
        remaining = findViewById(R.id.remaining);
        Start = findViewById(R.id.button_Start);
        laps =(TextView) findViewById(R.id.laps);
        laps.setMovementMethod(ScrollingMovementMethod.getInstance());

        /* End of Ryan Kelsey official certified improvements*/

        Stop  = findViewById(R.id.button_stop);
        Stop.setEnabled(false);
        timer = findViewById(R.id.time);

        round = AnimationUtils.loadAnimation(this, R.anim.round_animate);
        stoparrow = AnimationUtils.loadAnimation(this, R.anim.stop_animate);
        Typeface CalibriFont = Typeface.createFromAsset(getAssets(), "fonts/CalibriFont.ttf");
        Start.setTypeface(CalibriFont);
        Stop.setTypeface(CalibriFont);


        /* Ryan Kelsey official certified improvements*/
        go.setOnClickListener(new View.OnClickListener() { //on click of go button
            @Override
            public void onClick(View view) {
                int min=0, hour=0;
                try { //attempt to cancel (reset) current timer if pressed while active
                    ryan.cancel();
                }
                catch (Exception e){
                    //hehe
                }
                try {
                    //grabbing variables from elements

                    min = Integer.parseInt(mins.getText().toString());
                    hour = Integer.parseInt(hours.getText().toString());

                    if (hour < 0 || min<0 || min>59){ //checking for invalid times
                        remaining.setText("Please enter a valid time!");
                        return;
                    }
                    else if (hour>20){ //time too large
                        remaining.setText("Please enter a smaller time!");
                        return;
                    }
                    else if (hour==0 && min==0){
                        remaining.setText("Please enter a time!");
                        return;
                    }

                    int millis = (min*60 + (hour*60)*60)*1000; //millisecond conversions
                    ryan = new CountDownTimer(millis, 1000) {
                        public void onTick(long millisUntilFinished) {//start count down
                            //set text for remaining time
                            remaining.setText("seconds remaining: " + millisUntilFinished / 1000);
                        }
                        public void onFinish() {
                            //finish text
                            remaining.setText("done!");
                        }
                    }.start();
                }
                catch (Exception e){
                    hours.setText("0");
                    mins.setText("0");
                    //some other exception is thrown
                    remaining.setText("Please enter a valid time");
                    return;
                }
            }
        });

        /* End of Ryan Kelsey official certified improvements*/

        Start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (started==false) {
                    started=true;
                    Stop.setEnabled(true);
                    Start.setText("Stop Workout");
                    Start.setBackgroundColor(0xFFFF0000);
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();// once the timer starts
                    laps.setText("");
                }
                else{
                    started=false;
                    Stop.setEnabled(false);
                    lapcount=0;
                    laps.scrollTo(0, 0);
                    Start.setText("Start Workout");
                    Start.setBackgroundColor(0xFF87ab69);;
                    timer.stop();
                }
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lapcount++;
                time = SystemClock.elapsedRealtime() - timer.getBase();
                String str = String.format("%s \n Lap%d: %02d:%02d:%03d", laps.getText(),lapcount, (time/(60*1000)),(int)(time/(1000))%60, time-(((time/1000)%60)*1000)-(time/(60*1000))*60000 );
                laps.setText(str);
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
