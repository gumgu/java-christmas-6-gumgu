package christmas.domain.event;

import christmas.domain.badge.Badge;
import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountManager;
import christmas.domain.freegift.FreeGift;
import christmas.domain.freegift.FreeGiftManager;
import christmas.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static final Integer ZERO = 0;
    public static final Integer EVENT_APPLY_THRESHOLD = 10_000;

    private final List<Event> applicableEvents = new ArrayList<>();

    Order order;

    public EventManager(Order order) {
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

    public Badge getBadgeByBenefit() {
        return Badge.getBadgeByBenefitPrice(getTotalBenefitPrice());
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
