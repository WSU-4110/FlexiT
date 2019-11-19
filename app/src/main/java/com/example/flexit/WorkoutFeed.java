package com.example.flexit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnAnimationEndListener;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFeed extends AppCompatActivity {

    private static final String TAG = "WorkoutFeed";

    ViewPager viewPager;
    ModelAdapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_feed);
        /* put images in the drawable folder */
        /* parameters to pass in (image, title, description) */
        models = new ArrayList<>();
        models.add(new Model(R.drawable.brochure, "Workout1", "Description 1"));
        models.add(new Model(R.drawable.sticker, "Workout2", "Description 2"));
        models.add(new Model(R.drawable.poster, "Workout3", "Description 3"));
        models.add(new Model(R.drawable.namecard, "Workout4", "Description 4"));


        adapter = new ModelAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)};

        colors = colors_temp;


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));
                }
                else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
