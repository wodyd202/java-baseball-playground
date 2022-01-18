package calculator;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

enum Operator {
    PLUS("+"),
    MINUS("-"),
    DIV("/"),
    MUL("*"),
    NOT_OPERATOR("");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    private static Map<String, Operator> operationMap = Arrays.stream(values())
                                                        .collect(toMap(Operator::getValue, Function.identity()));
    static Operator tokenOf(String value) {
        return operationMap.getOrDefault(value, NOT_OPERATOR);
    }

    boolean isOperator() {
        return !equals(NOT_OPERATOR);
    }

    String operation(String firstOperand, String secondOperand) {
        double convertFirstOperand = Double.parseDouble(firstOperand);
        double convertSecondOperand =  Double.parseDouble(secondOperand);
        switch (this){
            case PLUS:
                return resultToString(convertFirstOperand + convertSecondOperand);
            case MINUS:
                return resultToString(convertFirstOperand - convertSecondOperand);
            case DIV:
                verifyNotZero(convertFirstOperand);
                return resultToString(convertSecondOperand / convertFirstOperand);
            case MUL:
                return resultToString(convertFirstOperand * convertSecondOperand);
        }
        return null;
    }

    private static final int ZERO = 0;
    private void verifyNotZero(double convertFirstOperand) {
        if(convertFirstOperand == ZERO){
            throw new IllegalArgumentException();
        }
    }

    private String resultToString(double result){
        return Double.toString(result);
    }
}
