package seedu.address.model.event.exceptions;

import seedu.address.commons.exceptions.DuplicateDataException;

/**
 * Signals that an operation would have violated the 'no duplicates' property of the list.
 */
public class DuplicateEventException extends DuplicateDataException {
    public DuplicateEventException() {
        super("Operation would result in duplicate events");
    }
}
