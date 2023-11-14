package christmas.domain.freegift;

import christmas.domain.Dish;

public class FreeGiftChampagne extends FreeGift {

    public static final Integer MINIMUM_PRICE_FOR_GIFT = 120_000;

    public FreeGiftChampagne(Integer originalPrice) {
        super(originalPrice);
    }

    @Override
    protected boolean howToDetermineApplicable(Integer originalPrice) {
        return originalPrice >= MINIMUM_PRICE_FOR_GIFT;
    }

    @Override
    protected Integer howToCalculateBenefit() {
        return Dish.CHAMPAGNE.getPrice();
    }
}
