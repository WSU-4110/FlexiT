package com.example.flexit;

import org.junit.Test;

import static org.junit.Assert.*;

public class BMIActivityTest {


    @Test
    public void getUserBMI() {


        int height = 100;
        int weight =100;
        int expect;

        double var = .1;

        BMIActivity bmiActivity = new BMIActivity();

        expect = bmiActivity.getUserBMI(height,weight);

        assertEquals(height,weight,expect);



    }

    @Test
    public void showUserBMI() {



        final int BMI = 25;
        String expectedBMI;
        String test ="normal bmi";
        String s = "overweight";

        BMIActivity bmiActivity = new BMIActivity();
        expectedBMI = bmiActivity.showUserBMI(BMI);

        assertEquals(s,expectedBMI);



    }


}