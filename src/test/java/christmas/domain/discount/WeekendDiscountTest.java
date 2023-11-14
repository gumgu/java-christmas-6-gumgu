package christmas.domain.discount;

import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeekendDiscountTest {

    @DisplayName("visitDate가 금요일이거나 토요일인 경우, true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "9", "15", "23"})
    void isApply_false(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);
        System.out.println("visitDate.getWeekday() = " + visitDate.getWeekday());

        // when
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, 0);
        boolean apply = weekendDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(true);
    }

    @DisplayName("visitDate가 평일인 경우, false 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "6", "10", "12"})
    void isApply_true_isBeforeThursday(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, 0);
        boolean apply = weekendDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(false);
    }

}
