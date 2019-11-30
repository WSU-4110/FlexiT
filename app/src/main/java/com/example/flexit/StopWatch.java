package com.example.flexit;

import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class StopWatch extends AppCompatActivity implements View.OnClickListener {


    private long timeCountInMilliSeconds = 1 * 60000;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;

    private ProgressBar CircleStopWatch;
    private EditText enterMinute;
    private TextView textViewTime;
    private ImageView ResettimeView;
    private ImageView StoptimeView;
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);


        // method call to initialize the views
        initViews();
        // method call to initialize the listeners
        initListeners();


    }


    /**
     * method to initialize the views
     */
    private void initViews() {
        CircleStopWatch = (ProgressBar) findViewById(R.id.progressStopWatch);
        enterMinute = (EditText) findViewById(R.id.entertextMinute);
        textViewTime = (TextView) findViewById(R.id.ViewTime);
        ResettimeView = (ImageView) findViewById(R.id.ResetView);
        StoptimeView = (ImageView) findViewById(R.id.StartView);
    }

    /**
     * method to initialize the click listeners
     */
    private void initListeners() {
        ResettimeView.setOnClickListener(this);
        StoptimeView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ResetView:
                reset();
                break;
            case R.id.StartView:
                startStop();
                break;
        }
    }

    /**
     * method to resetResult count down timer
     */
    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }



    private void startStop() {
        if (timerStatus == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimer();
            // call to initialize the progress bar values
            setWatch();
            // showing the resetResult icon
            ResettimeView.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            StoptimeView.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            enterMinute.setEnabled(false);
            // changing the timer status to started
            timerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();

        } else {

            // hiding the resetResult icon
            ResettimeView.setVisibility(View.GONE);
            // changing stop icon to start icon
            StoptimeView.setImageResource(R.drawable.icon_start);
            // making edit text editable
            enterMinute.setEnabled(true);
            // changing the timer status to stopped
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();

        }

    }


    private void setTimer() {
        int time = 0;
        if (!enterMinute.getText().toString().isEmpty()) {

            time = Integer.parseInt(enterMinute.getText().toString().trim());
        } else {

            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000;
    }


    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewTime.setText(TimeFormat(millisUntilFinished));

                CircleStopWatch.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {

                textViewTime.setText(TimeFormat(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setWatch();
                // hiding the resetResult icon
                ResettimeView.setVisibility(View.GONE);
                // changing stop icon to start icon
                StoptimeView.setImageResource(R.drawable.icon_start);
                // making edit text editable
                enterMinute.setEnabled(true);
                // changing the timer status to stopped
                timerStatus = TimerStatus.STOPPED;
            }

        }.start();
        countDownTimer.start();
    }

    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    /**
     * method to set circular progress bar values
     */
    private void setWatch() {

        CircleStopWatch.setMax((int) timeCountInMilliSeconds / 1000);
        CircleStopWatch.setProgress((int) timeCountInMilliSeconds / 1000);
    }


    private String TimeFormat(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}
