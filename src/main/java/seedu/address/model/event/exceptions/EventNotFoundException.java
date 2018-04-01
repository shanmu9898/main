package seedu.address.model.event.exceptions;

/**
 * Signals that an operation is looking for an appointment doesn't exist.
 */
public class EventNotFoundException extends Exception {
    public EventNotFoundException() {
        super("Event not found");
    }
}
