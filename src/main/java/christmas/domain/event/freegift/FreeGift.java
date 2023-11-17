package christmas.domain.event.freegift;

import christmas.domain.event.Event;

public abstract class FreeGift implements Event {

    Integer originalPrice;

    public FreeGift(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Override
    public boolean isApply() {
        return checkApplicability(originalPrice);
    }

    protected abstract boolean checkApplicability(Integer originalPrice);

    @Override
    public Integer calculateBenefitPrice() {
        return calculateFreeGiftPrice();
    }

    protected abstract Integer calculateFreeGiftPrice();
}
