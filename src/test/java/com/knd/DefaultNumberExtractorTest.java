package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class DefaultNumberExtractorTest {

    @Test
    public void testExtractorReturnsEmptyListOfNumbersForEmptyNumberString() {
        NumberExtractor extractor = NumberExtractor.create("");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).isEmpty();
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersSmallerThan1001() {
        NumberExtractor extractor = NumberExtractor.create("1\n1001,2,3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
}
