package christmas.domain.event.discount;

import christmas.domain.order.VisitDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
