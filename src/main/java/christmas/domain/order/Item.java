package christmas.domain.order;

import christmas.ui.utilObject.Number;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final Integer MINIMUM_ORDER_QUANTITY = 1;
    private static final Integer MAXIMUM_TOTAL_ORDER_QUANTITY = 20;

    private final Map<Dish, Integer> item;

    public Item(List<String[]> items) {
        item = new HashMap<>();

        for (String[] parts : items) {
            Integer quantity = getQuantity(parts[1]);
            Dish dishByName = Dish.findDishByName(parts[0]);

            validateMinimumDishCount(quantity);
            validateDuplicateDish(dishByName);

            item.put(dishByName, quantity);
        }

        validateMaximumTotalDishCount();
        validateOnlyDrinkItem();

    }

    public Integer getDessertDishQuantity() {
        return getDishQuantityByType(MenuType.DESSERT);
    }

    public Integer getMainDishQuantity() {
        return getDishQuantityByType(MenuType.MAIN);
    }

    private Integer getDishQuantityByType(MenuType menuType) {
        return item.entrySet().stream()
                .filter(entry -> entry.getKey().getMenuType() == menuType)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public Integer calculateOriginalPrice() {
        return item.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private void validateMinimumDishCount(Integer quantity) {
        if (quantity < MINIMUM_ORDER_QUANTITY || quantity > MAXIMUM_TOTAL_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateDish(Dish dishByName) {
        if (item.containsKey(dishByName)) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateMaximumTotalDishCount() {
        boolean isExceedMaximumQuantity = item.values().stream()
                .mapToInt(Integer::intValue)
                .sum() > MAXIMUM_TOTAL_ORDER_QUANTITY;

        if (isExceedMaximumQuantity) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateOnlyDrinkItem() {
        boolean isOnlyDrinkDish = item.keySet().stream()
                .map(Dish::getMenuType)
                .allMatch(menuType -> menuType.equals(MenuType.DRINK));

        if (isOnlyDrinkDish) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private Integer getQuantity(String parts) {
        Number number = new Number(parts, ORDER_ERROR_MESSAGE);
        Integer quantity = number.getNumericValue();
        return quantity;
    }

    public Map<Dish, Integer> getItem() {
        return Collections.unmodifiableMap(item);
    }
}
