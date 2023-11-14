package christmas.domain.discount;

import christmas.domain.VisitDate;

public class DdayDiscount extends Discount{

    private static final Integer DISCOUNT_APPLICATION_DEADLINE = 25;
    private static final Integer DEFAULT_DISCOUNT_PRICE = 1000;
    private static final Integer PER_DAY_DISCOUNT_PRICE = 100;

    public DdayDiscount(VisitDate visitDate) {
        super(visitDate);
    }

    @Override
    protected boolean howToDetermineApplicable(VisitDate visitDate) {
        return visitDate.isBeforeIncluding(DISCOUNT_APPLICATION_DEADLINE);
    }

    @Override
    public boolean isApply() {
        return super.isApply();
    }

    @Override
    public Integer calculateBenefitPrice() {
        return super.calculateBenefitPrice();
    }

    @Override
    protected Integer howToCalculateBenefit() {
        return DEFAULT_DISCOUNT_PRICE + (PER_DAY_DISCOUNT_PRICE * (visitDate.getVisitDay() - 1));
    }
}
