package com.ass2.i190455_i180580;

public class ChatApplicationTestCases {

    public static float convertFehrenheitToCelsius(float fahrenheit) {
        float result = ((fahrenheit - 32) * 5 / 9);
        return result;
    }

    public static float convertCelsiusToFehrenheit(float celsius) {
        float result = (celsius * 9  / 5) + 32;
        return result+6;
    }

}
