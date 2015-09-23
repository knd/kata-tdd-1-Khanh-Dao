package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class NumberExtractorTest {

    @Test
    public void testExtractorReturnsTrueIfNumberStringHasSpecifiedDelimiter() {
        NumberExtractor extractor = NumberExtractor.create("//;\n1;2");
        assertThat(extractor.hasSpecifiedDelimiter()).isTrue();
    }
    
    @Test
    public void testExtractorReturnsFalseIfNumberStringHasNoSpecifiedDelimiter() {
        NumberExtractor extractor = NumberExtractor.create("1\n2;3");
        assertThat(extractor.hasSpecifiedDelimiter()).isFalse();
    }
    
    @Test
    public void testExtractorReturnsDelimiterIfNumberStringHasSpecifiedDelimiter() {
        NumberExtractor extractor = NumberExtractor.create("//;\n1;2");
        assertThat(extractor.getSpecifiedDelimiters()).containsOnly(";");
    }

    @Test
    public void testExtractorReturnsDelimiterIfNumberStringHasSpecifiedDelimiter1() {
        NumberExtractor extractor = NumberExtractor.create("//*\n1;2");
        assertThat(extractor.getSpecifiedDelimiters()).containsOnly("*");
    }
    
    @Test
    public void testExtractorReturnsNullIfNumberStringHasNoSpecifiedDelimiter() {
        NumberExtractor extractor = NumberExtractor.create("1\n2;3");
        assertThat(extractor.getSpecifiedDelimiters()).isEmpty();
    }
    
    @Test
    public void testExtractorReturnsNumberStringPart() {
        NumberExtractor extractor = NumberExtractor.create("//;\n1;2");
        assertThat(extractor.getNumberString()).isEqualTo("1;2");
    }
    
    @Test
    public void testExtractorReturnsNumberStringPart1() {
        NumberExtractor extractor = NumberExtractor.create("1\n2;3");
        assertThat(extractor.getNumberString()).isEqualTo("1\n2;3");
    }
    
    @Test
    public void testExtractorReturnsNumberStringAsEmptyString() {
        NumberExtractor extractor = NumberExtractor.create("//;\n");
        assertThat(extractor.getNumberString()).isEmpty();
    }
    
    @Test
    public void testExtractorReturnsListOfNegativeNumbers() {
        NumberExtractor extractor = NumberExtractor.create("-1");
        List<Integer> negativeNumbers = extractor.getNegativeNumbers();
        assertThat(negativeNumbers).containsOnly(-1);
    }
    
    @Test
    public void testExtractorReturnsListOfNegativeNumbers1() {
        NumberExtractor extractor = NumberExtractor.create("-1\n-2,-3,4");
        List<Integer> negativeNumbers = extractor.getNegativeNumbers();
        assertThat(negativeNumbers).containsOnly(-1, -2, -3);
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
    
    @Test
    public void testExtractorReturnsSetOfUniqueDelimiters() {
        NumberExtractor extractor = NumberExtractor.create("//[*][%]\n1*2%3");
        Set<String> delimiters = extractor.getSpecifiedDelimiters();
        assertThat(delimiters).containsOnly("*", "%");
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
    
    @Test
    public void testExtractorCreatesDefaultNumberExtractorInstance() {
        NumberExtractor extractor = NumberExtractor.create("1\n2;3");
        assertThat(extractor).isInstanceOf(DefaultNumberExtractor.class);
    }
    
    @Test
    public void testExtractorCreatesDelimiterNumberExtractorInstance() {
        NumberExtractor extractor = NumberExtractor.create("//;\n1;2;3");
        assertThat(extractor).isInstanceOf(DelimiterNumberExtractor.class);
    }
    
    @Test
    public void testExtractorCreatesExtendedDelimiterNumberExtractorInstance() {
        NumberExtractor extractor = NumberExtractor.create("//[;]\n1;2;3");
        assertThat(extractor).isInstanceOf(ExtendedDelimiterNumberExtractor.class);
    }
    
}
