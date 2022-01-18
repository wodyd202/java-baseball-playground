package calculator;

import java.util.Stack;

final class StringCalculatorStack {
    private final Stack<String> stack = new Stack<>();

    boolean isEmpty() {
        return stack.isEmpty();
    }
    String peek() {
        return stack.peek();
    }

    long getResult() {
        if(peekIsOperator()){
            throw new IllegalStateException();
        }
        return Long.parseLong(stack.pop());
    }

    void push(final String token) {
        validation(token);
        stack.push(token);
    }

    private void validation(final String token) {
        Operation operation = Operation.tokenOf(token);
        if(operation.isNumber() && peekIsNumber()){
            throw new IllegalArgumentException();
        }
        if(operation.isOperator() && (stack.isEmpty() || peekIsOperator())){
            throw new IllegalArgumentException();
        }
    }

    private boolean peekIsNumber(){
        return !isEmpty() && Operation.tokenOf(peek()).isNumber();
    }

    private boolean peekIsOperator(){
        return !isEmpty() && Operation.tokenOf(peek()).isOperator();
    }

    void operation(final String targetOperend) {
        if(isEmpty()){
            push(targetOperend);
            return;
        }
        Operation operator = Operation.tokenOf(stack.pop());
        String operand = stack.pop();
        String result = operator.operation(targetOperend, operand);
        stack.push(result);
    }
}
