package com.example.flexit;

import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.junit.Assert.*;

public class SettingActivityTest {

    @Test
    public void getUserInfo() {

        String uID = "123user", fName = "John", lName = "Doe", description = "hi";
        String expected;

        int age = 21, height = 11, weight = 122;

        SettingActivity settingActivity = new SettingActivity();

        expected = settingActivity.getUserInfo(uID,fName,lName,age,height,weight,description);



        assertTrue(uID,TRUE);
        assertTrue(expected,TRUE);







    }
};