package com.knd;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberExtractor {

    private static final String DELIMITER_SPECIFICATION_PREFIX = "//";
    private static final String DELIMITER_SPECIFICATION_EXTENDED_PREFIX = "//[";
    private static final String DELIMITER_SPECFICIATION_EXTENDED_PATTERN = "\\[(.+?)\\]";
    private static final String NEW_LINE = "\n";
    private static final String ESCAPE_CHARACTER_PATTERN = "\\";

    private String numbers;

    public NumberExtractor(String numbers) {
        this.numbers = numbers;
    }

    public boolean hasSpecifiedDelimiter() {
        return numbers.startsWith(DELIMITER_SPECIFICATION_PREFIX);
    }
    
    public Set<String> getSpecifiedDelimiters() {
        Set<String> delimiters = new HashSet<String>();
        if (hasSpecifiedDelimiter()) {
            String firstLine = numbers.split(NEW_LINE)[0];
            delimiters = getDelimiters(firstLine);
        }
        return delimiters;
    }
    
    public List<Integer> getNumbersSmallerThan1001() {
        List<Integer> numbers = new LinkedList<Integer>();
        String numberString = getNumberString();
        if (!numberString.isEmpty()) {
            String splitPattern = getSplitPatternForNumberString();
            numbers = getNumbersSmallerThanThreshold(numberString.split(splitPattern), 1001);
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
    
    protected String getNumberString() {
        if (hasSpecifiedDelimiter()) {
            return numbers.split(NEW_LINE, 2)[1]; // split by first occurrence of '\n'
        }
        return numbers;
    }
    
    private List<Integer> getNumbersSmallerThanThreshold(String [] numbers, int threshold) {
        List<Integer> result = new LinkedList<Integer>();
        for (String number : numbers) {
            int value = Integer.valueOf(number);
            if (value < threshold) {
                result.add(value);
            }
        }
        return result;
    }
    
    private String getSplitPatternForNumberString() {
        String splitPattern = "";
        if (hasSpecifiedDelimiter()) {
            Set<String> delimiters = getSpecifiedDelimiters();
            splitPattern = getSplitPattern(delimiters);
        } else {
            splitPattern = StringCalculator.DEFAULT_SPLIT_PATTERN;
        }
        return splitPattern;
    }
    
    private Set<String> getDelimiters(String delimiterString) {
        Set<String> delimiters = new HashSet<String>();
        if (delimiterString.startsWith(DELIMITER_SPECIFICATION_EXTENDED_PREFIX)) {
            Pattern pattern = Pattern.compile(DELIMITER_SPECFICIATION_EXTENDED_PATTERN);
            Matcher matcher = pattern.matcher(delimiterString);
            while (matcher.find()) {
                delimiters.add(matcher.group(1));
            }
        } else {
            delimiters.add(delimiterString.substring(DELIMITER_SPECIFICATION_PREFIX.length()));
        }
        return delimiters;
    }
    
    private String getSplitPattern(Set<String> delimiters) {
        StringBuffer splitPattern = new StringBuffer();
        for (String delimiter : delimiters) {
            splitPattern.append(getEscapedPattern(delimiter));
            splitPattern.append("+|");
        }
        return splitPattern.substring(0, splitPattern.length() - 1);
    }

    private String getEscapedPattern(String pattern) {
        StringBuffer escapedPattern = new StringBuffer();
        for (int i = 0; i < pattern.length(); i++) {
            char singleDelimiter = pattern.charAt(i);
            escapedPattern.append(ESCAPE_CHARACTER_PATTERN);
            escapedPattern.append(String.valueOf(singleDelimiter));
        }
        return escapedPattern.toString();
    }

}
