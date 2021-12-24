package study;

public class StringCalculator {
    public int calculte(String fixture) {
        String[] split = fixture.split(" ");
        return calcaulteViaStringArray(split);
    }

    private int calcaulteViaStringArray(String[] split) {
        int result = Integer.parseInt(split[0]);
        String currentOperator = null;
        for (String currentStr : split) {
            if(isOperator(currentStr)){
                currentOperator = currentStr;
                continue;
            }
            result = operation(result, currentOperator, Integer.parseInt(currentStr));
        }
        return result;
    }

    private boolean isOperator(String str) {
        switch (str){
            case "+":
            case "-":
            case "/":
            case "*":
                return true;
        }
        return false;
    }

    private int operation(int result, String operator, int target) {
        if(operator == null) return result;
        switch (operator) {
            case "+": return result + target;
            case "-": return result - target;
            case "*": return result * target;
            case "/": return result / target;
        }
        return result;
    }
}
