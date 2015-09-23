package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class NumberExtractorTest {
    
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
