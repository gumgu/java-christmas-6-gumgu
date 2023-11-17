package christmas.domain.event.freegift;

import christmas.domain.order.Dish;

public class FreeGiftChampagne extends FreeGift {

    public static final Integer MINIMUM_PRICE_FOR_GIFT = 120_000;

    public FreeGiftChampagne(Integer originalPrice) {
        super(originalPrice);
    }

    @Override
    protected boolean checkApplicability(Integer originalPrice) {
        return originalPrice >= MINIMUM_PRICE_FOR_GIFT;
    }

    @Override
    protected Integer calculateFreeGiftPrice() {
        return Dish.CHAMPAGNE.getPrice();
    }
}
