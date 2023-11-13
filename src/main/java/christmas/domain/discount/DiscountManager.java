package christmas.domain.discount;

import christmas.domain.VisitDate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscountManager {

    private final List<Discount> discountList = new ArrayList<>();

    public DiscountManager() {
        initDiscountManager();
    }

    private void initDiscountManager() {
        discountList.add(new DdayDiscount());
        discountList.add(new SpecialDiscount());
        discountList.add(new WeekdayDiscount());
        discountList.add(new WeekendDiscount());
    }

    public List<Discount> calculateApplicableDiscount(VisitDate visitDate) {
        final List<Discount> applicableDiscounts = new ArrayList<>();

        for (Discount discount : discountList) {
            if (discount.isApply(visitDate)) {
                applicableDiscounts.add(discount);
            }
        }

        return Collections.unmodifiableList(applicableDiscounts);
    }

}
