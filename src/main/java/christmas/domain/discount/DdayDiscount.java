package christmas.domain.discount;

import christmas.domain.VisitDate;

public class DdayDiscount implements Discount{

    private static final Integer DISCOUNT_APPLICATION_DEADLINE = 25;
    private static final Integer DEFAULT_DISCOUNT_PRICE = 1000;
    private static final Integer PER_DAY_DISCOUNT_PRICE = 100;
    private Integer visitDate = 0;

    @Override
    public boolean isApply(VisitDate visitDate) {
        //todo 이걸 어떻게 다른 곳에서 하지?
        this.visitDate = visitDate.getVisitDay();
        return visitDate.isBeforeIncluding(DISCOUNT_APPLICATION_DEADLINE);
    }

    public Integer calculateDiscountPrice() {
        return DEFAULT_DISCOUNT_PRICE + (PER_DAY_DISCOUNT_PRICE * (visitDate - 1));
    }
}
