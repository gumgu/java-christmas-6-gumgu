package christmas.domain.discount;

import christmas.domain.VisitDate;

public interface Discount {

    boolean isApply(VisitDate visitDate);

}
