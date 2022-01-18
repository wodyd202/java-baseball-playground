package calculator;

import calculator.Operation;
import calculator.StringCalculator;
import calculator.StringCalculatorStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    @Test
    @DisplayName("+ 연산자를 입력받았을 경우 Operation.PLUS를 반환함")
    void charOfPlus(){
        // when
        String plus = "+";
        Operation operation = Operation.tokenOf(plus);

        // then
        assertEquals(operation, Operation.PLUS);
    }

    @Test
    @DisplayName("- 연산자를 입력받았을 경우 Operaion.MINUS를 반환함")
    void charOfMinus(){
        // when
        String minus = "-";

        // then
        Operation operation = Operation.tokenOf(minus);

        // then
        assertEquals(operation, Operation.MINUS);
    }

    @Test
    @DisplayName("/ 연산자를 입력받았을 경우 Operation.DIV를 반환함")
    void charOfDiv(){
        // when
        String div = "/";

        // then
        Operation operation = Operation.tokenOf(div);

        // then
        assertEquals(operation, Operation.DIV);
    }

    @Test
    @DisplayName("* 연산자를 입력받았을 경우 Operation.MUL을 반환함")
    void charOfMul(){
        // when
        String mul = "*";

        // then
        Operation operation = Operation.tokenOf(mul);

        // then
        assertEquals(operation, Operation.MUL);
    }

    @Test
    @DisplayName("숫자를 입력받았을 경우 NOT_OPERATION을 반환함")
    void charOfNotOperaion(){
        // when
        String number = "1";

        // then
        Operation operation = Operation.tokenOf(number);

        // then
        assertEquals(operation, Operation.NOT_OPERAION);
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
        long result = calculator.calculte(input);

        // then
        assertEquals(result, expectedResult);
    }
}
