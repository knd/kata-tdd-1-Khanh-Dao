package com.knd;

public class StringCalculator {
    
    public static final String COMMA_DELIMETER = ",";
    public static final String NEW_LINE_DELIMETER = "\n";

    public int add(String numbers) {
        NumberExtractor extractor = new NumberExtractor(numbers);
        int sum = 0;
        if (extractor.hasSpecifiedDelimiter()) {
            String delimiter = extractor.getSpecifiedDelimiter();
            String numberString = extractor.getNumberString();         
            for (String number : numberString.split(delimiter)) {
                sum += Integer.valueOf(number);
            }
        } else {
            if (numbers.isEmpty()) {
                return 0;
            }
            for (String commaSplittedNumber : numbers.split(COMMA_DELIMETER)) {
                for (String newLineSplittedNumber : commaSplittedNumber.split(NEW_LINE_DELIMETER)) {
                    sum += Integer.valueOf(newLineSplittedNumber);
                }
            }
        }
        return sum;
    }

}
