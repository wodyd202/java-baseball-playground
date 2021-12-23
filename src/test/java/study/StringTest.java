package study;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {

    @Test
    void replace() {
        // given
        String fixture = "abc";

        // when
        String actual = fixture.replace("b", "d");

        // then
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    @DisplayName("문자열 1,2 를 , 기준으로 split할 경우 문자열 1과 2를 포함하는 배열이 반환됨")
    void split_1(){
        // given
        String fixture = "1,2";

        // when
        String[] split = fixture.split(",");

        // then
        assertThat(split).containsExactly("1","2");
    }

    @Test
    @DisplayName("문자열 1을 , 기준으로 split할 경우 1만 포함하는 배열이 반환됨")
    void split_2(){
        // given
        String fixture = "1";

        // when
        String[] split = fixture.split(",");

        // then
        assertThat(split).containsExactly("1");
    }

    @Test
    @DisplayName("substring을 통해 (1,2) 문자열의 괄호 제거")
    void substring(){
        // given
        String fixture = "(1,2)";

        // when
        int frontBracketIdx = fixture.indexOf("(");
        int backBracketIdx = fixture.indexOf(")");
        String result = fixture.substring(frontBracketIdx + 1, backBracketIdx);

        // then
        assertThat(result).isEqualTo("1,2");
    }
}
