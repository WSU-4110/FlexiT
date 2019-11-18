package com.example.flexit;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainWatch extends AppCompatActivity {

    Button btnStop, btnStart;
    ImageView icanchor;
    Chronometer timerHere;
    Animation roundingalone, stopiconmeter;
    Button Countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);


        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        icanchor = findViewById(R.id.icanchor);
        timerHere = findViewById(R.id.timerHere);
        Countdown = findViewById(R.id.button_countdown);
        //load animations
        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);
        stopiconmeter = AnimationUtils.loadAnimation(this, R.anim.stopiconchor);
        //import font
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        //customize  font
        btnStart.setTypeface(MMedium);
        btnStop.setTypeface(MMedium);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing Animation
                icanchor.startAnimation(roundingalone);

                //start time
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icanchor.setAnimation(stopiconmeter);

                timerHere.stop();
            }
        });

        Countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), StopWatch.class));
            }
        });


    }


}
