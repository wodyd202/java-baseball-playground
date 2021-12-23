package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
