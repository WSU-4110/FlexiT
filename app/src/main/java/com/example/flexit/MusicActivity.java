package com.example.flexit;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    Button play, pause, stop;
    MediaPlayer mediaPlayer;
    int pausePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        play =(Button)findViewById(R.id.play_button);
        pause=(Button)findViewById(R.id.pause_button);
        stop=(Button)findViewById(R.id.stop_button);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.play_button:
                if(mediaPlayer==null) {

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.jcole);
                    mediaPlayer.start();
                }
                else if(!mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(pausePosition);
                    mediaPlayer.start();
                }
                break;

            case R.id.pause_button:
                if(mediaPlayer!=null){
                    mediaPlayer.pause();
                    pausePosition=mediaPlayer.getCurrentPosition();

                }
                break;

            case R.id.stop_button:
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer=null;
                }
                break;


        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
