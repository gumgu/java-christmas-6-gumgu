package christmas.domain.freegift;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FreeGiftManagerTest {

    @DisplayName("할인전 총 주문 금액이 기준점 이상인 경우, 객체를 포함한다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 140_123_000, 200_000})
    void findApplicableFreeGift_(Integer originalPrice) {
        // given
        FreeGiftManager freeGiftManager = new FreeGiftManager(originalPrice);

        // when
        List<FreeGift> freeGifts = freeGiftManager.findApplicableFreeGift();

        // then
        assertThat(freeGifts)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of(
                                new FreeGiftChampagne(originalPrice)
                        )
                );
    }

    @DisplayName("할인전 총 주문 금액이 기준점 미만인 경우, 빈 List를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {119_999, 0, 2000})
    void findApplicableFreeGift(Integer originalPrice) {
        // given
        FreeGiftManager freeGiftManager = new FreeGiftManager(originalPrice);

        // when
        List<FreeGift> freeGifts = freeGiftManager.findApplicableFreeGift();

        // then
        assertThat(freeGifts)
                .usingRecursiveComparison()
                .isEqualTo(
                        List.of()
                );
    }
}