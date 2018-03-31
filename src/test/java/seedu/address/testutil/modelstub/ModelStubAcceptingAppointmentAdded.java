package seedu.address.testutil.modelstub;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.UniqueEventList;

//@@author Sisyphus25
/**
 * A Model stub that always accept the appointment being added.
 */
public class ModelStubAcceptingAppointmentAdded extends ModelStub {
    public final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

    @Override
    public void addAppointment(Appointment event) throws UniqueEventList.DuplicateEventException {
        requireNonNull(event);
        appointmentsAdded.add(event);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
