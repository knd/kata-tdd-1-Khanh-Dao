package com.knd;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtendedDelimiterNumberExtractor extends NumberExtractor {
    
    private static final String DELIMITER_SPECFICIATION_EXTENDED_PATTERN = "\\[(.+?)\\]";

    protected ExtendedDelimiterNumberExtractor(String numbers) {
        super(numbers);
    }
    
    @Override
    public List<Integer> getNumbersSmallerThan1001() {
        List<Integer> numbers = new LinkedList<Integer>();
        String numberString = getNumberString();
        if (!numberString.isEmpty()) {
            String splitPattern = getSplitPatternForNumberString();
            numbers = getNumbersSmallerThanThreshold(numberString.split(splitPattern), DEFAULT_THRESHOLD);
        }
        return numbers;
    }
    
    protected Set<String> getSpecifiedDelimiters() {
        Set<String> delimiters = new HashSet<String>();
        String firstLine = numbers.split(NEW_LINE)[0];
        delimiters = getSpecifiedDelimiters(firstLine);
        return delimiters;
    }
    
    private Set<String> getSpecifiedDelimiters(String delimiterString) {
        Set<String> delimiters = new HashSet<String>();
        Pattern pattern = Pattern.compile(DELIMITER_SPECFICIATION_EXTENDED_PATTERN);
        Matcher matcher = pattern.matcher(delimiterString);
        while (matcher.find()) {
            delimiters.add(matcher.group(1));
        }
        return delimiters;
    }
    
    private String getSplitPatternForNumberString() {
        String splitPattern = "";
        Set<String> delimiters = getSpecifiedDelimiters();
        splitPattern = getSplitPattern(delimiters);
        return splitPattern;
    }
    
    private String getSplitPattern(Set<String> delimiters) {
        StringBuffer splitPattern = new StringBuffer();
        for (String delimiter : delimiters) {
            splitPattern.append(getEscapedPattern(delimiter));
            splitPattern.append("+|");
        }
        return splitPattern.substring(0, splitPattern.length() - 1);
    }

}
