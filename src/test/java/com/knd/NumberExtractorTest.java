package com.knd;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class NumberExtractorTest {

    @Test
    public void testExtractorIsInstantiated() {
        NumberExtractor extractor = new NumberExtractor("//;\n1;2");       
    }

}
