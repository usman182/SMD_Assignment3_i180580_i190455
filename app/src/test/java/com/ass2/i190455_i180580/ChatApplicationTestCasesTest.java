package com.ass2.i190455_i180580;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChatApplicationTestCasesTest {

    @Test
    public void convertFehrenheitToCelsius() {
        float input = 212;
        float output;
        float expected = 0;
        double delta = 0.1;

        ChatApplicationTestCases chatApplicationTestCases = new ChatApplicationTestCases();

        output = chatApplicationTestCases.convertFehrenheitToCelsius(input);

        assertEquals(expected, output, delta);
    }

    @Test
    public void convertCelsiusToFehrenheit() {
        float input = 100;
        float output;
        float expected = 212;
        double delta = 0.1;

        ChatApplicationTestCases chatApplicationTestCases = new ChatApplicationTestCases();

        output = chatApplicationTestCases.convertFehrenheitToCelsius(input);

        assertEquals(expected, output, delta);
    }
}