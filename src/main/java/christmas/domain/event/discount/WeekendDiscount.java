package christmas.domain.event.discount;

import christmas.domain.order.VisitDate;

public class WeekendDiscount extends Discount {

    private static final Integer FRIDAY_WEEKEND = 5;
    private static final Integer SATURDAY_WEEKEND = 6;
    private static final Integer DISCOUNT_PRICE = 2023;
    Integer mainQuantity;

    public WeekendDiscount(VisitDate visitDate, Integer mainQuantity) {
        super(visitDate);
        this.mainQuantity = mainQuantity;
    }

    @Override
    protected boolean checkApplicability(VisitDate visitDate) {
        return isSaturday() || isFriday();
    }
    private boolean isFriday() {
        return visitDate.getWeekday() == FRIDAY_WEEKEND;
    }

    private boolean isSaturday() {
        return visitDate.getWeekday() == SATURDAY_WEEKEND;
    }

    @Override
    protected Integer calculateDiscount() {
        return mainQuantity * DISCOUNT_PRICE;

    }

}
