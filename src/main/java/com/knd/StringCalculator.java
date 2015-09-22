package com.knd;

import java.util.List;

public class StringCalculator {

    public static final String DELIMITER_DEFAULT_PATTERN = ",|\n";

    public int add(String numbers) throws NegativeValueException {
        NumberExtractor extractor = new NumberExtractor(numbers);
        List<Integer> negativeNumbers = extractor.getNegativeNumbers();
        if (!negativeNumbers.isEmpty()) {
            throw new NegativeValueException(negativeNumbers);
        }
        int sum = 0;
        for (Integer number : extractor.getNumbers()) {
            sum += number;
        }
        return sum;
    }

}
