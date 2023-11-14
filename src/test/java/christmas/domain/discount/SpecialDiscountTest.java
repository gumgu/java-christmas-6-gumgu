package christmas.domain.discount;

import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTest {

    @DisplayName("visitDate 가 범위 이내인 경우, true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17", "25"})
    void isApply_true(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate);
        boolean apply = specialDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(true);
    }

    @DisplayName("visitDate 가 범위 이내가 아닌 경우, false 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "30", "16"})
    void isApply_false(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate);
        boolean apply = specialDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(false);
    }

}