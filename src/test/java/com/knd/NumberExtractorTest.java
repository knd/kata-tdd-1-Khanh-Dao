package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class NumberExtractorTest {

    @Test
    public void testExtractorReturnsTrueIfNumberStringHasSpecifiedDelimiter() {
        NumberExtractor extractor = new NumberExtractor("//;\n1;2");
        assertThat(extractor.hasSpecifiedDelimiter()).isTrue();
    }
    
    @Test
    public void testExtractorReturnsFalseIfNumberStringHasNoSpecifiedDelimiter() {
        NumberExtractor extractor = new NumberExtractor("1\n2;3");
        assertThat(extractor.hasSpecifiedDelimiter()).isFalse();
    }
    
    @Test
    public void testExtractorReturnsDelimiterIfNumberStringHasSpecifiedDelimiter() {
        NumberExtractor extractor = new NumberExtractor("//;\n1;2");
        assertThat(extractor.getSpecifiedDelimiter()).isEqualTo(";");
    }

    @Test
    public void testExtractorReturnsDelimiterIfNumberStringHasSpecifiedDelimiter1() {
        NumberExtractor extractor = new NumberExtractor("//*\n1;2");
        assertThat(extractor.getSpecifiedDelimiter()).isEqualTo("*");
    }
    
    @Test
    public void testExtractorReturnsNullIfNumberStringHasNoSpecifiedDelimiter() {
        NumberExtractor extractor = new NumberExtractor("1\n2;3");
        assertThat(extractor.getSpecifiedDelimiter()).isNull();
    }
    
    @Test
    public void testExtractorReturnsNumberStringPart() {
        NumberExtractor extractor = new NumberExtractor("//;\n1;2");
        assertThat(extractor.getNumberString()).isEqualTo("1;2");
    }
    
    @Test
    public void testExtractorReturnsNumberStringPart1() {
        NumberExtractor extractor = new NumberExtractor("1\n2;3");
        assertThat(extractor.getNumberString()).isEqualTo("1\n2;3");
    }
    
    @Test
    public void testExtractorReturnsNumberStringAsEmptyString() {
        NumberExtractor extractor = new NumberExtractor("//;\n");
        assertThat(extractor.getNumberString()).isEmpty();
    }
    
    @Test
    public void testExtractorReturnsListOfNegativeNumbers() {
        NumberExtractor extractor = new NumberExtractor("-1");
        List<Integer> negativeNumbers = extractor.getNegativeNumbers();
        assertThat(negativeNumbers).containsOnly(-1);
    }
    
    @Test
    public void testExtractorReturnsListOfNegativeNumbers1() {
        NumberExtractor extractor = new NumberExtractor("-1\n-2,-3,4");
        List<Integer> negativeNumbers = extractor.getNegativeNumbers();
        assertThat(negativeNumbers).containsOnly(-1, -2, -3);
    }
    
    @Test 
    public void testExtractorReturnsListOfNumbers() {
        NumberExtractor extractor = new NumberExtractor("//;\n-1;-2;3;-4");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(-1, -2, 3, -4);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbers1() {
        NumberExtractor extractor = new NumberExtractor("//*\n-1*-2*3*-4");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(-1, -2, 3, -4);
    }
    
    @Test
    public void testExtractorReturnsEmptyListOfNumbersForEmptyNumberString() {
        NumberExtractor extractor = new NumberExtractor("");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).isEmpty();
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersSmallerThan1001() {
        NumberExtractor extractor = new NumberExtractor("1\n1001,2,3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test 
    public void testExtractorReturnsListOfNumbersWithSpecifiedDelimiterInSquareBrackets() {
        NumberExtractor extractor = new NumberExtractor("//[*]\n1*2*3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithSpecifiedDelimiterInSquareBrackets1() {
        NumberExtractor extractor = new NumberExtractor("//[***]\n1***2***3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithMixedSpecifiedDelimiterWithoutSquareBrackets() {
        NumberExtractor extractor = new NumberExtractor("//*%;\n1*%;2*%;3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithMixedSpecifiedDelimiterWithoutSquareBrackets1() {
        NumberExtractor extractor = new NumberExtractor("//***%;*\n1***%;*2***%;*3");
        List<Integer> numbers = extractor.getNumbersSmallerThan1001();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsSetOfUniqueDelimiters() {
        NumberExtractor extractor = new NumberExtractor("//[*][%]\n1*2%3");
        Set<String> delimiters = extractor.getSpecifiedDelimiters();
        assertThat(delimiters).containsOnly("*", "%");
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithDifferentSpecifiedDelimiters() {
        NumberExtractor extractor = new NumberExtractor("//[*][%]\n1*2%3");
        List<Integer> numbers = extractor.getNumbers1();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithDifferentSpecifiedDelimiters1() {
        NumberExtractor extractor = new NumberExtractor("//[*][**]\n1**2*3");
        List<Integer> numbers = extractor.getNumbers1();
        assertThat(numbers).containsOnly(1, 2, 3);
    }
    
    @Test
    public void testExtractorReturnsListOfNumbersWithDifferentSpecifiedDelimiters2() {
        NumberExtractor extractor = new NumberExtractor("//[%,,*][%,*][;;][%,**][**]\n1%,,*2%,*3;;4%,**5**6");
        List<Integer> numbers = extractor.getNumbers1();
        assertThat(numbers).containsOnly(1, 2, 3, 4, 5, 6);
    }
    
}
