package com.knd;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (String number : numbers.split(",")) {
            sum += Integer.valueOf(number);
        }
        return sum;
    }

}
