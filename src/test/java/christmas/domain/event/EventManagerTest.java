package christmas.domain.event;

import christmas.domain.order.Item;
import christmas.domain.order.VisitDate;
import christmas.domain.event.discount.DdayDiscount;
import christmas.domain.event.discount.WeekdayDiscount;
import christmas.domain.event.discount.WeekendDiscount;
import christmas.domain.event.freegift.FreeGiftChampagne;
import christmas.domain.order.OrderDTO;
import christmas.ui.parser.ParseItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EventManagerTest {

    @DisplayName("4일에 기준점(12만원) 미만을 주문하는 경우")
    @Test
    void eventManager_day4_underOriginalPrice() {
        // given
        OrderDTO order = new OrderDTO(
                new VisitDate("4"),
                new Item(ParseItem.parseItem("타파스-2,시저샐러드-3"))
        );

        // when
        EventManager eventManager = new EventManager(order);

        // then
        List<Event> applicableEvents = eventManager.getApplicableEvents();
        assertThat(applicableEvents)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                new DdayDiscount(order.getVisitDate()),
                                new WeekdayDiscount(order.getVisitDate(), 0)
                        )
                );
    }

    @DisplayName("15일에 기준점(12만원)을 주문하는 경우")
    @Test
    void eventManager_day15_equalOriginalPrice() {
        // given
        OrderDTO order = new OrderDTO(
                new VisitDate("15"),
                new Item(ParseItem.parseItem("티본스테이크-2,아이스크림-2"))
        );

        // when
        EventManager eventManager = new EventManager(order);

        // then
        List<Event> applicableEvents = eventManager.getApplicableEvents();
        assertThat(applicableEvents)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                new DdayDiscount(order.getVisitDate()),
                                new WeekendDiscount(order.getVisitDate(), 2), //메인메뉴 2개
                                new FreeGiftChampagne(order.getItem().calculateOriginalPrice())
                        )
                );
    }

    @DisplayName("27일에 기준점(12만원) 이상을 주문하는 경우")
    @Test
    void eventManager_day26_upperOriginalPrice() {
        // given
        OrderDTO order = new OrderDTO(
                new VisitDate("27"),
                new Item(ParseItem.parseItem("바비큐립-3,아이스크림-2,초코케이크-1"))
        );

        // when
        EventManager eventManager = new EventManager(order);

        // then
        List<Event> applicableEvents = eventManager.getApplicableEvents();
        assertThat(applicableEvents)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                new WeekdayDiscount(order.getVisitDate(), 3), //디저트 3개
                                new FreeGiftChampagne(order.getItem().calculateOriginalPrice())
                        )
                );
    }

}