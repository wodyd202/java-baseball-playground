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
        int convertFirstOperand = Integer.parseInt(firstOperand);
        int convertSecondOperand = Integer.parseInt(secondOperand);
        int result = 0;
        switch (this){
            case PLUS:
                result = convertFirstOperand + convertSecondOperand;
                break;
            case MINUS:
                result = convertFirstOperand - convertSecondOperand;
                break;
            case DIV:
                verifyNotZero(convertFirstOperand);
                result = convertSecondOperand / convertFirstOperand;
                break;
            case MUL:
                result = convertFirstOperand * convertSecondOperand;
                break;
        }
        return Integer.toString(result);
    }

    private void verifyNotZero(int convertFirstOperand) {
        if(convertFirstOperand == 0){
            throw new IllegalArgumentException();
        }
    }
}
