package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    @Test
    @DisplayName("+ 연산자를 입력받았을 경우 Operator.PLUS를 반환함")
    void charOfPlus(){
        // when
        String plus = "+";
        Operator operation = Operator.tokenOf(plus);

        // then
        assertEquals(operation, Operator.PLUS);
    }

    @Test
    @DisplayName("- 연산자를 입력받았을 경우 Operaion.MINUS를 반환함")
    void charOfMinus(){
        // when
        String minus = "-";

        // then
        Operator operation = Operator.tokenOf(minus);

        // then
        assertEquals(operation, Operator.MINUS);
    }

    @Test
    @DisplayName("/ 연산자를 입력받았을 경우 Operator.DIV를 반환함")
    void charOfDiv(){
        // when
        String div = "/";

        // then
        Operator operation = Operator.tokenOf(div);

        // then
        assertEquals(operation, Operator.DIV);
    }

    @Test
    @DisplayName("* 연산자를 입력받았을 경우 Operator.MUL을 반환함")
    void charOfMul(){
        // when
        String mul = "*";

        // then
        Operator operation = Operator.tokenOf(mul);

        // then
        assertEquals(operation, Operator.MUL);
    }

    @Test
    @DisplayName("숫자를 입력받았을 경우 NOT_OPERATION을 반환함")
    void charOfNotOperaion(){
        // when
        String number = "1";

        // then
        Operator operation = Operator.tokenOf(number);

        // then
        assertEquals(operation, Operator.NOT_OPERATOR);
    }

    @Test
    @DisplayName("스택이 비워져 있는 상태에서 연산자를 push할 경우 에러 발생")
    void push(){
        // given
        StringCalculatorStack stringCalculatorStack = new StringCalculatorStack();

        // when
        assertThrows(IllegalArgumentException.class,()->{
            stringCalculatorStack.push("/");
        });
    }

    @Test
    @DisplayName("연속적으로 숫자를 push할 경우 에러 발생")
    void repeatNumberPush(){
        // given
        StringCalculatorStack stringCalculatorStack = new StringCalculatorStack();
        stringCalculatorStack.push("45");

        // when
        assertThrows(IllegalArgumentException.class,()->{
            stringCalculatorStack.push("123");
        });
    }

    @Test
    @DisplayName("마지막으로 입력된 값이 연산자이기 때문에 값을 도출할 수 없을 경우 에러 발생")
    void lastPushOperator(){
        // given
        StringCalculatorStack stringCalculatorStack = new StringCalculatorStack();
        stringCalculatorStack.push("45");
        stringCalculatorStack.push("/");

        // when
        assertThrows(IllegalStateException.class,()->{
            stringCalculatorStack.getResult();
        });
    }

    @Test
    @DisplayName("연속적으로 연산자를 입력할 경우 에러 발생")
    void repeatOperatorPush(){
        // given
        StringCalculatorStack stringCalculatorStack = new StringCalculatorStack();
        stringCalculatorStack.push("45");
        stringCalculatorStack.push("+");

        // when
        assertThrows(IllegalArgumentException.class,()->{
            stringCalculatorStack.push("-");
        });
    }

    @Test
    @DisplayName("0으로 나눌 경우 에러")
    void divZero(){
        // given
        Operator operator = Operator.DIV;

        // when
        assertThrows(IllegalArgumentException.class,()->{
            operator.operation("0", "1");
        });
    }

    @Test
    @DisplayName("입력 받은 토큰이 숫자도 아니고 연산자도 아닐 경우 에러 발생")
    void inputNotOperatorAndNumber(){
        // when
        StringCalculatorValidator stringCalculatorValidator = new StringCalculatorValidator();
        assertThrows(IllegalArgumentException.class, ()->{
            stringCalculatorValidator.verifyCurrentTokenNumberOrOperator(Operator.NOT_OPERATOR, "notNumber");
        });
    }

    StringCalculator calculator = new StringCalculator();
    @ParameterizedTest
    @CsvSource(value = {
            "2 + 3 * 4 / 2:10",
            "2 * 0 * 2:0",
            "100 * 200:20000",
            "0 / 2:0",
    }, delimiter = ':')
    void calculte(String input, int expectedResult){
        // when
        double result = calculator.calculte(input);

        // then
        assertEquals(result, expectedResult);
    }
}
