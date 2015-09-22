package com.knd;

public class NumberExtractor {

    private static final String DELIMITER_PREFIX = "//";
    private static final int SPECIFIED_DELIMITER_INDEX = 2;

    private String numbers;

    public NumberExtractor(String numbers) {
        this.numbers = numbers;
    }

    public boolean hasSpecifiedDelimiter() {
        return numbers.startsWith(DELIMITER_PREFIX);
    }

    public String getSpecifiedDelimiter() {
        return String.valueOf(numbers.charAt(SPECIFIED_DELIMITER_INDEX));
    }

}