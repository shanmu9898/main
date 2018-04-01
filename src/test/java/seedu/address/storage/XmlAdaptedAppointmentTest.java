package seedu.address.storage;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.storage.XmlAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_2;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Title;
import seedu.address.testutil.Assert;

//@@author Sisyphus25
public class XmlAdaptedAppointmentTest {

    private static final String INVALID_TITLE = "  ";
    private static final String VALID_PERSON_TO_MEET = "Alice Email: alice@gmail.com";
    private static final String INVALID_TIME = "not a time stamp";

    @Test
    public void toModelType_validAppointmentDetails_returnsPerson() throws Exception {
        XmlAdaptedAppointment appointment = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_1);
        assertEquals(TYPICAL_APPOINTMENT_1, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(INVALID_TITLE, VALID_START_TIME, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = Title.MESSAGE_TITLE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, INVALID_TIME, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = EventTime.MESSAGE_TIME_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStartEndTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, VALID_START_TIME, INVALID_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = EventTime.MESSAGE_TIME_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(null, VALID_START_TIME, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, null, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Start Time");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, VALID_START_TIME, null, VALID_PERSON_TO_MEET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "End Time");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidTimePeriod_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, "20/10/2018 10:00", "20/10/2018 09:00");
        String expectedMessage = Appointment.MESSAGE_TIME_PERIOD_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void equals() {
        XmlAdaptedAppointment appointment = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_1);

        //same object
        assertTrue(appointment.equals(appointment));

        //same value
        XmlAdaptedAppointment appointmentCopy = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_1);
        assertTrue(appointment.equals(appointmentCopy));

        //different type
        assertFalse(appointment.equals(TYPICAL_APPOINTMENT_1));

        //different obj
        XmlAdaptedAppointment anotherAppointment = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_2);
        assertFalse(appointment.equals(anotherAppointment));
    }
}
