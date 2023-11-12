package christmas.domain;

import christmas.ui.utilObject.Number;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final Integer MINIMUM_ORDER_QUANTITY = 1;
    private static final Integer MAXIMUM_TOTAL_ORDER_QUANTITY = 20;

    private final Map<Dish, Integer> order;

    public Order(List<String[]> items) {
        order = new HashMap<>();

        for (String[] parts : items) {
            Integer quantity = getQuantity(parts[1]);
            Dish dishByName = Dish.findDishByName(parts[0]);

            validateMinimumDishCount(quantity);
            validateDuplicateDish(dishByName);

            order.put(dishByName, quantity);
        }

        validateMaximumTotalDishCount();
        validateOnlyDrinkOrder();

    }

    public Integer calculateOriginalPrice() {
        return order.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private void validateDuplicateDish(Dish dishByName) {
        if (order.containsKey(dishByName)) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateMinimumDishCount(Integer quantity) {
        if (quantity < MINIMUM_ORDER_QUANTITY || quantity > MAXIMUM_TOTAL_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateMaximumTotalDishCount() {
        boolean isExceedMaximumQuantity = order.values().stream()
                .mapToInt(Integer::intValue)
                .sum() > MAXIMUM_TOTAL_ORDER_QUANTITY;

        if (isExceedMaximumQuantity) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateOnlyDrinkOrder() {
        boolean isOnlyDrinkDish = order.keySet().stream()
                .map(Dish::getMenuType)
                .allMatch(menuType -> menuType.equals(MenuType.DRINK));

        if (isOnlyDrinkDish) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }


    //todo: 이거 InputView로 분리할 생각 해보기
    private Integer getQuantity(String parts) {
        Number number = new Number(parts);
        Integer quantity = number.getNumericValue();
        return quantity;
    }

}
