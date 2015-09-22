package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Random;

import org.junit.Test;

public class StringCalculatorTest {

    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";

    StringCalculator calculator = new StringCalculator();

    @Test
    public void testCalculatorSummationToZeroForEmptryString() throws NegativeValueException {
        int sum = calculator.add("");
        assertThat(sum).isEqualTo(0);
    }

    @Test
    public void testCalculatorSummationForSingleNumberString() throws NegativeValueException {
        int sum = calculator.add("1");
        assertThat(sum).isEqualTo(1);
    }

    @Test
    public void testCalculatorSummationForSingleNumberString1() throws NegativeValueException {
        int sum = calculator.add("2");
        assertThat(sum).isEqualTo(2);
    }

    @Test
    public void testCalculatorSummationForTwoNumberString() throws NegativeValueException {
        int sum = calculator.add("1,2");
        assertThat(sum).isEqualTo(3);
    }

    @Test
    public void testCalculatorSummationForUnknownAmountOfNumberString() throws NegativeValueException {
        Random rand = new Random();
        int amountOfNumbers = rand.nextInt(1000); // safe enough to avoid hard code
        String numbers = getNumbersInputString(amountOfNumbers);
        int expectedSum = getExpectedSumOFromAmountOfNumbers(amountOfNumbers);
        int sum = calculator.add(numbers.toString());
        assertThat(sum).isEqualTo(expectedSum);
    }

    @Test
    public void testCalculatorSummationForMixedNewLineAndCommaDelimetedNumberString() throws NegativeValueException {
        int sum = calculator.add("1\n2,3");
        assertThat(sum).isEqualTo(6);
    }

    @Test
    public void testCalculatorSummationForOnlyNewLineDelimitedNumberString() throws NegativeValueException {
        int sum = calculator.add("1\n2\n3");
        assertThat(sum).isEqualTo(6);
    }

    @Test
    public void testCalculatorSummationForSpecifiedDelimiterNumberString() throws NegativeValueException {
        int sum = calculator.add("//;\n1;2");
        assertThat(sum).isEqualTo(3);
    }

    @Test
    public void testCalculatorSummationForSpecifiedDelimiterNumberString1() throws NegativeValueException {
        int sum = calculator.add("//:\n1:2");
        assertThat(sum).isEqualTo(3);
    }

    @Test(expected = NegativeValueException.class)
    public void testCalculatorSummationForNumberStringWithNegativeValues() throws NegativeValueException {
        calculator.add("-1\n2,3");
    }
    
    @Test
    public void testCalculatorSummationForNumberStringWithNegativeValuesThrowExceptionWithProperMessage() {
        try {
            calculator.add("//;\n-1;-2;3;4;-5");
        } catch (NegativeValueException exception) {
            assertThat(exception.getMessage()).isEqualTo("-1, -2, -5" + " are not allowed");
        }
    }
    
    @Test
    public void testCalculatorSummationIgnoringNumbersGreatherThan1000() throws NegativeValueException {
        int sum = calculator.add("1\n1001,2,3");
        assertThat(sum).isEqualTo(6);
    }

    private String getNumbersInputString(int amountOfNumbers) {
        StringBuffer numbers = new StringBuffer();
        for (int i = 0; i < amountOfNumbers; i++) {
            numbers.append(String.valueOf(i));
            if (i < amountOfNumbers - 1) { // not next to last element
                if (i % 2 == 0) {
                    numbers.append(COMMA);
                } else {
                    numbers.append(NEW_LINE);
                }
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
