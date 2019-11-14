package com.example.flexit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AlertDialog;
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

import com.example.flexit.R;

import android.os.Bundle;



Class ObeseFsctory implements WeightFactory{
public MaleBMI Fitnessinputs() {
        return new BMIOver();
        }



        }

class BMIFactory implements WeightFactory
{
    public MaleBMI Fitnessinputs() {
        return Malefitness();
    }

    public FemaleBMI Fitnessinputs(){

        return FemaleFitness();
    }
}

public class BMI_Abstractfactory {

    private WeightFactory WeightManager;
    public static void

    {
        String userfitness = int temp[0];

        if userfitness.endsWith("Obese") {
        factory = new ObeseFactory();
    }

            else if userfitness.endsWith("UnderWeight")
        {
            factory = new UnderWeightFactory();
        }
            else if userfitness.endsWith("Normal"){

        factory = new NormalFactory()
    }
            else{
        throw toString("Invalid");

    }



    }






}package com.example.flexit;

        import androidx.appcompat.app.AppCompatActivity;
        package com.example.flexit;

        import android.annotation.SuppressLint;
        import android.graphics.Color;
        import android.os.Bundle;

        import androidx.annotation.NonNull;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import androidx.appcompat.app.AlertDialog;
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

        import com.example.flexit.R;

        import android.os.Bundle;


class MaleBMI{

    void WeightHeight();
}



class FemaleBMI {
    void WeightHeight();

}
    public Class WeightFactory{

        MaleBMI Fitnessinputs()


        FemaleBMI Fitnessinputs()

        }


public Class ObeseFsctory implements WeightFactory{
public MaleBMI Fitnessinputs() {
        return new BMIOverMale();
        }


public FemaleBMI Fitnessinputs(){


        return new BMIOverFemale();


        }


        }


public Class UnderWeight implements WeightFactory{


public MaleBMI Fitnessinputs()

        {
        return new BMIUnderMale();


        }

public FemaleBMI Fitnessinputs(){

        return new BMIUnderFemale();
        }

        }




class BMIFactory implements WeightFactory
{
    public MaleBMI Fitnessinputs() {
        return Malefitness();
    }

    public FemaleBMI Fitnessinputs(){

        return FemaleFitness();
    }
}

public class BMI_Abstractfactory {

    private WeightFactory WeightManager;
    public static void

    {
        String userfitness = int temp[0];

        if userfitness.endsWith("Obese") {
        factory = new ObeseFactory();
    }

            else if userfitness.endsWith("UnderWeight")
        {
            factory = new UnderWeightFactory();
        }
            else if userfitness.endsWith("Normal"){

        factory = new NormalFactory()
    }
            else{
        throw toString("Invalid");

    }



    }






}