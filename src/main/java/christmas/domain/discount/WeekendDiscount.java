package christmas.domain.discount;

import christmas.domain.VisitDate;

public class WeekendDiscount implements Discount {

    private static final Integer FRIDAY_WEEKEND = 5;
    private static final Integer SATURDAY_WEEKEND = 6;
    private static final Integer DISCOUNT_PRICE = 2023;

    @Override
    public boolean isApply(VisitDate visitDate) {
        return isSaturday(visitDate) || isFriday(visitDate);
    }

    private boolean isFriday(VisitDate visitDate) {
        return visitDate.getWeekday() == FRIDAY_WEEKEND;
    }

    private boolean isSaturday(VisitDate visitDate) {
        return visitDate.getWeekday() == SATURDAY_WEEKEND;
    }

    public Integer calculateDiscountPrice(Integer quantity) {
        return quantity * DISCOUNT_PRICE;
    }
}
