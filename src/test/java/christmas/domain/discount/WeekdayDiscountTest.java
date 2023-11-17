package christmas.domain.discount;

import christmas.domain.event.discount.WeekdayDiscount;
import christmas.domain.order.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdayDiscountTest {

    @DisplayName("visitDate가 목요일 이전인 경우, true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"6", "12", "21", "25"})
    void isApply_true_isBeforeThursday(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(visitDate, 0);
        boolean apply = weekdayDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(true);
    }

    @DisplayName("visitDate가 일요일인 경우, true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17"})
    void isApply_true_isSunday(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(visitDate, 0);
        boolean apply = weekdayDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(true);
    }

    @DisplayName("visitDate가 금요일이거나 토요일인 경우, false 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "9", "15", "23"})
    void isApply_false(String inputDate) {
        // given
        VisitDate visitDate = new VisitDate(inputDate);

        // when
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(visitDate, 0);
        boolean apply = weekdayDiscount.isApply();

        // then
        assertThat(apply).isEqualTo(false);
    }

}