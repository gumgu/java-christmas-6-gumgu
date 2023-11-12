package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class VisitDateTest {

    @ParameterizedTest
    @ValueSource(strings = {"31", "0", "200"})
    @DisplayName("유효하지 않은 범위의 (1 ~30을 넘는) 날짜를 입력하는 경우, 예외가 발생한다.")
    void validateIsRange(String visitDate) {
        // when & then
        assertThatThrownBy(() ->new VisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-6", "가나다라", "1234567890"})
    @DisplayName("숫자가 아니거나 Integer 범위 이상의 값을 입력하는 경우, 예외가 발생한다.")
    void validateNumber(String visitDate) {
        // when & then
        assertThatThrownBy(() ->new VisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }



}