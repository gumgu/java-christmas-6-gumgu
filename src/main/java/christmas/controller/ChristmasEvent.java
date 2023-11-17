package christmas.controller;

import christmas.domain.event.EventResultDTO;
import christmas.domain.order.Item;
import christmas.domain.order.VisitDate;
import christmas.domain.badge.Badge;
import christmas.domain.event.Event;
import christmas.domain.event.EventManager;
import christmas.domain.order.OrderDTO;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.List;

public class ChristmasEvent {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    
    OrderDTO order;

    public EventResultDTO doEventPlanner() {
        outputView.printGreeting();

        VisitDate visitDate = getVisitDate();
        Item item = getItem();
        Integer originalPrice = item.calculateOriginalPrice();

        printOrderInformation(visitDate, item, originalPrice);

        order = new OrderDTO(visitDate, item);

        EventManager eventManager = new EventManager(order);
        EventResultDTO eventResultDTO = runEventManager(eventManager);

        return eventResultDTO;
    }

    private VisitDate getVisitDate() {
        while (true) {
            try {
                return new VisitDate(inputView.readDate());
            } catch(IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private Item getItem() {
        while (true) {
            try {
                return new Item(inputView.readItem());
            } catch(IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void printOrderInformation(
            VisitDate visitDate,
            Item item,
            Integer originalPrice
    ) {
        outputView.printVisitDate(visitDate);
        outputView.printItem(item);
        outputView.printOriginalPrice(originalPrice);
    }

    private EventResultDTO runEventManager(EventManager eventManager) {

        List<Event> applicableEvents = eventManager.getApplicableEvents();
        Integer totalBenefitPrice = eventManager.getTotalBenefitPrice();
        outputView.printEventResult(applicableEvents, totalBenefitPrice);

        Integer finalPrice = order.getItem().calculateOriginalPrice() - totalBenefitPrice;
        outputView.printFinalPrice(finalPrice);

        Badge badge = Badge.getBadgeByBenefitPrice(totalBenefitPrice);
        outputView.printBadge(badge);

        return new EventResultDTO(finalPrice, badge, order);

    }

}
