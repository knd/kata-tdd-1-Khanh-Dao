package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExtendedDelimiterNumberExtractorTest {
    
    @Test
    public void testExtractorReturnsSetOfUniqueDelimiters() {
        NumberExtractor extractor = NumberExtractor.create("//[*][%]\n1*2%3");
        Set<String> delimiters = ((ExtendedDelimiterNumberExtractor) extractor).getSpecifiedDelimiters();
        assertThat(delimiters).containsOnly("*", "%");
    }

    @Test 
    public void testExtractorReturnsListOfNumbersWithSpecifiedDelimiterInSquareBrackets() {
        NumberExtractor extractor = NumberExtractor.create("//[*]\n1*2*3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithSpecifiedDelimiterInSquareBrackets1() {
        NumberExtractor extractor = NumberExtractor.create("//[***]\n1***2***3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithDifferentSpecifiedDelimiters() {
        NumberExtractor extractor = NumberExtractor.create("//[*][%]\n1*2%3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithDifferentSpecifiedDelimiters1() {
        NumberExtractor extractor = NumberExtractor.create("//[*][**]\n1**2*3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithDifferentSpecifiedDelimiters2() {
        NumberExtractor extractor = NumberExtractor.create("//[%,,*][%,*][;;][%,**][**]\n1%,,*2%,*3;;4%,**5**6");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3, 4, 5, 6);
    }
    
}
