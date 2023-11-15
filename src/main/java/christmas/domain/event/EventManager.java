package christmas.domain.event;

import christmas.domain.event.discount.Discount;
import christmas.domain.event.discount.DiscountManager;
import christmas.domain.event.freegift.FreeGift;
import christmas.domain.event.freegift.FreeGiftManager;
import christmas.domain.order.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static final Integer ZERO = 0;
    public static final Integer EVENT_APPLY_THRESHOLD = 10_000;

    private final List<Event> applicableEvents = new ArrayList<>();

    OrderDTO order;

    public EventManager(OrderDTO order) {
        this.order = order;
    }

    public List<Event> getApplicableEvents() {
        if (isEventApplicable()) {
            addDiscounts();
            addFreeGifts();
        }

        return applicableEvents;
    }

    public Integer getTotalBenefitPrice() {
        Integer totalBenefitPrice = ZERO;

        for (Event event : applicableEvents) {
            totalBenefitPrice += event.calculateBenefitPrice();
        }

        return totalBenefitPrice;
    }

    private boolean isEventApplicable() {
        return order.getItem().calculateOriginalPrice() >= EVENT_APPLY_THRESHOLD;
    }

    private void addDiscounts() {
        DiscountManager discountManager = new DiscountManager(
                order.getVisitDate(),
                order.getItem().getDessertDishQuantity(),
                order.getItem().getMainDishQuantity()
        );

        List<Discount> applicableDiscount = discountManager.findApplicableDiscount();
        applicableEvents.addAll(applicableDiscount);
    }

    private void addFreeGifts() {
        FreeGiftManager freeGiftManager = new FreeGiftManager(
                order.getItem().calculateOriginalPrice()
        );

        List<FreeGift> applicableFreeGift = freeGiftManager.findApplicableFreeGift();
        applicableEvents.addAll(applicableFreeGift);
    }


}
