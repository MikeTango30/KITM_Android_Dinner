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
        return Pattern.matches("^\\w{3,20}$", credentials);
    }

    public static boolean isEmailValid(String email) {
        return Pattern.matches("(\\w+.?\\w+@\\w+-?\\w+.\\w+.?\\w+)", email);
    }

}

//mokytojo pvz
//pattern for Pattern
//private static final CREDENTIALS_PATTERN = "^[a-zA-Z]{3,20}$";
// method for matching pattern
//public static boolean isCredentialsValid(String credentials) {
//    Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);
//    Matcher matcher = pattern.matcher(credentials);
//
//    return matcher.matches();
//}