package calculator;

import java.util.Stack;

final class StringCalculatorStack {
    private final Stack<String> stack = new Stack<>();

    boolean isEmpty() {
        return stack.isEmpty();
    }
    String peek() {
        return isEmpty() ? null : stack.peek();
    }

    double getResult() {
        if(peekIsOperator()){
            throw new IllegalStateException();
        }
        return Double.parseDouble(stack.pop());
    }

    void push(final String token) {
        validation(token);
        stack.push(token);
    }

    private void validation(final String token) {
        if(isNumber(token) && isNumber(peek())){
            throw new IllegalArgumentException();
        }

        Operator operator = Operator.tokenOf(token);
        if(operator.isOperator() && (stack.isEmpty() || peekIsOperator())){
            throw new IllegalArgumentException();
        }
    }

    private boolean isNumber(String operator){
        try{
            Integer.parseInt(operator);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean peekIsOperator(){
        return !isEmpty() && Operator.tokenOf(peek()).isOperator();
    }

    void operation(final String targetOperend) {
        if(isEmpty()){
            push(targetOperend);
            return;
        }
        Operator operator = Operator.tokenOf(stack.pop());
        String operand = stack.pop();
        String result = operator.operation(targetOperend, operand);
        stack.push(result);
    }
}
