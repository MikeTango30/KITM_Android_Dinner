package com.dinner.dinner;

import java.util.regex.Pattern;

public class InputValidator {

    public static boolean checkInputValidity(String input) {
        return Pattern.matches("^[a-zA-Z]{3,20}$", input);
    }

}
