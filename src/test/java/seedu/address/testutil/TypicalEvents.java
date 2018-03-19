package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public final Event typicalAppointment1;
    public final Event typicalTask1;

    public TypicalEvents() {
        typicalAppointment1 =
                new EventBuilder("Meet student", "09/10/2018 10:00", "09/10/2018 11:00", ALICE).build();
        typicalTask1 =
                new EventBuilder("To do", "10/10/2018 10:00").build();
    }
}
