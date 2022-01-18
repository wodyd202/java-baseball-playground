package calculator;

final public class StringCalculator {
    private final StringCalculatorStack stringCalculatorStack = new StringCalculatorStack();

    private static final String DELIMETER = " ";
    public long calculte(final String input) {
        String[] tokenArr = input.split(DELIMETER);
        for (String token : tokenArr) {
            operation(token);
        }
        return stringCalculatorStack.getResult();
    }

    private void operation(final String token) {
        Operation targetOperator = Operation.tokenOf(token);
        if(targetOperator.isOperator()){
            stringCalculatorStack.push(token);
            return;
        }
        stringCalculatorStack.operation(token);
    }
}
