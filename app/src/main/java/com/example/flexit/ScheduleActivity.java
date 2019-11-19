package com.example.flexit;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ScheduleActivity extends AppCompatActivity {

    String Sunday, Monday, Tuesday, Wednesday,
            Thursday, Friday, Saturday;

    EditText SundayBox;
    EditText MondayBox;
    EditText TuesdayBox;
    EditText WednesdayBox;
    EditText ThursdayBox;
    EditText FridayBox;
    EditText SaturdayBox;

    @Override
    protected void onCreate(Bundle saveIntstantState) {
        super.onCreate(saveIntstantState);
        setContentView(R.layout.activity_schedule);


        SundayBox = (EditText) findViewById(R.id.SundayBox);
        MondayBox = (EditText) findViewById(R.id.MondayBox);
        TuesdayBox = (EditText) findViewById(R.id.TuesdayBox);
        WednesdayBox = (EditText) findViewById(R.id.WednesdayBox);
        ThursdayBox = (EditText) findViewById(R.id.ThursdayBox);
        FridayBox = (EditText) findViewById(R.id.FridayBox);
        SaturdayBox = (EditText) findViewById(R.id.SaturdayBox);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
