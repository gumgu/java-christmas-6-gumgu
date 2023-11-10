package christmas.domain;

public enum MenuType {

    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String title;

    MenuType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
