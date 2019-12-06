package com.example.flexit;

import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void registerUser() {

        String Input = "email";
        String expect;
        String output = "password";
        int var =90;
        String Gym = "gym";


        MainActivity mainActivity = new MainActivity();
        expect = mainActivity.RegisterUser(Input,output);

        assertTrue(Input,TRUE);


    }


}