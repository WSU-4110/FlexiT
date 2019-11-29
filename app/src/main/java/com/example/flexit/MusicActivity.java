package com.example.flexit;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    private ArrayList<getUserSong> insertGetUserSong;
    private ListView songList;
    private UserSongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        songList = (ListView) findViewById(R.id.songList);
        insertGetUserSong = new ArrayList<>();
        insertGetUserSong.add(new getUserSong("baby shark", "unknown", R.raw.babyshark));
        insertGetUserSong.add(new getUserSong("GOMD", "Jcole", R.raw.jcole));
        insertGetUserSong.add(new getUserSong("Someone you loved ", "Lewis Capaldi ", R.raw.syl));
        insertGetUserSong.add(new getUserSong("Kal Ho Naa Ho", "Sonu Nigam ", R.raw.srk));

        adapter = new UserSongAdapter(this, R.layout.content_musc, insertGetUserSong);
        songList.setAdapter(adapter);
    }
}
