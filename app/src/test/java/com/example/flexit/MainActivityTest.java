package com.example.flexit;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void SuccessfulRegisterUser() {
        String expectedEmail = "mohammza@gmail.com";
        String empty = " ";
        String expectedPassword = "mo1012ha";

        assertNotEquals(expectedEmail, empty);
        assertNotEquals(expectedPassword, empty);
    }
    public void FailedRegisterUser() {
        String expectedEmail = "mohammza@gmail.com";
        String empty = " ";
        String expectedPassword = "mo1012ha";

        assertEquals(expectedEmail, empty);
        assertEquals(expectedPassword, empty);
    }

    public void SuccessTestQuote(){
        String expected = "This is deep";
        assertNotNull(expected);
    }

    public void FailedTestQuote(){
        String expected = "This is deep";
        assertEquals(expected,"");
    }
}