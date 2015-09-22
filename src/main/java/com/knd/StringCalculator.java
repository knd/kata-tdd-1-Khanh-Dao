package com.knd;

public class StringCalculator {

    public static final String DELIMITER_DEFAULT_PATTERN = ",|\n";

    public int add(String numbers) throws NegativeValueException {
        NumberExtractor extractor = new NumberExtractor(numbers);
        String numberString = extractor.getNumberString();
        if (!extractor.getNegativeNumbers().isEmpty()) {
            throw new NegativeValueException(extractor.getNegativeNumbers());
        }
        if (numberString.isEmpty()) {
            return 0;
        }
        int sum = 0;
        String delimiterPattern = extractor.hasSpecifiedDelimiter() ? extractor.getSpecifiedDelimiter() : DELIMITER_DEFAULT_PATTERN;
        for (String number : numberString.split(delimiterPattern)) {
            sum += Integer.valueOf(number);
        }
        return sum;
    }

}
