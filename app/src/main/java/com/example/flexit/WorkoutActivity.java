package com.example.flexit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutActivity extends AppCompatActivity
{
    private Button weightedButton;
    private Button unweightedButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        weightedButton = (Button) findViewById(R.id.button_weighted);
        unweightedButton = (Button) findViewById(R.id.button_unweighted);

        weightedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openWeightedActivity();
            }
        });
        unweightedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openUnweightedAcivity();
            }
        });
    }
    public void openWeightedActivity()
    {
        Intent intent = new Intent(this,WeightedActivity.class);
        startActivity(intent);
    }
    public void openUnweightedAcivity()
    {
        Intent intent = new Intent(this,UnweightedActivity.class);
        startActivity(intent);
    }
}
