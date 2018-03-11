package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

/**
 * Schedule contains all events
 */
public class Schedule implements ReadOnlySchedule {
    private final UniqueEventList events;

    {
        events = new UniqueEventList();
    }

    public Schedule() {}

    @Override
    public ObservableList<Event> getEventList() {
        return events.asObservableList();
    }
}
