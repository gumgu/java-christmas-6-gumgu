package christmas.domain.discount;

import christmas.domain.VisitDate;

public class WeekdayDiscount implements Discount {

    private static final Integer SUNDAY_WEEKDAY = 7;
    private static final Integer THURSDAY_WEEKDAY = 4;
    private static final Integer DISCOUNT_PRICE = 2023;

    @Override
    public boolean isApply(VisitDate visitDate) {
        return isSunday(visitDate)
                || isBeforeThursday(visitDate);
    }

    private boolean isSunday(VisitDate visitDate) {
        return visitDate.getWeekday() == SUNDAY_WEEKDAY;
    }
    private boolean isBeforeThursday(VisitDate visitDate) {
        return visitDate.getWeekday() <= THURSDAY_WEEKDAY;
    }

    //todo: 금액=Dish, 주문=Order, 날짜=Discount. 여러 도메인에 걸쳐있는 기능
    //어디서 다루는것이 가장 적절할까?
    public Integer calculateDiscountPrice(Integer quantity) {
        return quantity * DISCOUNT_PRICE;
    }

}
