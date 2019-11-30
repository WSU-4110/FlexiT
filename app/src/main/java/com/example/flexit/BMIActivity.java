package com.example.flexit;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


// cited reference to code is on " https://github.com/Samina019/WeightManagementAndroid/blob/8cb70e55e7f64ae338d0f0495337ba300588504d/app/src/main/java/developers/weightmanagement/BMI/BMIActivity.java"
// that was the inspiration for code
public class BMIActivity extends AppCompatActivity {

    int TempAge2 = 0, TempAge3 = 0;
            int temp_height1 = 0, temp_height3 = 0;
                    int temp_weight2 = 0, temp_weight3 = 0;
                        int gender = -1;
                            int var1 = 0, var2 = 0, var3 = 0, var4 = 0;
    float temp1 = 0, temp2 = 0, temp3 = 0;
    String Temp_Age1 = "", height1 = "", weight1 = "";

    // For the bmi window
    EditText age, height, weight;

    boolean doubleBackToExitPressedOnce = false;

    private RadioGroup group;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle(" Flexit BMI");
        // To change the color of the action Bar

        setContentView(R.layout.activity_bmi);
        group = findViewById(R.id.rg);

        //To remove the focus from the activity when the activity starts
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void getSelection(int userSelection) {
        FrameLayout frameLayout = findViewById(R.id.content);
        View view = null;
        if (userSelection == 0) {
            view = getLayoutInflater().inflate(R.layout.content_bmi, frameLayout, false);
        }
        if (userSelection == 1) {
        }
        frameLayout.removeAllViews();
        frameLayout.addView(view);
    }

    public void checkGender(View view) {
        int checkedId = group.getCheckedRadioButtonId();
        if (R.id.menButton == checkedId) {
            gender = 0;
        } else if (R.id.womenButton == checkedId) {
            gender = 1;
        }
    }



    private float bmi(int userHeight, int userWeight) {
        if (userWeight == 0 || userHeight == 0) {
            Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show();
            return 0;
        }
        double temp = (userWeight * 703.0) / (userHeight * userHeight * 1.0);
        float f = (float) temp;
        return f;
    }

    //take age from user
    private int GetAge() {
        var1 = 1;
        age = findViewById(R.id.age);
        Temp_Age1 = age.getText().toString();

        try {
            TempAge2 = Integer.parseInt(Temp_Age1);
        } catch (NumberFormatException e) {
        }
        return TempAge2;


    }

    //Take height from user
    private int Getheight() {
        var2 = 1;
        height = findViewById(R.id.height);
        height1 = height.getText().toString();
        try {
            temp_height1 = Integer.parseInt(height1);
        } catch (NumberFormatException e) {
            // Toast.makeText(this,"Only Digits Allowed",Toast.LENGTH_SHORT).show();
        }
        return temp_height1;
    }

    //take weight from user
    private int GetWeight() {
        var3 = 1;
        weight = findViewById(R.id.weight);
        weight1 = weight.getText().toString();
        try {
            temp_weight2 = Integer.parseInt(weight1);
        } catch (NumberFormatException e) {
            //Toast.makeText(this,"Only Digits Allowed",Toast.LENGTH_SHORT).show();
        }
        return temp_weight2;
    }

    @SuppressLint("SetTextI18n")
    private void DisplayMaleData(float userResult, int metrics) {
        TextView under = findViewById(R.id.underweightView);
        TextView normal = findViewById(R.id.normalView);
        TextView over = findViewById(R.id.overweightView);
        TextView obese = findViewById(R.id.obeseView);
        TextView morobese = findViewById(R.id.morobese);
        TextView tv = findViewById(R.id.resultView);
        if (metrics < 10) {
            under.setText("< 14.6");
            normal.setText("14.6 - 21.3");
            over.setText("21.3 - 25.0");
            obese.setText("> 25.0");
            morobese.setText("-");
            if (userResult == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (userResult < 14.6) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (userResult >= 14.6 && userResult < 21.3) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (userResult >= 21.3 && userResult < 25.0) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (userResult >= 25.0) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (metrics >= 10 && metrics < 15) {
            under.setText("< 16.7");
            normal.setText("16.7 - 22.5");
            over.setText("22.5 - 25.6");
            obese.setText("> 25.6");
            morobese.setText("-");
            if (userResult == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (userResult < 16.7) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (userResult >= 16.7 && userResult < 22.5) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (userResult >= 22.5 && userResult < 25.6) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (userResult >= 25.6) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (metrics >= 15 && metrics < 20) {
            under.setText("< 18.6");
            normal.setText("18.6 - 23.9");
            over.setText("23.9 -26.7");
            obese.setText("> 26.7");
            morobese.setText("-");
            if (userResult == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (userResult < 18.6) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (userResult >= 18.6 && userResult < 23.9) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (userResult >= 23.9 && userResult < 26.7) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (userResult >= 26.7) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (metrics >= 20) {
            under.setText("< 20");
            normal.setText("20 - 25");
            over.setText("25 - 30");
            obese.setText("30 - 40");
            morobese.setText("> 40");
            if (userResult == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (userResult < 20) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (userResult >= 20 && userResult < 25) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (userResult >= 25 && userResult < 30) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (userResult >= 30 && userResult < 40) {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            } else {
                tv.setText("" + userResult);
                tv.setBackgroundColor(Color.parseColor("#ff0400"));
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void DisplayFemaleData(float f, int a) {
        TextView under = findViewById(R.id.underweightView);
        TextView normal = findViewById(R.id.normalView);
        TextView over = findViewById(R.id.overweightView);
        TextView obese = findViewById(R.id.obeseView);
        TextView morobese = findViewById(R.id.morobese);
        TextView resultView = findViewById(R.id.resultView);
        if (a < 10) {
            under.setText("< 14.2");
            normal.setText("14.2 - 20.6");
            over.setText("20.6 - 23.3");
            obese.setText("> 23.3");
            morobese.setText("-");
            if (f == 0) {
                resultView.setText("--");
                resultView.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 14.2) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 14.2 && f < 20.6) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 20.6 && f < 23.3) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 23.3) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 10 && a < 15) {
            under.setText("< 15");
            normal.setText("15 - 21.4");
            over.setText("21.4 - 23.3");
            obese.setText("> 23.3");
            morobese.setText("-");
            if (f == 0) {
                resultView.setText("--");
                resultView.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 15) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 15 && f < 21.4) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 21.4 && f < 23.3) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 23.3) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 15 && a < 20) {
            under.setText("< 17.8");
            normal.setText("17.8 - 23.3");
            over.setText("23.3 - 25.6");
            obese.setText("> 25.6");
            morobese.setText("-");
            if (f == 0) {
                resultView.setText("--");
                resultView.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 17.8) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 17.8 && f < 23.3) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 23.3 && f < 25.6) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 25.6) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 20) {
            under.setText("< 19");
            normal.setText("19 - 24");
            over.setText("24 - 30");
            obese.setText("30 - 40");
            morobese.setText("> 40");
            if (f == 0) {
                resultView.setText("--");
                resultView.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 19) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 19 && f < 24) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 24 && f < 30) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 30 && f < 40) {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#ff9900"));
            } else {
                resultView.setText("" + f);
                resultView.setBackgroundColor(Color.parseColor("#ff0400"));
            }
        }
    }

    public void userResult(View view) {
        Button button = findViewById(R.id.BMIResult);
        TempAge3 = GetAge();
        temp_height3 = Getheight();
        temp_weight3 = GetWeight();
        if ((gender == 0 || gender == 1) && var1 == 1 && var2 == 1 && var3 == 1) {
            temp1 = bmi(temp_height3, temp_weight3);
            var4 = 1;
            if (gender == 0)
                DisplayMaleData(temp1, TempAge3);
            else
                DisplayFemaleData(temp1, TempAge3);
        } else if (var1 == 0 || var2 == 0 || var3 == 0) {
            Toast.makeText(this, "Choose the Required Fields", Toast.LENGTH_SHORT).show();
            TempAge3 = GetAge();
            temp_height3 = Getheight();
            temp_weight3 = GetWeight();
        } else {
            Toast.makeText(this, "Choose Gender", Toast.LENGTH_SHORT).show();
        }
    }

    public void resetResult(View view) {
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(600);
        Button button = findViewById(R.id.BMIReset);
        button.startAnimation(animation);
        if (var4 != 0) {
            TextView under = findViewById(R.id.underweightView);
            TextView normal = findViewById(R.id.normalView);
            TextView over = findViewById(R.id.overweightView);
            TextView obese = findViewById(R.id.obeseView);
            TextView morobese = findViewById(R.id.morobese);
            TempAge2 = 0;
            TempAge3 = 0;
            temp_height1 = 0;
            temp_height3 = 0;
            temp_weight2 = 0;
            temp_weight3 = 0;
            gender = -1;
            var1 = 0;
            var4 = 0;
            var2 = 0;
            var3 = 0;
            temp1 = 0;
            temp2 = 0;
            temp3 = 0;
            Temp_Age1 = "";
            height1 = "";
            weight1 = "";
            TextView t = findViewById(R.id.resultView);
            t.setText("0.0");
            t.setBackgroundColor(Color.parseColor("#FFCDCDC6"));
            group.clearCheck();
            under.setText("-");
            normal.setText("-");
            over.setText("-");
            obese.setText("-");
            morobese.setText("-");
            height.setText("");
            weight.setText("");
            age.setText("");
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
