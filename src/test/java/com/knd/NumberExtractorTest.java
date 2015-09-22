package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

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
    
}
