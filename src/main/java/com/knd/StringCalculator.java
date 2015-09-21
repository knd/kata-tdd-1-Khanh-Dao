package com.knd;

public class StringCalculator {
    
    public static final String COMMA_DELIMETER = ",";
    public static final String NEW_LINE_DELIMETER = "\n";

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (String commaSplittedNumber : numbers.split(COMMA_DELIMETER)) {
            for (String newLineSplittedNumber : commaSplittedNumber.split(NEW_LINE_DELIMETER)) {
                sum += Integer.valueOf(newLineSplittedNumber);
            }
        }
        return sum;
    }

}
