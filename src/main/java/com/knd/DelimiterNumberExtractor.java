package com.knd;

import java.util.LinkedList;
import java.util.List;

public class DelimiterNumberExtractor extends NumberExtractor {

    public DelimiterNumberExtractor(String numbers) {
        super(numbers);
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

}
