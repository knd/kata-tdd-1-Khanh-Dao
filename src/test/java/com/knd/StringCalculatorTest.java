package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

public class StringCalculatorTest {

    public void testCalculatorAddsUpToZeroForEmptryString() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.add("");
        assertThat(sum).isEqualTo(0);
    }
    
}
