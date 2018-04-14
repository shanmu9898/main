package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;

//@@author randypx
/**
 * Indicates the student list has changed due to the addition/ deletion of a class.
 */
public class StudentListChangedEvent extends BaseEvent {

    public StudentListChangedEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
