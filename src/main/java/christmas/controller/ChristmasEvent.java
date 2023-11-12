package christmas.controller;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.ui.InputView;
import christmas.ui.OutputView;

import java.util.List;

public class ChristmasEvent {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void doEventPlanner() {

        outputView.printGreeting();
        VisitDate visitDate = getVisitDate();

        List<String[]> orders = inputView.readOrder();

    }

    private VisitDate getVisitDate() {
        while (true) {
            try {
                return new VisitDate(inputView.readDate());
            } catch(IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }


}
