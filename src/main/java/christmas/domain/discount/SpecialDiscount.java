package christmas.domain.discount;

import christmas.domain.VisitDate;

public class SpecialDiscount implements Discount {

    private static final Integer SPECIAL_DISCOUNT_PRICE = 1000;
    private static final Integer CHRISTMAS_DAY = 25;
    private static final Integer SUNDAY_WEEKDAY = 7;

    @Override
    public boolean isApply(VisitDate visitDate) {
        return isSunday(visitDate) || isChristmas(visitDate);
    }

    private boolean isChristmas(VisitDate visitDate) {
        return visitDate.getVisitDay() == CHRISTMAS_DAY;
    }

    private boolean isSunday(VisitDate visitDate) {
        return visitDate.getWeekday().equals(SUNDAY_WEEKDAY);
    }

    public Integer calculateDiscountPrice() {
        return SPECIAL_DISCOUNT_PRICE;
    }
}
