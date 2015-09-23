package com.knd;

import java.util.LinkedList;
import java.util.List;

public class DelimiterNumberExtractor extends NumberExtractor {

    protected DelimiterNumberExtractor(String numbers) {
        super(numbers);
    }
    
    @Override
    public List<Integer> getNumbersSmallerThan1001() {
        List<Integer> numbers = new LinkedList<Integer>();
        String numberString = getNumberString();
        if (!numberString.isEmpty()) {
            String splitPattern = getEscapedPattern(getSpecifiedDelimiter());
            numbers = getNumbersSmallerThanThreshold(numberString.split(splitPattern), 1001);
        }
        return numbers;
    }
    
    protected String getSpecifiedDelimiter() {
        String firstLine = numbers.split(NEW_LINE)[0];
        return firstLine.substring(DELIMITER_SPECIFICATION_PREFIX.length());
    } 

}
