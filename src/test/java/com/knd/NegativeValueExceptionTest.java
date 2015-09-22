package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NegativeValueExceptionTest {

    @Test
    public void testNegativeValueExceptionGeneratesProperMessage() {
        List<Integer> negativeValues = Arrays.asList(-1, -2, -3);
        Exception exception = new NegativeValueException(negativeValues);
        assertThat(exception.getMessage()).isEqualTo("-1, -2, -3" + " are not allowed");
    }

}
