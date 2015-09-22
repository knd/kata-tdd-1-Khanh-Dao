package com.knd;

import java.util.LinkedList;
import java.util.List;

public class NumberExtractor {

    private static final String DELIMITER_PREFIX = "//";

    private String numbers;

    public NumberExtractor(String numbers) {
        this.numbers = numbers;
    }

    public boolean hasSpecifiedDelimiter() {
        return numbers.startsWith(DELIMITER_PREFIX);
    }

    public String getSpecifiedDelimiter() {
        if (hasSpecifiedDelimiter()) {
            String firstLine = numbers.split("\n")[0];
            if (firstLine.startsWith("//[")) {
                return firstLine.substring(3, firstLine.length() - 1);
            }
            if (firstLine.startsWith("//")) {
                return firstLine.substring(DELIMITER_PREFIX.length());
            }
        }
        return null;
    }

    public String getNumberString() {
        if (hasSpecifiedDelimiter()) {
            return numbers.split("\n", 2)[1];
        }
        return numbers;
    }

    public List<Integer> getNumbersSmallerThan1001() {
        List<Integer> numbers = new LinkedList<Integer>();
        if (!getNumberString().isEmpty()) {
            String delimiterPattern = hasSpecifiedDelimiter() ? getEscapedPattern(getSpecifiedDelimiter()) : StringCalculator.DELIMITER_DEFAULT_PATTERN;
            for (String number : getNumberString().split(delimiterPattern)) {
                int num = Integer.valueOf(number);
                if (num < 1001) {
                    numbers.add(num);
                }
            }
        }
        return numbers;
    }

    public List<Integer> getNegativeNumbers() {
        List<Integer> negativeNumbers = new LinkedList<Integer>();
        for (Integer number : getNumbersSmallerThan1001()) {
            if (number < 0) {
                negativeNumbers.add(number);
            }
        }
        return negativeNumbers;
    }

    private String getEscapedPattern(String pattern) {
        StringBuffer escapedPattern = new StringBuffer();
        for (int i = 0; i < pattern.length(); i++) {
            char singleDelimiter = pattern.charAt(i);
            escapedPattern.append("\\" + String.valueOf(singleDelimiter));
        }
        return escapedPattern.toString();
    }

}
