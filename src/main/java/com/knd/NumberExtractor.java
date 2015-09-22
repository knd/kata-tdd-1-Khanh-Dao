package com.knd;

public class NumberExtractor {
    
    private static final String DELIMITER_PREFIX = "//";
    
    private String numbers;

    public NumberExtractor(String numbers) {
        this.numbers = numbers;
    }

    public boolean hasSpecifiedDelimiter() {
        return numbers.startsWith(DELIMITER_PREFIX);
    }

}
