package christmas.domain.discount;

import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DiscountManagerTest {

    @Test
    @DisplayName("3일인 경우")
    void discountManager_day3() {
        // given
        VisitDate visitDate = new VisitDate("3");

        // when
        DiscountManager discountManager = new DiscountManager(visitDate, 0, 0);
        List<Discount> applicableDiscounts = discountManager.findApplicableDiscount();

        // then
        System.out.println("applicableDiscounts = " + applicableDiscounts);
        assertThat(applicableDiscounts)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                new DdayDiscount(visitDate),
                                new SpecialDiscount(visitDate),
                                new WeekdayDiscount(visitDate, 0)
                        )
                );
    }

    @Test
    @DisplayName("8일인 경우")
    void discountManager_day8() {
        // given
        VisitDate visitDate = new VisitDate("8");

        // when
        DiscountManager discountManager = new DiscountManager(visitDate, 0, 0);
        List<Discount> applicableDiscounts = discountManager.findApplicableDiscount();

        // then
        System.out.println("applicableDiscounts = " + applicableDiscounts);
        assertThat(applicableDiscounts)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                new DdayDiscount(visitDate),
                                new WeekendDiscount(visitDate, 0)
                        )
                );
    }

    @Test
    @DisplayName("26일인 경우")
    void discountManager_day26() {
        // given
        VisitDate visitDate = new VisitDate("26");

        // when
        DiscountManager discountManager = new DiscountManager(visitDate, 0, 0);
        List<Discount> applicableDiscounts = discountManager.findApplicableDiscount();

        // then
        System.out.println("applicableDiscounts = " + applicableDiscounts);
        assertThat(applicableDiscounts)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                new WeekdayDiscount(visitDate, 0)
                        )
                );
    }

}
