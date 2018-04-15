package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;

//@@author randypx
/**
 * Indicates the class list has changed due to the deletion/edit of a student.
 */
public class ClassListChangedEvent extends BaseEvent {

    public ClassListChangedEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
