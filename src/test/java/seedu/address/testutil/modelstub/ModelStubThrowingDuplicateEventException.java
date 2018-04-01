package seedu.address.testutil.modelstub;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;
import seedu.address.model.event.UniqueEventList;

//@@author Sisyphus25
/**
 * A Model stub that always throw a DuplicateEventException when trying to add an appointment/task.
 */
public class ModelStubThrowingDuplicateEventException extends ModelStub {
    @Override
    public void addAppointment (Appointment appointment) throws UniqueEventList.DuplicateEventException {
        throw new UniqueEventList.DuplicateEventException();
    }

    @Override
    public void addTask (Task task) throws UniqueEventList.DuplicateEventException {
        throw new UniqueEventList.DuplicateEventException();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
