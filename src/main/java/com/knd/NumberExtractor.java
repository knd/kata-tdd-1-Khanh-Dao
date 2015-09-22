package com.knd;

public class NumberExtractor {

    private static final String DELIMITER_PREFIX = "//";
    private static final int SPECIFIED_DELIMITER_INDEX = 2;
    private static final String NEW_LINE = "\\n";

    private String numbers;

    public NumberExtractor(String numbers) {
        this.numbers = numbers;
    }

    public boolean hasSpecifiedDelimiter() {
        return numbers.startsWith(DELIMITER_PREFIX);
    }

    public String getSpecifiedDelimiter() {
        if (hasSpecifiedDelimiter()) {
            return String.valueOf(numbers.charAt(SPECIFIED_DELIMITER_INDEX));
        }
        return null;
    }

    public String getNumberString() {
        return numbers.substring(SPECIFIED_DELIMITER_INDEX + NEW_LINE.length());
    }

}
