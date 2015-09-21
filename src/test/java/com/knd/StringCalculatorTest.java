package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void testCalculatorSummationToZeroForEmptryString() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.add("");
        assertThat(sum).isEqualTo(0);
    }
    
    @Test
    public void testCalculatorSummationForSingleNumberString() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.add("1");
        assertThat(sum).isEqualTo(1);
    }
    
}
