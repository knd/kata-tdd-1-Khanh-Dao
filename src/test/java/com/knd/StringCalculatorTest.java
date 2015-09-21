package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

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
    public void testCalculatorSummationForTwoNumbersString() {
        int sum = calculator.add("1,2");
        assertThat(sum).isEqualTo(3);
    }
    
}
