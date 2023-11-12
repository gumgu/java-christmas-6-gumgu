package christmas.domain;

import christmas.ui.parser.ParseOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @BeforeEach

    //todo: 테스트 코드 더 잘만드는 방법 연구해보기
    @Test
    @DisplayName("최소 갯수 이하의 주문이 입력되는 경우, 예외가 발생한다.")
    void validateMiniMumDishCount() {
        // given
        List<String[]> inputOrder = ParseOrder.parseOrder("해산물파스타-0");

        //when & then
        assertThatThrownBy(() -> new Order(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("최대 갯수 이상의 주문이 포함되는 경우, 예외가 발생한다.")
    void validateMaximumDishCount() {
        // given
        List<String[]> inputOrder = ParseOrder.parseOrder("해산물파스타-21");

        //when & then
        assertThatThrownBy(() -> new Order(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("중복되는 요리가 입력되는 경우, 예외가 발생한다.")
    void validateDuplicateDish() {
        // given
        List<String[]> inputOrder = ParseOrder.parseOrder("해산물파스타-2,해산물파스타-1");

        // when & then
        assertThatThrownBy(() -> new Order(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료만 입력되는 경우, 예외가 발생한다.")
    void validateOnlyDrinkOrder() {
        // given
        List<String[]> inputOrder = ParseOrder.parseOrder("제로콜라-2,샴페인-1");

        // when & then
        assertThatThrownBy(() -> new Order(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("계산한 할인 전 총 주문금액이 정확하다.")
    void calculateOriginalPrice() {
        // given
        List<String[]> inputOrder = ParseOrder.parseOrder("제로콜라-1,샴페인-3,티본스테이크-3");
        // when
        Order order = new Order(inputOrder);

        // then
        assertThat(order.calculateOriginalPrice()).isEqualTo(243000);
    }

}
