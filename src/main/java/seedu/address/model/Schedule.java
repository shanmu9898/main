package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

public class Schedule implements ReadOnlySchedule {
    private final UniqueEventList events;

    @Override
    public ObservableList<Event> getEventList() {
        return events.asObservableList();
    }
}
