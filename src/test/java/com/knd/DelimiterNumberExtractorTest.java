package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class DelimiterNumberExtractorTest {

    @Test
    public void testDelimiterNumberExtractorGetsSpecifiedDelimiter() {
        NumberExtractor extractor = NumberExtractor.create("//;**%\n1;**%2;**%3");
        assertThat(((DelimiterNumberExtractor) extractor).getSpecifiedDelimiter()).isEqualTo(";**%");
    }
    
    @Test 
    public void testExtractorReturnsListOfNumbers() {
        NumberExtractor extractor = NumberExtractor.create("//;\n-1;-2;3;-4");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(-1, -2, 3, -4);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbers1() {
        NumberExtractor extractor = NumberExtractor.create("//*\n-1*-2*3*-4");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(-1, -2, 3, -4);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithMixedSpecifiedDelimiterWithoutSquareBrackets() {
        NumberExtractor extractor = NumberExtractor.create("//*%;\n1*%;2*%;3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithMixedSpecifiedDelimiterWithoutSquareBrackets1() {
        NumberExtractor extractor = NumberExtractor.create("//***%;*\n1***%;*2***%;*3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
}
