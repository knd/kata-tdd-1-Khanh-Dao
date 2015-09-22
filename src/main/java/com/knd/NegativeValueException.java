package com.knd;

import java.util.List;

public class NegativeValueException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public NegativeValueException(List<Integer> negativeValues) {
        super(getNegativeValueExceptionMessage(negativeValues));
    }

    private static String getNegativeValueExceptionMessage(List<Integer> negativeValues) {
        StringBuffer message = new StringBuffer();
        for (int i = 0; i < negativeValues.size(); i++) {
            message.append(String.valueOf(negativeValues.get(i)));
            if (i < negativeValues.size() - 1) {
                message.append(", ");
            }
        }
        message.append(" are not allowed");
        return message.toString();
    }

}
