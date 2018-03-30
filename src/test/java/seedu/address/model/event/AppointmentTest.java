package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import seedu.address.testutil.Assert;

//@@author Sisyphus25
public class AppointmentTest {
    private static final Title VALID_TITLE = new Title("Meet Student");
    private static final EventTime VALID_START_TIME = new EventTime("05/04/2018 10:00");
    private static final EventTime VALID_END_TIME = new EventTime("05/04/2018 11:00");
    private static final EventTime INVALID_END_TIME = new EventTime("05/04/2018 09:00");

    @Test
    public void constructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Appointment(VALID_TITLE, VALID_START_TIME, INVALID_END_TIME));
    }

    @Test
    public void isValidTime() {
        // invalid time stamps
        assertFalse(Appointment.isValidTime(VALID_START_TIME, INVALID_END_TIME)); //End time is before Start Time

        // valid time stamps
        assertTrue(Appointment.isValidTime(VALID_START_TIME, VALID_END_TIME));
    }
}
