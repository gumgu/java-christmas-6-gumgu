package christmas.domain.discount;

import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class DdayDiscountTest {

    @DisplayName("visitDate 가 범위 이내인 경우 true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "25", "15"})
    void isApply_true(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        DdayDiscount ddayDiscount = new DdayDiscount(visitDate);
        boolean apply = ddayDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(true);
    }

    @DisplayName("visitDate 가 범위가 아닌 경우 false 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"26", "31", "28"})
    void isApply_false(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        DdayDiscount ddayDiscount = new DdayDiscount(visitDate);
        boolean apply = ddayDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(false);
    }


}