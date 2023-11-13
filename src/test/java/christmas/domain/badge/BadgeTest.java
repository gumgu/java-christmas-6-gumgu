package christmas.domain.badge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BadgeTest {

    @ParameterizedTest
    @DisplayName("획득한 혜택 금액에 맞는 뱃지를 반환한다.")
    @MethodSource("generateData")
    void getBadgeByBenefitPrice(Integer benefitPrice, Badge expectBadge) {
        // when
        Badge badgeByBenefitPrice = Badge.getBadgeByBenefitPrice(benefitPrice);

        // then
        assertThat(badgeByBenefitPrice).isEqualTo(expectBadge);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(0, Badge.NONE),
                Arguments.of(3_000, Badge.NONE),
                Arguments.of(5_000, Badge.STAR),
                Arguments.of(8_000, Badge.STAR),
                Arguments.of(10_000, Badge.TREE),
                Arguments.of(15_000, Badge.TREE),
                Arguments.of(20_000, Badge.SANTA),
                Arguments.of(200_000, Badge.SANTA)
        );
    }

}