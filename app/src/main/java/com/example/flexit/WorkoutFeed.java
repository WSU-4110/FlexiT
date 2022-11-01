package com.example.flexit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.like.LikeButton;

import com.like.OnAnimationEndListener;
import com.like.OnLikeListener;


import java.util.ArrayList;
import java.util.List;

public class WorkoutFeed extends AppCompatActivity {

    //private Button back;
    private static final String TAG = "WorkoutFeed";

    ViewPager viewPager;
    ModelAdapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button button_weighted;
    int isWeighted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_feed);
        /* put images in the drawable folder */
        /* parameters to pass in (image, title, description) */

       // back = (Button)findViewById(R.id.button_back);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.unweighted1, "Wall Sit", "30 secs per set." +
                "Slowly slide your back down a wall until your thighs are parallel to the floor. " +
                "Make sure knees are directly above ankles and keep back straight."));
        models.add(new Model(R.drawable.unweighted2, "Lunge Jump", " 10 reps Rest for 60 sec: " +
                "Stand with feet together and lunge forward with right foot. " +
                "Jump straight up, propelling arms forward while keeping elbows bent." +
                "While in the air, switch legs and land in a lunge with the opposite leg forward. Repeat and continue switching legs."));
        models.add(new Model(R.drawable.weighted1, "Dumbell Bench Press", "3 Sets 10 Reps Rest for 60sec: " +
                "Lie on a bench with your feet on the floor directly underneath your knees, " +
                "holding the dumbbells above your chest. Lower them to your chest, then drive your feet hard into the floor and push the dumbbells back strongly to the start position."));
        models.add(new Model(R.drawable.weighted2, "Back Squat", "Sets 5 Time 5 Rest 90sec" +
                "Rest the bar on your back with your feet roughly shoulder-width apart, toes pointing out slightly. " +
                "Keep your spine in alignment by looking at a spot on the floor about two metres in front of you, then sit back and down as if you were aiming for a chair. " +
                "Lower until your hip crease is below your knee. As you drive back up, keep your weight on your heels."));

        adapter = new ModelAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(140,0,140,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)};

        colors = colors_temp;

        button_weighted = findViewById(R.id.button_all);
        button_weighted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                models.clear();
                models.add(new Model(R.drawable.unweighted1, "Wall Sit", "30 secs per set." +
                        "Slowly slide your back down a wall until your thighs are parallel to the floor. " +
                        "Make sure knees are directly above ankles and keep back straight."));
                models.add(new Model(R.drawable.unweighted2, "Lunge Jump", " 10 reps Rest for 60 sec: " +
                        "Stand with feet together and lunge forward with right foot. " +
                        "Jump straight up, propelling arms forward while keeping elbows bent." +
                        "While in the air, switch legs and land in a lunge with the opposite leg forward. Repeat and continue switching legs."));
                models.add(new Model(R.drawable.weighted1, "Dumbell Bench Press", "3 Sets 10 Reps Rest for 60sec: " +
                        "Lie on a bench with your feet on the floor directly underneath your knees, " +
                        "holding the dumbbells above your chest. Lower them to your chest, then drive your feet hard into the floor and push the dumbbells back strongly to the start position."));
                models.add(new Model(R.drawable.weighted2, "Back Squat", "Sets 5 Time 5 Rest 90sec" +
                        "Rest the bar on your back with your feet roughly shoulder-width apart, toes pointing out slightly. " +
                        "Keep your spine in alignment by looking at a spot on the floor about two metres in front of you, then sit back and down as if you were aiming for a chair. " +
                        "Lower until your hip crease is below your knee. As you drive back up, keep your weight on your heels."));

                adapter = new ModelAdapter(models, getApplicationContext());

                viewPager.setAdapter(adapter);

            }
        });
        button_weighted = findViewById(R.id.button_weighted);
        button_weighted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                models.clear();
                models.add(new Model(R.drawable.weighted1, "Dumbell Bench Press", "3 Sets 10 Reps Rest for 60sec: " +
                        "Lie on a bench with your feet on the floor directly underneath your knees, " +
                        "holding the dumbbells above your chest. Lower them to your chest, then drive your feet hard into the floor and push the dumbbells back strongly to the start position."));
                models.add(new Model(R.drawable.weighted2, "Back Squat", "Sets 5 Time 5 Rest 90sec" +
                        "Rest the bar on your back with your feet roughly shoulder-width apart, toes pointing out slightly. " +
                        "Keep your spine in alignment by looking at a spot on the floor about two metres in front of you, then sit back and down as if you were aiming for a chair. " +
                        "Lower until your hip crease is below your knee. As you drive back up, keep your weight on your heels."));

                adapter = new ModelAdapter(models, getApplicationContext());

                viewPager.setAdapter(adapter);

            }
        });

        button_weighted = findViewById(R.id.button_body);
        button_weighted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                models.clear();
                models.add(new Model(R.drawable.unweighted1, "Wall Sit", "30 secs per set." +
                        "Slowly slide your back down a wall until your thighs are parallel to the floor. " +
                        "Make sure knees are directly above ankles and keep back straight."));
                models.add(new Model(R.drawable.unweighted2, "Lunge Jump", " 10 reps Rest for 60 sec: " +
                        "Stand with feet together and lunge forward with right foot. " +
                        "Jump straight up, propelling arms forward while keeping elbows bent." +
                        "While in the air, switch legs and land in a lunge with the opposite leg forward. Repeat and continue switching legs."));

                adapter = new ModelAdapter(models, getApplicationContext());

                viewPager.setAdapter(adapter);

            }
        });

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
