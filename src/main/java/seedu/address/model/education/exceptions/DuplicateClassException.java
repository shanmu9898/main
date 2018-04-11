package seedu.address.model.education.exceptions;

import seedu.address.commons.exceptions.DuplicateDataException;

/**
 * Signals that the operation will result in duplicate Class objects.
 */
public class DuplicateClassException extends DuplicateDataException {
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}
