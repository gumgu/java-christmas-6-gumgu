package christmas.domain.freegift;

import christmas.domain.Dish;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class FreeGiftChampagneTest {

    @DisplayName("할인전 총 주문 금액이 기준점 이상인 경우 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 140_123_000, 200_000})
    void isApply_true(Integer originalPrice){
        //given
        FreeGiftChampagne freeGiftChampagne = new FreeGiftChampagne(originalPrice);

        // when
        boolean apply = freeGiftChampagne.isApply();
        freeGiftChampagne.calculateBenefitPrice();

        // then
        assertThat(apply).isEqualTo(true);
    }

    @DisplayName("할인전 총 주문 금액이 기준점 이상인 경우, 샴페인 가격을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 140_123_000, 200_000})
    void calculateBenefitPrice_champagne(Integer originalPrice) {
        // given
        FreeGiftChampagne freeGiftChampagne = new FreeGiftChampagne(originalPrice);

        // when
        Integer benefitPrice = freeGiftChampagne.calculateBenefitPrice();

        // then
        assertThat(benefitPrice).isEqualTo(Dish.CHAMPAGNE.getPrice());
    }

    @DisplayName("할인전 총 주문 금액이 기준점 미만인 경우, true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {119_999, 0, 2000})
    void isApply_false(Integer originalPrice){
        //given
        FreeGiftChampagne freeGiftChampagne = new FreeGiftChampagne(originalPrice);

        // when
        boolean apply = freeGiftChampagne.isApply();

        // then
        assertThat(apply).isEqualTo(false);
    }

}
