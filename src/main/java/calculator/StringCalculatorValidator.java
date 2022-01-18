package calculator;

class StringCalculatorValidator {
    void verifyCurrentTokenNumberOrOperator(Operator targetOperator, String token) {
        if(targetOperator.isOperator()){
            return;
        }
        if(!targetOperator.isOperator() && !isNumber(token)){
            throw new IllegalArgumentException();
        }
    }

    private boolean isNumber(String token){
        try{
            Integer.parseInt(token);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
