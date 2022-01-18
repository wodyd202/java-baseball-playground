package calculator;

final public class StringCalculator {
    private final StringCalculatorValidator stringCalculatorValidator = new StringCalculatorValidator();
    private final StringCalculatorStack stringCalculatorStack = new StringCalculatorStack();

    private static final String DELIMETER = " ";
    public double calculte(final String input) {
        String[] tokenArr = input.split(DELIMETER);
        for (String token : tokenArr) {
            Operator targetOperator = Operator.tokenOf(token);
            stringCalculatorValidator.verifyCurrentTokenNumberOrOperator(targetOperator, token);

            operation(targetOperator, token);
        }
        return stringCalculatorStack.getResult();
    }

    private void operation(final Operator targetOperator, final String token) {
        if(targetOperator.isOperator()){
            stringCalculatorStack.push(token);
            return;
        }
        stringCalculatorStack.operation(token);
    }
}
