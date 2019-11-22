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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;


// cited reference to code is on " https://github.com/Samina019/WeightManagementAndroid/blob/8cb70e55e7f64ae338d0f0495337ba300588504d/app/src/main/java/developers/weightmanagement/BMI/BMIActivity.java"
// that was the inspiration for code
public class BMIActivity extends AppCompatActivity {

    int TempAge2 = 0, TempAge3 = 0, temp_height1 = 0, temp_height3 = 0, temp_weight2 = 0, temp_weight3 = 0, gender = -1, var1 = 0, var2 = 0, var3 = 0, var4 = 0;
    float temp1 = 0, temp2 = 0, temp3 = 0;
    String Temp_Age1 = "", height1 = "", weight1 = "";

    // For the bmi window
    EditText age, height, weight;

    boolean doubleBackToExitPressedOnce = false;

    //For the BMI Window
    private RadioGroup rg;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

            }
            return false;
        }

    };

    //This method takes a big decimal number and convert that to the exponent from and scale is mantissa
    private static String format(BigDecimal x, int scale) {
        NumberFormat formatter = new DecimalFormat("0.0E0");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        formatter.setMinimumFractionDigits(scale);
        return formatter.format(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Initially set the BMI the main homepage
        setTitle("BMI Calculator");
        // To change the color of the action Bar

        setContentView(R.layout.activity_bmi);
        rg = findViewById(R.id.rg);

        //To remove the focus from the activity when the activity starts
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void change_layout(int selectedItem) {
        // get your outer Frame layout
        FrameLayout frameLayout = findViewById(R.id.content);
        View view = null;
        if (selectedItem == 0) {
            //Add the content bmi layout to Inflator
            view = getLayoutInflater().inflate(R.layout.content_bmi, frameLayout, false);
        }
        if (selectedItem == 1) {
            //Add the content bmi layout to Inflator
        }
        frameLayout.removeAllViews();
        frameLayout.addView(view);
    }

    public void checkGender(View view) {
        int checkedId = rg.getCheckedRadioButtonId();
        if (R.id.rb1 == checkedId) {
            gender = 0;
        } else if (R.id.rb2 == checkedId) {
            gender = 1;
        }
    }



    private float bmi(int a, int b) {
        if (b == 0 || a == 0) {
            Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show();
            return 0;
        }
        double temp = (b * 703.0) / (a * a * 1.0);
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
            // Toast.makeText(this,"Only Digits Allowed",Toast.LENGTH_SHORT).show();
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
    private void DisplayMaleData(float f, int a) {
        TextView under = findViewById(R.id.underweightView);
        TextView normal = findViewById(R.id.normalView);
        TextView over = findViewById(R.id.overweightView);
        TextView obese = findViewById(R.id.obeseView);
        TextView morobese = findViewById(R.id.morobese);
        TextView tv = findViewById(R.id.resultView);
        if (a < 10) {
            under.setText("< 14.6");
            normal.setText("14.6 - 21.3");
            over.setText("21.3 - 25.0");
            obese.setText("> 25.0");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 14.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 14.6 && f < 21.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 21.3 && f < 25.0) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 25.0) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 10 && a < 15) {
            under.setText("< 16.7");
            normal.setText("16.7 - 22.5");
            over.setText("22.5 - 25.6");
            obese.setText("> 25.6");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 16.7) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 16.7 && f < 22.5) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 22.5 && f < 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 15 && a < 20) {
            under.setText("< 18.6");
            normal.setText("18.6 - 23.9");
            over.setText("23.9 -26.7");
            obese.setText("> 26.7");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 18.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 18.6 && f < 23.9) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 23.9 && f < 26.7) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 26.7) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 20) {
            under.setText("< 20");
            normal.setText("20 - 25");
            over.setText("25 - 30");
            obese.setText("30 - 40");
            morobese.setText("> 40");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 20) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 20 && f < 25) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 25 && f < 30) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 30 && f < 40) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            } else {
                tv.setText("" + f);
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
        TextView tv = findViewById(R.id.resultView);
        if (a < 10) {
            under.setText("< 14.2");
            normal.setText("14.2 - 20.6");
            over.setText("20.6 - 23.3");
            obese.setText("> 23.3");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 14.2) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 14.2 && f < 20.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 20.6 && f < 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 10 && a < 15) {
            under.setText("< 15");
            normal.setText("15 - 21.4");
            over.setText("21.4 - 23.3");
            obese.setText("> 23.3");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 15) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 15 && f < 21.4) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 21.4 && f < 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 15 && a < 20) {
            under.setText("< 17.8");
            normal.setText("17.8 - 23.3");
            over.setText("23.3 - 25.6");
            obese.setText("> 25.6");
            morobese.setText("-");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 17.8) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 17.8 && f < 23.3) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 23.3 && f < 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 25.6) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            }
        } else if (a >= 20) {
            under.setText("< 19");
            normal.setText("19 - 24");
            over.setText("24 - 30");
            obese.setText("30 - 40");
            morobese.setText("> 40");
            if (f == 0) {
                tv.setText("--");
                tv.setBackgroundColor(Color.parseColor("#000000"));

            } else if (f < 19) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#48acda"));
            } else if (f >= 19 && f < 24) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#42cd3a"));
            } else if (f >= 24 && f < 30) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#F30BDE"));
            } else if (f >= 30 && f < 40) {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff9900"));
            } else {
                tv.setText("" + f);
                tv.setBackgroundColor(Color.parseColor("#ff0400"));
            }
        }
    }

    public void result(View view) {
        Button button = findViewById(R.id.res);
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

    public void reset(View view) {
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(600);
        Button button = findViewById(R.id.reset);
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
            rg.clearCheck();
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
