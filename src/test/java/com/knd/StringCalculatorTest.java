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
        Random rand = new Random();
        int amountOfNumbers = rand.nextInt(1000); // safe enough to avoid hard code
        String numbers = getNumbersInputString(amountOfNumbers);
        int expectedSum = getExpectedSumOFromAmountOfNumbers(amountOfNumbers);
        int sum = calculator.add(numbers.toString());
        assertThat(sum).isEqualTo(expectedSum);
    }

    private String getNumbersInputString(int amountOfNumbers) {
        StringBuffer numbers = new StringBuffer();
        for (int i = 0; i < amountOfNumbers; i++) {
            numbers.append(String.valueOf(i));
            if (i < amountOfNumbers - 1) {
                numbers.append(StringCalculator.NUMBER_DELIMETER);
            }
        }
        return numbers.toString();
    }

    private int getExpectedSumOFromAmountOfNumbers(int amountOfNumbers) {
        int expectedSum = 0;
        for (int i = 0; i < amountOfNumbers; i++) {
            expectedSum += i;
        }
        return expectedSum;
    }

}
