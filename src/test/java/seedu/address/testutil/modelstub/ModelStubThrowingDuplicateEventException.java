package seedu.address.testutil.modelstub;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;
import seedu.address.model.event.exceptions.DuplicateEventException;

//@@author Sisyphus25
/**
 * A Model stub that always throw a DuplicateEventException when trying to add an appointment/task.
 */
public class ModelStubThrowingDuplicateEventException extends ModelStub {
    @Override
    public void addAppointment (Appointment appointment) throws DuplicateEventException {
        throw new DuplicateEventException();
    }

    @Override
    public void addTask (Task task) throws DuplicateEventException {
        throw new DuplicateEventException();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
