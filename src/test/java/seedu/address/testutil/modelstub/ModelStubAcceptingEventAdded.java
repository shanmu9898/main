package seedu.address.testutil.modelstub;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

//@@author Sisyphus25
/**
 * A Model stub that always accept the event being added.
 */
public class ModelStubAcceptingEventAdded extends ModelStub {
    public final ArrayList<Event> eventsAdded = new ArrayList<>();

    @Override
    public void addEvent(Event event) throws UniqueEventList.DuplicateEventException {
        requireNonNull(event);
        eventsAdded.add(event);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
