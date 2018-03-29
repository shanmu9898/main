package seedu.address.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_2;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_3;

import org.junit.Test;

import seedu.address.model.event.Appointment;

//@@author Sisyphus25
public class AppointmentCardTest extends GuiUnitTest {

    @Test
    public void equals() {
        Appointment appointment = TYPICAL_APPOINTMENT_2;
        AppointmentCard appointmentCard = new AppointmentCard(appointment, 0);

        // same appointment, same index -> returns true
        AppointmentCard copy = new AppointmentCard(appointment, 0);
        assertTrue(appointmentCard.equals(copy));

        // same object -> returns true
        assertTrue(appointmentCard.equals(appointmentCard));

        // null -> returns false
        assertFalse(appointmentCard.equals(null));

        // different types -> returns false
        assertFalse(appointmentCard.equals(0));

        // different appointment, same index -> returns false
        Appointment differentAppointment = TYPICAL_APPOINTMENT_3;
        assertFalse(appointmentCard.equals(new AppointmentCard(differentAppointment, 0)));

        // same appointment, different index -> returns false
        assertFalse(appointmentCard.equals(new AppointmentCard(appointment, 1)));
    }

}
