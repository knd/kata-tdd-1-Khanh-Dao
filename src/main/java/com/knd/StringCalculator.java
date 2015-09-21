package com.knd;

public class StringCalculator {
    
    public static final String NUMBER_DELIMETER = ",";

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        int counter = 0;
        for (String number : numbers.split(NUMBER_DELIMETER)) {
            sum += Integer.valueOf(number);
            counter++;
            if (counter == 3) {
                break;
            }
        }
        return sum;
    }

}
