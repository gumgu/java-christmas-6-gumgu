package christmas.ui;

import christmas.domain.order.Dish;
import christmas.domain.order.Item;
import christmas.domain.order.VisitDate;
import christmas.domain.badge.Badge;
import christmas.domain.event.discount.DdayDiscount;
import christmas.domain.event.discount.SpecialDiscount;
import christmas.domain.event.discount.WeekdayDiscount;
import christmas.domain.event.discount.WeekendDiscount;
import christmas.domain.event.Event;
import christmas.domain.event.freegift.FreeGift;
import christmas.domain.event.freegift.FreeGiftChampagne;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final Integer ZERO = 0;
    private final Map<Class<? extends Event>, String> eventMessages = new HashMap<>();

    public OutputView() {
        eventMessages.put(DdayDiscount.class, "크리스마스 디데이 할인: -");
        eventMessages.put(SpecialDiscount.class, "특별 할인: -");
        eventMessages.put(WeekendDiscount.class, "주말 할인: -");
        eventMessages.put(WeekdayDiscount.class, "평일 할인: -");
        eventMessages.put(FreeGiftChampagne.class, "증정 이벤트: -");
    }

    public void printGreeting() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printVisitDate(VisitDate visitDate) {
        System.out.println("12월 "+ visitDate.getVisitDay()
                + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printItem(Item item) {
        System.out.println("<주문 메뉴>");

        Map<Dish, Integer> orders = item.getItem();

        for (Map.Entry<Dish, Integer> entry : orders.entrySet()) {
            Dish dish = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println(dish.getName() + " " + quantity + "개");
        }

        System.out.println();
    }

    public void printOriginalPrice(Integer originalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(originalPrice +"원");
        System.out.println();
    }

    public void printEventResult(List<Event> events, Integer totalBenefitPrice) {
        if (events.size() == ZERO) {
            printNonEvent();
            return;
        }
        printFreeGift(events);
        printEventBenefit(events);
        printTotalBenefitPrice(totalBenefitPrice);
    }

    private void printNonEvent() {
        System.out.println("<증정 메뉴>");
        System.out.println("없음");
        System.out.println();
        System.out.println("<혜택 내역>");
        System.out.println("없음");
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println("0원");
        System.out.println();
    }

    private void printFreeGift(List<Event> events) {
        System.out.println("<증정 메뉴>");
        for (Event event : events) {
            if (event instanceof FreeGift) {
                System.out.println("샴페인 1개");
            }
        }
        System.out.println("없음");
        System.out.println();
    }


    private void printEventBenefit(List<Event> events) {
        System.out.println("<혜택 내역>");
        for (Event event : events) {
            System.out.println(eventMessages.get(event.getClass()) + event.calculateBenefitPrice() + "원");
        }
        System.out.println();
    }

    private void printTotalBenefitPrice(Integer totalBenefitPrice) {
        System.out.println("<총혜택 금액>");
        System.out.println(totalBenefitPrice + "원");
        System.out.println();
    }

    public void printFinalPrice(Integer finalPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(finalPrice + "원");
        System.out.println();
    }

    public void printBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getName());
    }

}
