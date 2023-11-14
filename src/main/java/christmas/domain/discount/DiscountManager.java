package christmas.domain.discount;

import christmas.domain.VisitDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscountManager {

    private static final Integer ZERO = 0;

    private final List<Discount> allDiscounts = new ArrayList<>();
    private final List<Discount> applicableDiscounts = new ArrayList<>();

    private VisitDate visitDate;

    public DiscountManager(VisitDate visitDate,
                           Integer dessertQuantity,
                           Integer mainQuantity) {
        this.visitDate = visitDate;
        initDiscountManager(dessertQuantity, mainQuantity);
    }

    private void initDiscountManager(
            Integer dessertQuantity,
            Integer mainQuantity
    ) {
        allDiscounts.add(new DdayDiscount(visitDate));
        allDiscounts.add(new SpecialDiscount(visitDate));
        allDiscounts.add(new WeekdayDiscount(visitDate, dessertQuantity));
        allDiscounts.add(new WeekendDiscount(visitDate, mainQuantity));
    }

    public List<Discount> findApplicableDiscount() {
        for (Discount discount : allDiscounts) {
            if (discount.isApply()) {
                applicableDiscounts.add(discount);
            }
        }

        return Collections.unmodifiableList(applicableDiscounts);
    }

    public Integer getTotalDiscountPrice() {
        Integer totalDiscountPrice = ZERO;

        for (Discount discount : applicableDiscounts) {
            totalDiscountPrice += discount.calculateBenefitPrice();
        }

        return totalDiscountPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(visitDate, discount.visitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitDate);
    }

}
