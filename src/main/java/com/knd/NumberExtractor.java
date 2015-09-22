package com.knd;

import java.util.LinkedList;
import java.util.List;

public class NumberExtractor {

    private static final String DELIMITER_PREFIX = "//";
    private static final int SPECIFIED_DELIMITER_INDEX = 2;
    private static final int NEW_LINE_STR_LENGTH = 2;

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
        if (hasSpecifiedDelimiter()) {
            return numbers.substring(SPECIFIED_DELIMITER_INDEX + NEW_LINE_STR_LENGTH);
        }
        return numbers;
    }

    public List<Integer> getNumbers() {
        List<Integer> numbers = new LinkedList<Integer>();
        String delimiterPattern = hasSpecifiedDelimiter() ? getSpecifiedDelimiter() : StringCalculator.DELIMITER_DEFAULT_PATTERN;
        for (String number : getNumberString().split(delimiterPattern)) {
            numbers.add(Integer.valueOf(number));
        }
        return numbers;
    }
    
    public List<Integer> getNegativeNumbers() {
        List<Integer> negativeNumbers = new LinkedList<Integer>();
        for (Integer number : getNumbers()) {
            if (number < 0) {
                negativeNumbers.add(number);
            }
        }
        return negativeNumbers;
    }

}
