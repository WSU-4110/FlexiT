package com.example.schedulehome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeeklySchedule extends AppCompatActivity {

    Button Save;
    String st1, st2, st3, st4, st5, st6, st7;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);

        getSupportActionBar().setTitle("            Weekly Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SundayBox = (EditText) findViewById(R.id.SundayBox);
        MondayBox = (EditText) findViewById(R.id.MondayBox);
        TuesdayBox = (EditText) findViewById(R.id.TuesdayBox);
        WednesdayBox = (EditText) findViewById(R.id.WednesdayBox);
        ThursdayBox = (EditText) findViewById(R.id.ThursdayBox);
        FridayBox = (EditText) findViewById(R.id.FridayBox);
        SaturdayBox = (EditText) findViewById(R.id.SaturdayBox);
        Save = (Button)findViewById(R.id.BtnSave);

        //To retrieve data from Shared Preferences

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String str = prefs.getString("st1","");
        SundayBox.setText(str);

        String strr = prefs.getString("st2","");
        MondayBox.setText(strr);

        String strrr= prefs.getString("st3","");
        TuesdayBox.setText(strrr);

        String strrrr = prefs.getString("st4","");
        WednesdayBox.setText(strrrr);

        String strrrrr = prefs.getString("st5","");
        ThursdayBox.setText(strrrrr);

        String strrrrrr = prefs.getString("st6","");
        FridayBox.setText(strrrrrr);

        String strrrrrrr = prefs.getString("st7","");
        SaturdayBox.setText(strrrrrrr);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                st1 = SundayBox.getText().toString();
                st2 = MondayBox.getText().toString();
                st3 = TuesdayBox.getText().toString();
                st4 = WednesdayBox.getText().toString();
                st5 = ThursdayBox.getText().toString();
                st6 = FridayBox.getText().toString();
                st7 = SaturdayBox.getText().toString();

                //This line saves the Data
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences
                        (WeeklySchedule.this);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("st1", st1);
                editor.putString("st2", st2);
                editor.putString("st3", st3);
                editor.putString("st4", st4);
                editor.putString("st5", st5);
                editor.putString("st6", st6);
                editor.putString("st7", st7);
                editor.apply();

            }
        });
    }
}
