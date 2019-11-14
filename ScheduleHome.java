package com.example.schedulehome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScheduleHome extends AppCompatActivity {
    private Button MonthlyS, WeeklyS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("            Schedule Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MonthlyS = (Button) findViewById(R.id.MonthlyS);
        MonthlyS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMonthlySchedule();
            }
        });
        WeeklyS = (Button) findViewById(R.id.WeeklyS);
        WeeklyS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeeklySchedule();
            }
        });
    }
    public void openMonthlySchedule() {
        Intent intent = new Intent(this, MonthlySchedule.class);
        startActivity(intent);
    }
    public void openWeeklySchedule() {
        Intent intent = new Intent(this, WeeklySchedule.class);
        startActivity(intent);
    }
}