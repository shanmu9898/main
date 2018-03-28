package seedu.address.model.event;

/**
 * Represents an Event in the schedule.
 */
public interface Event {
    Title getTitle();
    EventTime getTime();
}
