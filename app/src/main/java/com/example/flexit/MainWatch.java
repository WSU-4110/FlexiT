package com.example.flexit;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainWatch extends AppCompatActivity {

    Button Stop, Start;
    ImageView arrow;
    Chronometer timer;
    Animation round, stoparrow;
    Button watch_countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);


        Start = findViewById(R.id.button_Start);
        Stop = findViewById(R.id.button_stop);
        arrow = findViewById(R.id.arrow_view);
        timer = findViewById(R.id.time);
        watch_countdown = findViewById(R.id.button_countdown);

        round = AnimationUtils.loadAnimation(this, R.anim.round_animate);
        stoparrow = AnimationUtils.loadAnimation(this, R.anim.stop_animate);
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        //customize  font
        Start.setTypeface(MMedium);
        Stop.setTypeface(MMedium);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing Animation
                arrow.startAnimation(round);

                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrow.setAnimation(stoparrow);

                timer.stop();
            }
        });

        watch_countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), StopWatch.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
