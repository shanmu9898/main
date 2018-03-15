package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.Assert;
import seedu.address.testutil.TypicalPersons;

//@@author Sisyphus25
public class AppointmentTest {
    private static final String VALID_TITLE = "Meet Student";
    private static final Date CURRENT_DATE = new Date();
    private static final Person VALID_PERSON_TO_MEET = TypicalPersons.ALICE;


    private Calendar validStartTime;
    private Calendar validEndTime;
    private Calendar invalidStartTime;
    private Calendar invalidEndTime;

    public AppointmentTest() {
        validStartTime = Calendar.getInstance();
        validStartTime.setTime(CURRENT_DATE);
        validStartTime.add(Calendar.HOUR_OF_DAY, 2);

        validEndTime = Calendar.getInstance();
        validEndTime.setTime(CURRENT_DATE);
        validEndTime.add(Calendar.HOUR_OF_DAY, 3);

        invalidStartTime = Calendar.getInstance();
        invalidStartTime.setTime(CURRENT_DATE);
        invalidStartTime.add(Calendar.HOUR_OF_DAY, -1);

        invalidEndTime = Calendar.getInstance();
        invalidEndTime.setTime(CURRENT_DATE);
        invalidEndTime.add(Calendar.HOUR_OF_DAY, 1);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () ->
                new Appointment(null, null, null));
        Assert.assertThrows(NullPointerException.class, () ->
                new Appointment(VALID_TITLE, validStartTime, null));
        Assert.assertThrows(NullPointerException.class, () ->
                new Appointment(VALID_TITLE, null, validEndTime));
        Assert.assertThrows(NullPointerException.class, () ->
                new Appointment(null, validStartTime, validEndTime));
    }

    @Test
    public void constructor_invalidAppointmentTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Appointment(invalidTitle, validStartTime, validEndTime));
    }

    @Test
    public void constructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Appointment(VALID_TITLE, invalidStartTime, validEndTime));
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Appointment(VALID_TITLE, validStartTime, invalidEndTime));
    }

    @Test
    public void isValidTitle() {
        // null title
        Assert.assertThrows(NullPointerException.class, () -> Appointment.isValidTitle(null));

        // invalid Appointment
        assertFalse(Appointment.isValidTitle("")); // empty string
        assertFalse(Appointment.isValidTitle(" ")); // spaces only

        // valid Appointment
        assertTrue(Appointment.isValidTitle("Meet Dave"));
        assertTrue(Appointment.isValidTitle("-")); // one character
    }
}
