package com.example.flexit;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.widget.TextView;

public class UserSongAdapter extends BaseAdapter {

    private Context MusicContent;
    private int Usermusic;
    private ArrayList<getUserSong> getUserSongArrList;
    private MediaPlayer MP;
    private Boolean validate = true;

    public UserSongAdapter(Context MusicContent, int Usermusic, ArrayList<getUserSong> getUserSongArrList) {
        this.MusicContent = MusicContent;
        this.Usermusic = Usermusic;
        this.getUserSongArrList = getUserSongArrList;
    }

    @Override// tehse are built in from array list , baseadapter is a built in adapter in android studio, everything is already pre initialized
    public int getCount() {
        return getUserSongArrList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class getView {
        ImageView playView, stopView;

        TextView nameView, songView;
    }

    @Override
    public View getView(int currentpos, View getUserView, ViewGroup view) {
        final getView getView;
        if(getUserView == null){
            getView = new getView();
            LayoutInflater layoutInflater = (LayoutInflater) MusicContent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            getUserView = layoutInflater.inflate(Usermusic, null);
            getView.nameView = (TextView) getUserView.findViewById(R.id.NameMusictext);
            getView.songView = (TextView) getUserView.findViewById(R.id.Songtext);
            getView.playView = (ImageView) getUserView.findViewById(R.id.play);
            getView.stopView = (ImageView) getUserView.findViewById(R.id.stop);

            getUserView.setTag(getView);
        } else {
            getView = (getView) getUserView.getTag();
        }

        final getUserSong currentSong = getUserSongArrList.get(currentpos);

        getView.nameView.setText(currentSong.getName());
        getView.songView.setText(currentSong.getArtist());

        // play the music for the user
        getView.playView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate){
                    MP = MediaPlayer.create(MusicContent, currentSong.getCurrentSong());// setting the music player up
                    validate = false;
                }
                if(MP.isPlaying()) {
                    MP.pause();// pauseing the music
                    getView.playView.setImageResource(R.drawable.playlogo);
                } else {
                    MP.start();// start the music
                    getView.playView.setImageResource(R.drawable.pauselogo);
                }
            }
        });

        // stop
        getView.stopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate) {
                    MP.stop();
                    MP.release();
                    validate = true;
                }
                getView.playView.setImageResource(R.drawable.playlogo);
            }
        });

        return getUserView;
    }
}
