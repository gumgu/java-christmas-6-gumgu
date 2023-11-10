package christmas.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Order {

    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final Integer MINIMUM_ORDER_QUANTITY = 1;
    private static final Integer MAXIMUM_TOTAL_ORDER_QUANTITY = 20;

    private final Map<Dish, Integer> order = new HashMap<>();

    public void putDish(Map<Dish, Integer> inputOrders) {
        validateMiniMumDishCount(getQuantityStream(inputOrders));
        validateMaximumDishCount(getQuantityStream(inputOrders));
        validateDuplicateDish(getDishStreams(inputOrders));
        validateOnlyDrinkOrder(getDishStreams(inputOrders));
    }

    private static Stream<Integer> getQuantityStream(Map<Dish, Integer> inputOrders) {
        return inputOrders.values().stream();
    }

    private static Stream<Dish> getDishStreams(Map<Dish, Integer> inputOrders) {
        return inputOrders.keySet().stream();
    }

    private void validateMiniMumDishCount(Stream<Integer> quantity) {
        if (quantity.anyMatch(i -> i < MINIMUM_ORDER_QUANTITY)) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateMaximumDishCount(Stream<Integer> quantity) {
        if (quantity.mapToInt(Integer::intValue).sum() > MAXIMUM_TOTAL_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateDish(Stream<Dish> dish) {
        Set<Dish> setDish = new HashSet<>();

        if (dish.toList().size() != setDish.size()) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private void validateOnlyDrinkOrder(Stream<Dish> dish) {
        if (isOnlyDrinkDish(dish)) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private static boolean isOnlyDrinkDish(Stream<Dish> dish) {
        return dish
                .map(Dish::getMenuType)
                .allMatch(menuType -> menuType.equals(MenuType.DRINK));
    }


}
