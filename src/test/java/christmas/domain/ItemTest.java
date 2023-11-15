package christmas.domain;

import christmas.domain.order.Item;
import christmas.ui.parser.ParseItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ItemTest {


    @Test
    @DisplayName("중복되는 요리가 입력되는 경우, 예외가 발생한다.")
    void validateDuplicateDish() {
        // given
        List<String[]> inputOrder = ParseItem.parseItem("해산물파스타-2,해산물파스타-1");

        // when & then
        assertThatThrownBy(() -> new Item(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("최소 갯수 이하의 주문이 입력되는 경우, 예외가 발생한다.")
    void validateMiniMumDishCount() {
        // given
        List<String[]> inputOrder = ParseItem.parseItem("해산물파스타-0");

        //when & then
        assertThatThrownBy(() -> new Item(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("최대 갯수 이상의 주문이 포함되는 경우, 예외가 발생한다.")
    void validateMaximumDishCount() {
        // given
        List<String[]> inputOrder = ParseItem.parseItem("해산물파스타-21");

        //when & then
        assertThatThrownBy(() -> new Item(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료만 입력되는 경우, 예외가 발생한다.")
    void validateOnlyDrinkOrder() {
        // given
        List<String[]> inputOrder = ParseItem.parseItem("제로콜라-2,샴페인-1");

        // when & then
        assertThatThrownBy(() -> new Item(inputOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("계산한 할인 전 총 주문금액이 정확하다.")
    void calculateOriginalPrice() {
        // given
        List<String[]> inputOrder = ParseItem.parseItem("제로콜라-1,샴페인-3,티본스테이크-3");
        // when
        Item item = new Item(inputOrder);

        // then
        assertThat(item.calculateOriginalPrice()).isEqualTo(243000);
    }

    @ParameterizedTest
    @MethodSource("generateMainDishes")
    @DisplayName("입력한 메인 메뉴 갯수와 동일한 숫자를 반환한다.")
    void getMainDishQuantity(String inputItem, Integer expectQuantity) {
        // given
        List<String[]> inputOrder = ParseItem.parseItem(inputItem);

        //when
        Item item = new Item(inputOrder);

        //then
        assertThat(item.getMainDishQuantity()).isEqualTo(expectQuantity);
    }

    static Stream<Arguments> generateMainDishes() {
        return Stream.of(
                Arguments.of("티본스테이크-3", 3),
                Arguments.of("티본스테이크-3,타파스-5,시저샐러드-1", 3),
                Arguments.of("해산물파스타-2,샴페인-3,바비큐립-1", 3),
                Arguments.of("초코케이크-1,레드와인-2", 0),
                Arguments.of("크리스마스파스타-1", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDessertDishes")
    @DisplayName("입력한 디저트 메뉴 갯수와 동일한 숫자를 반환한다.")
    void getDessertDishQuantity(String inputItem, Integer expectQuantity) {
        // given
        List<String[]> inputOrder = ParseItem.parseItem(inputItem);

        //when
        Item item = new Item(inputOrder);

        //then
        assertThat(item.getDessertDishQuantity()).isEqualTo(expectQuantity);
    }

    static Stream<Arguments> generateDessertDishes() {
        return Stream.of(
                Arguments.of("티본스테이크-3,아이스크림-2", 2),
                Arguments.of("티본스테이크-3,타파스-5,시저샐러드-1", 0),
                Arguments.of("해산물파스타-2,초코케이크-3,바비큐립-1", 3),
                Arguments.of("초코케이크-1,아이스크림-2", 3),
                Arguments.of("초코케이크-1", 1)
        );
    }

}
