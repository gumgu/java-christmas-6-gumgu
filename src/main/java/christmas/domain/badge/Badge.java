package christmas.domain.badge;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    NONE("없음", 0);

    private final String name;
    private final Integer minimumAmount;

    Badge(String name,
          Integer minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public static Badge getBadgeByBenefitPrice(Integer benefitPrice) {
        Badge[] badges = getAscendingPriceBadges();

        for (Badge badge : badges) {
            if (benefitPrice >= badge.getMinimumAmount()) {
                return badge;
            }
        }
        return Badge.NONE;
    }

    private static Badge[] getAscendingPriceBadges() {
        Badge[] badges = Badge.values();
        Arrays.sort(badges, Comparator.comparing(Badge::getMinimumAmount).reversed());
        return badges;
    }

    public Integer getMinimumAmount() {
        return minimumAmount;
    }
}
