package com.example.flexit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeedActivity extends AppCompatActivity {

    private Button map;
    private Button home;
    private Button workout;
    private Button schedule;
<<<<<<< HEAD
    private  Button clock;
=======
>>>>>>> 8ab73e2ad0cb799002d84483165c325bdeb071db
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        home = (Button) findViewById(R.id.button_home);
        map = (Button) findViewById(R.id.button_map);
        workout = (Button) findViewById(R.id.button_workout);
        schedule = (Button) findViewById(R.id.button_schedule);
<<<<<<< HEAD
        clock = (Button) findViewById(R.id.button_clock);
=======
>>>>>>> 8ab73e2ad0cb799002d84483165c325bdeb071db

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(getApplicationContext(),.class));
            }
        });

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
<<<<<<< HEAD
        // scheduler below
=======
        // scheduler below 
>>>>>>> 8ab73e2ad0cb799002d84483165c325bdeb071db
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
            }
        });


        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainWatch.class));
            }
        });
    }
}
