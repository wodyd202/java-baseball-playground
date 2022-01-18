package calculator;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

enum Operation {
    PLUS("+"),
    MINUS("-"),
    DIV("/"),
    MUL("*"),
    NOT_OPERAION("");

    private String value;

    Operation(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    private static Map<String, Operation> operationMap = Arrays.stream(values())
                                                        .collect(toMap(Operation::getValue, Function.identity()));
    static Operation tokenOf(String value) {
        return operationMap.getOrDefault(value, NOT_OPERAION);
    }

    boolean isOperator() {
        return !equals(NOT_OPERAION);
    }
    boolean isNumber() {
        return !isOperator();
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
                result = convertSecondOperand / convertFirstOperand;
                break;
            case MUL:
                result = convertFirstOperand * convertSecondOperand;
                break;
        }
        return Integer.toString(result);
    }
}
