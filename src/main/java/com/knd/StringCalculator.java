package com.knd;

import java.util.List;

public class StringCalculator {

    public int add(String numbers) throws NegativeValueException {
        NumberExtractor extractor = NumberExtractor.create(numbers);
        List<Integer> negativeNumbers = extractor.getNegativeNumbers();
        if (!negativeNumbers.isEmpty()) {
            throw new NegativeValueException(negativeNumbers);
        }
        int sum = 0;
        for (Integer number : extractor.getNumbersSmallerThan1001()) {
            sum += number;
        }
        return sum;
    }

}
