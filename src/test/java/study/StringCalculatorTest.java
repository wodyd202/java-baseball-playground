package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 문자열 계산기 테스트
 */
public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    @ParameterizedTest
    @CsvSource(value = {
            "1 + 2:3",
            "5 + 1:6",
            "0 + 0:0",
            "-1 + 0:-1",
            "54 + -1:53",
            "2 + 3 * 4 / 2:10",
    }, delimiter = ':')
    @DisplayName("일반 덧셈")
    void plus(String fixture, int result){
        // when
        int sum = calculator.calculte(fixture);

        // then
        assertThat(sum).isEqualTo(result);
    }
}
