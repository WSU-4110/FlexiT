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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        home = (Button) findViewById(R.id.button_home);
        map = (Button) findViewById(R.id.button_map);
        workout = (Button) findViewById(R.id.button_workout);
        schedule = (Button) findViewById(R.id.button_schedule);

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
        // scheduler below 
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
            }
        });

    }
}
