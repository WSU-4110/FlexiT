package com.example.flexit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.support.test.rule.ActivityRule;

import static org.junit.Assert.*;

public class MusicActivityTest {
//testing launch of music activity
    @Rule
    public ActivityTestRule<MusicActivity>mActivityTestRule=new ActivityTestRule<MusicActivity>(MusicActivity.class);

    private MusicActivity mActivity= null;

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){

    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}