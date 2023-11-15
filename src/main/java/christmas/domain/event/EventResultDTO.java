package christmas.domain.event;

import christmas.domain.badge.Badge;
import christmas.domain.order.OrderDTO;

public class EventResultDTO {

    private final static Integer ZERO = 0;

    private Integer finalPrice = ZERO;
    private Badge badge;
    private OrderDTO order;

    public EventResultDTO(
            Integer finalPrice,
            Badge badge,
            OrderDTO order
    ) {
        this.finalPrice = finalPrice;
        this.badge = badge;
        this.order = order;
    }

    public Badge getBadge() {
        return badge;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public OrderDTO getOrder() {
        return order;
    }
}
