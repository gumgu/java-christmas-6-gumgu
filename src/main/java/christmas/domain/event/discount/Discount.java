package christmas.domain.event.discount;

import christmas.domain.order.VisitDate;
import christmas.domain.event.Event;

public abstract class Discount implements Event {

    VisitDate visitDate;

    public Discount(VisitDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public boolean isApply() {
        return checkApplicability(visitDate);
    }

    protected abstract boolean checkApplicability(VisitDate visitDate);

    @Override
    public Integer calculateBenefitPrice() {
        return calculateDiscount();
    }

    protected abstract Integer calculateDiscount();

}
