package christmas.domain.event.discount;

import christmas.domain.order.VisitDate;

public class SpecialDiscount extends Discount {

    private static final Integer SPECIAL_DISCOUNT_PRICE = 1000;
    private static final Integer CHRISTMAS_DAY = 25;
    private static final Integer SUNDAY_WEEKDAY = 7;

    public SpecialDiscount(VisitDate visitDate) {
        super(visitDate);
    }

    @Override
    protected boolean howToDetermineApplicable(VisitDate visitDate) {
        return isSunday(visitDate) || isChristmas(visitDate);
    }

    private boolean isChristmas(VisitDate visitDate) {
        return visitDate.getVisitDay() == CHRISTMAS_DAY;
    }

    private boolean isSunday(VisitDate visitDate) {
        return visitDate.getWeekday().equals(SUNDAY_WEEKDAY);
    }

    @Override
    protected Integer howToCalculateBenefit() {
        return SPECIAL_DISCOUNT_PRICE;
    }

}
