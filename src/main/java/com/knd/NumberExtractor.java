package com.knd;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    public Set<String> getSpecifiedDelimiters() {
        Set<String> delimiters = new HashSet<String>();
        if (hasSpecifiedDelimiter()) {
            String firstLine = numbers.split("\n")[0];
            Pattern pattern = Pattern.compile("\\[(.+?)\\]");
            Matcher matcher = pattern.matcher(firstLine);
            while (matcher.find()) {
                delimiters.add(matcher.group(1));
            }
            if (delimiters.isEmpty()) {
                delimiters.add(firstLine.substring(DELIMITER_PREFIX.length(), firstLine.length()));
            }
        }
        return delimiters;
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
            String delimiterPattern = "";
            if (hasSpecifiedDelimiter()) {
                Set<String> delimiters = getSpecifiedDelimiters();
                delimiterPattern = getDelimiterPattern(delimiters);
            } else {
                delimiterPattern = StringCalculator.DELIMITER_DEFAULT_PATTERN;
            }
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
    
    private String getDelimiterPattern(Set<String> delimiters) {
        StringBuffer delimiterPattern = new StringBuffer();
        for (String delimiter : delimiters) {
            delimiterPattern.append(getEscapedPattern(delimiter));
            delimiterPattern.append("+|");
        }
        return delimiterPattern.substring(0, delimiterPattern.length() - 1);
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
