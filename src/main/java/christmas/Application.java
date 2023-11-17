package christmas;

import christmas.controller.ChristmasEvent;
import christmas.domain.event.EventResultDTO;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        final List<EventResultDTO> ChristmasEventParticipants = new ArrayList<>();

        EventResultDTO eventResult = participateInEvent();
        ChristmasEventParticipants.add(eventResult);
    }

    private static EventResultDTO participateInEvent() {
        ChristmasEvent christmasEvent = new ChristmasEvent();
        return christmasEvent.doEventPlanner();
    }

}
