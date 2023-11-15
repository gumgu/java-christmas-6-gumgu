package christmas.domain.event.freegift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FreeGiftManager {

    private static final Integer ZERO = 0;

    private final List<FreeGift> allFreeGifts = new ArrayList<>();
    private final List<FreeGift> applicableFreeGifts = new ArrayList<>();

    private Integer originalPrice;

    public FreeGiftManager(Integer originalPrice) {
        this.originalPrice = originalPrice;
        initFreeGiftManager();
    }

    private void initFreeGiftManager() {
        allFreeGifts.add(new FreeGiftChampagne(originalPrice));
    }

    public List<FreeGift> findApplicableFreeGift() {
        for (FreeGift freeGift : allFreeGifts) {
            if (freeGift.isApply()) {
                applicableFreeGifts.add(freeGift);
            }
        }

        return Collections.unmodifiableList(applicableFreeGifts);
    }

    public Integer getTotalFreeGiftPrice() {
        Integer totalFreeGiftPrice = ZERO;

        for (FreeGift freeGift : applicableFreeGifts) {
            totalFreeGiftPrice += freeGift.calculateBenefitPrice();
        }

        return totalFreeGiftPrice;
    }

}
