package com.example.flexit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andrognito.flashbar.Flashbar;

public class FeedActivity extends AppCompatActivity {

    private Button map;
    private Button home;
    private Button workout;
    private Button schedule;
    private  Button clock;
    private Button BMI;
    private Button music;
    private Flashbar flashbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        home = (Button) findViewById(R.id.button_home);
        map = (Button) findViewById(R.id.button_map);
        workout = (Button) findViewById(R.id.button_workout);
        schedule = (Button) findViewById(R.id.button_schedule);
        clock = (Button) findViewById(R.id.button_clock);
        BMI = (Button) findViewById(R.id.button_BMI);
        music =(Button)findViewById(R.id.button_music);

        flashbar = displayQuote();
        flashbar.show();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), FeedActivity.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });
        // scheduler below
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
            }
        });

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), WorkoutFeed.class));
            }
        });

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainWatch.class));
            }
        });

        BMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), BMIActivity.class));
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MusicActivity.class));
            }
        });

    }

    private Flashbar displayQuote() {
        return new Flashbar.Builder(this)
                .gravity(Flashbar.Gravity.BOTTOM)
                .title("Quote of the Day!")
                .message("This is very deep. \n -MLK")
                .backgroundDrawable(R.drawable.bg_gradient)
                .enableSwipeToDismiss()
                .build();
    }


}
