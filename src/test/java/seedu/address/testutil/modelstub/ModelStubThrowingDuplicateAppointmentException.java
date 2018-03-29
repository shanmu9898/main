package seedu.address.testutil.modelstub;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.UniqueAppointmentList;

//@@author Sisyphus25
/**
 * A Model stub that always throw a DuplicateAppointmentException when trying to add an appointment.
 */
public class ModelStubThrowingDuplicateAppointmentException extends ModelStub {
    @Override
    public void addAppointment (Appointment appointment) throws UniqueAppointmentList.DuplicateAppointmentException {
        throw new UniqueAppointmentList.DuplicateAppointmentException();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
