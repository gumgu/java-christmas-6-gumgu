package christmas.domain;

import christmas.domain.order.Dish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DishTest {

    @ParameterizedTest
    @ValueSource(strings = {"김치우동", "명란파스타", "오렌지주스", ""})
    @DisplayName("목록에 없는 요리의 이름을 검증하는 경우, 예외를 발생시킨다.")
    void validateDishName(String inputDish) {
        //when &  then
        Assertions.assertThatThrownBy(() -> Dish.validateDishName(inputDish))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}