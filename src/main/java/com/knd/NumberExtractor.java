package com.knd;

import java.util.LinkedList;
import java.util.List;

public abstract class NumberExtractor {

    protected static final String DELIMITER_SPECIFICATION_PREFIX = "//";
    protected static final String NEW_LINE = "\n";
    protected static final int DEFAULT_THRESHOLD = 1001;
    
    private static final String DELIMITER_SPECIFICATION_EXTENDED_PREFIX = "//[";
    private static final String ESCAPE_CHARACTER_PATTERN = "\\";

    protected String numbers;
    
    public static NumberExtractor create(String numbers) {
        if (numbers.startsWith(DELIMITER_SPECIFICATION_EXTENDED_PREFIX)) {
            return new ExtendedDelimiterNumberExtractor(numbers);
        } else if (numbers.startsWith(DELIMITER_SPECIFICATION_PREFIX)) {
            return new DelimiterNumberExtractor(numbers);
        }
        return new DefaultNumberExtractor(numbers);
    }
    
    public abstract List<Integer> getNumbersSmallerThan1001();
    
    public List<Integer> getNegativeNumbers() {
        List<Integer> negativeNumbers = new LinkedList<Integer>();
        for (Integer number : getNumbersSmallerThan1001()) {
            if (number < 0) {
                negativeNumbers.add(number);
            }
        }
        return negativeNumbers;
    }

    protected NumberExtractor(String numbers) {
        this.numbers = numbers;
    }
    
    protected String getNumberString() {
        if (this instanceof DefaultNumberExtractor) {
            return numbers; 
        }
        return numbers.split(NEW_LINE, 2)[1]; // split by first occurrence of '\n'
    }
    
    protected List<Integer> getNumbersSmallerThanThreshold(String [] numbers, int threshold) {
        List<Integer> result = new LinkedList<Integer>();
        for (String number : numbers) {
            int value = Integer.valueOf(number);
            if (value < threshold) {
                result.add(value);
            }
        }
        return result;
    }

    protected String getEscapedPattern(String pattern) {
        StringBuffer escapedPattern = new StringBuffer();
        for (int i = 0; i < pattern.length(); i++) {
            char singleDelimiter = pattern.charAt(i);
            escapedPattern.append(ESCAPE_CHARACTER_PATTERN);
            escapedPattern.append(String.valueOf(singleDelimiter));
        }
        return escapedPattern.toString();
    }

}
