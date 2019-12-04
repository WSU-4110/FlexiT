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
        insertGetUserSong.add(new getUserSong("A Change Is Gonna Come", "reta van fleet", R.raw.gretavan));
        insertGetUserSong.add(new getUserSong("Over The Hills And Far Away", "Night Wish", R.raw.nightwish));
        insertGetUserSong.add(new getUserSong("Ayushmann Khurrana ", "Tequila ", R.raw.teq));
        insertGetUserSong.add(new getUserSong("Lost", "France Ocean", R.raw.lost));
        insertGetUserSong.add(new getUserSong("Feelin you ", "Abe ", R.raw.feelingyou));
        insertGetUserSong.add(new getUserSong("Farewell ", "J cole ", R.raw.farewell));
        insertGetUserSong.add(new getUserSong("None Shall Pass ", "Aesop Rock ", R.raw.aesopr));
        insertGetUserSong.add(new getUserSong("Lost You  ", "Zeds dead ", R.raw.zedsdead));
        insertGetUserSong.add(new getUserSong("Rivers of Babylon ", "Bony M ", R.raw.babylon));
        insertGetUserSong.add(new getUserSong("Cotton Eye Joe ", "Rednex", R.raw.conttoneye));
        insertGetUserSong.add(new getUserSong("Cotton Eye Joe ", "Rednex", R.raw.conttoneye));
        insertGetUserSong.add(new getUserSong("Believer ", "Imagine Dragon", R.raw.believer));







        adapter = new UserSongAdapter(this, R.layout.content_musc, insertGetUserSong);
        songList.setAdapter(adapter);
    }
}
