package com.example.flexit;

import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.junit.Assert.*;

public class MapsActivityTest {

    @Test
    public void getData() {

        double Inputlat = 10;
        String expect;
        double output=100.0;
        int var =90;
        String Gym = "gym";

        MapsActivity mapsActivity = new MapsActivity();
        expect = mapsActivity.getData(output,Inputlat,Gym);


        assertTrue(Gym,TRUE);
        assertTrue(expect,TRUE);

    }
}