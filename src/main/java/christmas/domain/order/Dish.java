package christmas.domain.order;

import java.util.Arrays;

public enum Dish {

    // 애피타이저
    MUSHROOM_SOUP("양송이수프", MenuType.APPETIZER, 6000),
    TAPAS("타파스", MenuType.APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER, 8000),

    // 메인
    T_BONE_STEAK("티본스테이크", MenuType.MAIN, 55000),
    BARBECUE_RIBS("바비큐립", MenuType.MAIN, 54000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN, 35000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN, 25000),

    // 디저트
    CHOCO_CAKE("초코케이크", MenuType.DESSERT, 15000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5000),

    // 음료
    ZERO_COKE("제로콜라", MenuType.DRINK, 3000),
    RED_WINE("레드와인", MenuType.DRINK, 60000),
    CHAMPAGNE("샴페인", MenuType.DRINK, 25000);

    private final String name;
    private final MenuType menuType;
    private final Integer price;

    Dish(String name, MenuType menuType, Integer price) {
        this.name = name;
        this.menuType = menuType;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public Integer getPrice() {
        return price;
    }

    public static Dish findDishByName(String dishName) {
        return Arrays.stream(values())
                .filter(dish -> dish.getName().equals(dishName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    public static void validateDishName(String inputDish) {
        if (!isAnyMatchWithDish(inputDish)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isAnyMatchWithDish(String inputDish) {
        return Arrays.stream(values())
                .map(Dish::getName)
                .anyMatch(name -> name.equals(inputDish));
    }
}
