package com.dinner.dinner;

import java.util.regex.Pattern;

public class InputValidator {
    /**
     *  Validates Login Username or Password
     *
     * @param credentials Login username or Password input
     * @return true if valid
     */
    public static boolean isCredentialsValid(String credentials) {
        return Pattern.matches("^[a-zA-Z]{3,20}$", credentials);
    }

}

//pattern for Pattern
//private static final CREDENTIALS_PATTERN = "^[a-zA-Z]{3,20}$";
