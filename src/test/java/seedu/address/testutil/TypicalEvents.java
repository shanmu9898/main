package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
//@@author Sisyphus25
public class TypicalEvents {
    public static final Event TYPICAL_APPOINTMENT_1 =
            new EventBuilder("Meet student", "09/10/2018 10:00", "09/10/2018 11:00", ALICE).build();
    public static final Event TYPICAL_APPOINTMENT_2 =
            new EventBuilder("Consultation session", "04/07/2018 10:00", "04/07/2018 11:00", BOB).build();
    public static final Event TYPICAL_TASK_1 =
            new EventBuilder("To do", "10/10/2018 10:00").build();
    public static final Event TYPICAL_TASK_2 =
            new EventBuilder("Mark papers", "10/06/2018 20:00").build();
}
