package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
//@@author Sisyphus25
public class TypicalEvents {
    public static final Event TYPICAL_APPOINTMENT_1 =
            new EventBuilder("Meet student", "09/10/2018 10:00", "09/10/2018 11:00", ALICE).build();;
    public static final Event TYPICAL_TASK_1 =
            new EventBuilder("To do", "10/10/2018 10:00").build();
}
