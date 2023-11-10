package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @BeforeEach

    //todo: 테스트 코드 더 잘만드는 방법 연구해보기
    @Test
    @DisplayName("최소 갯수 이하의 주문이 입력되는 경우, 예외가 발생한다.")
    void validateMiniMumDishCount() {
        // given
        Map<Dish, Integer> inputOrder = new HashMap<>();
        inputOrder.put(Dish.BARBECUE_RIBS, 0);
        inputOrder.put(Dish.CAESAR_SALAD, 2);

        //when & then
        Order order = new Order();
        assertThatThrownBy(() -> order.putDish(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("최대 갯수 이상의 주문이 포함되는 경우, 예외가 발생한다.")
    void validateMaximumDishCount() {
        // given
        Map<Dish, Integer> inputOrder = new HashMap<>();
        inputOrder.put(Dish.BARBECUE_RIBS, 1);
        inputOrder.put(Dish.SEAFOOD_PASTA, 1);
        inputOrder.put(Dish.CAESAR_SALAD, 21);

        //when & then
        Order order = new Order();
        assertThatThrownBy(() -> order.putDish(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("중복되는 요리가 입력되는 경우, 예외가 발생한다.")
    void validateDuplicateDish() {
        // given
        Map<Dish, Integer> inputOrder = new HashMap<>();
        inputOrder.put(Dish.BARBECUE_RIBS, 1);
        inputOrder.put(Dish.BARBECUE_RIBS, 3);
        inputOrder.put(Dish.SEAFOOD_PASTA, 2);

        // when & then
        Order order = new Order();
        assertThatThrownBy(() -> order.putDish(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료만 입력되는 경우, 예외가 발생한다.")
    void validateOnlyDrinkOrder() {
        // given
        Map<Dish, Integer> inputOrder = new HashMap<>();
        inputOrder.put(Dish.ZERO_COKE, 1);
        inputOrder.put(Dish.CHAMPAGNE, 3);

        // when & then
        Order order = new Order();
        assertThatThrownBy(() -> order.putDish(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }



}