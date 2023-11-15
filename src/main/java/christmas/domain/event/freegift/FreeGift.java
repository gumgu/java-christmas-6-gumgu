package christmas.domain.event.freegift;

import christmas.domain.event.Event;

public abstract class FreeGift implements Event {

    Integer originalPrice;

    public FreeGift(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Override
    public boolean isApply() {
        return howToDetermineApplicable(originalPrice);
    }

    protected abstract boolean howToDetermineApplicable(Integer originalPrice);

    @Override
    public Integer calculateBenefitPrice() {
        return howToCalculateBenefit();
    }

    protected abstract Integer howToCalculateBenefit();
}
