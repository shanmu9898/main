package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;

/**
 * Unmodifiable view of a schedule
 */
public interface ReadOnlySchedule {
    /**
     * Returns an unmodifiable view of the schedule.
     * This list will not contain any duplicate event.
     */
    ObservableList<Event> getEventList();
}
