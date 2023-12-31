package christmas.domain.event.discount;

import christmas.domain.order.VisitDate;

public class WeekdayDiscount extends Discount {

    private static final Integer SUNDAY_WEEKDAY = 7;
    private static final Integer THURSDAY_WEEKDAY = 4;
    private static final Integer DISCOUNT_PRICE = 2023;
    Integer dessertDishQuantity;

    public WeekdayDiscount(VisitDate visitDate, Integer dessertDishQuantity) {
        super(visitDate);
        this.dessertDishQuantity = dessertDishQuantity;
    }

    @Override
    protected boolean checkApplicability(VisitDate visitDate) {
        return isSunday(visitDate)
                || isBeforeThursday(visitDate);
    }

    private boolean isSunday(VisitDate visitDate) {
        return visitDate.getWeekday() == SUNDAY_WEEKDAY;
    }
    private boolean isBeforeThursday(VisitDate visitDate) {
        return visitDate.getWeekday() <= THURSDAY_WEEKDAY;
    }

    @Override
    protected Integer calculateDiscount() {
        return dessertDishQuantity * DISCOUNT_PRICE;
    }


}
