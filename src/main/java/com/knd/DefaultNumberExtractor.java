package com.knd;

import java.util.LinkedList;
import java.util.List;

public class DefaultNumberExtractor extends NumberExtractor {
    
    private static final String DEFAULT_SPLIT_PATTERN = ",|\n";

    protected DefaultNumberExtractor(String numbers) {
        super(numbers);
    }
    
    @Override
    public List<Integer> getNumbersSmallerThan1001() {
        List<Integer> numbers = new LinkedList<Integer>();
        String numberString = getNumberString();
        if (!numberString.isEmpty()) {
            numbers = getNumbersSmallerThanThreshold(numberString.split(DEFAULT_SPLIT_PATTERN), DEFAULT_THRESHOLD);
        }
        return numbers;
    }

}
