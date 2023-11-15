package christmas.domain.order;

public class OrderDTO {

    VisitDate visitDate;
    Item item;

    public OrderDTO(
            VisitDate visitDate,
            Item item
    ) {
        this.visitDate = visitDate;
        this.item = item;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public Item getItem() {
        return item;
    }

}
