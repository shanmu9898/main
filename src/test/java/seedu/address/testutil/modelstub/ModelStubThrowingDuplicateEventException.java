package seedu.address.testutil.modelstub;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

//@@author Sisyphus25
/**
 * A Model stub that always throw a DuplicateEventException when trying to add an appointment.
 */
public class ModelStubThrowingDuplicateEventException extends ModelStub {
    @Override
    public void addEvent(Event event) throws UniqueEventList.DuplicateEventException {
        throw new UniqueEventList.DuplicateEventException();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
