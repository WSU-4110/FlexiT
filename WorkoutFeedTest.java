package com.example.flexit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkoutFeedTest {

    @Rule
    public ActivityTestRule<WorkoutFeed>mActivityTestRule=new ActivityTestRule<WorkoutFeed>(WorkoutFeed.class);

    private WorkoutFeed mActivity= null;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testLaunch(){

    }
    @After
    public void tearDown() throws Exception {
    }
}