package seedu.address.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.event.Appointment;
import seedu.address.model.event.UniqueEventList;

public class UniqueEventListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueEventList<Appointment> uniqueAppointmentList = new UniqueEventList<Appointment>();
        thrown.expect(UnsupportedOperationException.class);
        uniqueAppointmentList.asObservableList().remove(0);
    }
}
