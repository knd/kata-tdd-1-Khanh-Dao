package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Random;

import org.junit.Test;

public class StringCalculatorTest {
    
    StringCalculator calculator = new StringCalculator();

    @Test
    public void testCalculatorSummationToZeroForEmptryString() {
        int sum = calculator.add("");
        assertThat(sum).isEqualTo(0);
    }
    
    @Test
    public void testCalculatorSummationForSingleNumberString() {
        int sum = calculator.add("1");
        assertThat(sum).isEqualTo(1);
    }
    
    @Test
    public void testCalculatorSummationForSingleNumberString1() {
        int sum = calculator.add("2");
        assertThat(sum).isEqualTo(2);
    }
    
    @Test
    public void testCalculatorSummationForTwoNumberString() {
        int sum = calculator.add("1,2");
        assertThat(sum).isEqualTo(3);
    }
    
    @Test
    public void testCalculatorSummationForUnknownAmountOfNumberString() {
        StringBuffer numbers = new StringBuffer();
        Random rand = new Random();
        int amountOfNumbers = rand.nextInt(1000);        // safe enough to avoid hard code
        int expectedSum = 0;
        for (int i = 0; i < amountOfNumbers; i++) {
            expectedSum += i;
            numbers.append(String.valueOf(i));
            if (i < amountOfNumbers - 1) {
                numbers.append(StringCalculator.NUMBER_DELIMETER);
            }
        }
        int sum = calculator.add(numbers.toString());
        assertThat(sum).isEqualTo(expectedSum);
    }
    
}
