package seedu.address.testutil.modelstub;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Task;
import seedu.address.model.event.exceptions.DuplicateEventException;

//@@author Sisyphus25
/**
 * A Model stub that always accept the task being added.
 */
public class ModelStubAcceptingTaskAdded extends ModelStub {
    public final ArrayList<Task> tasksAdded = new ArrayList<>();

    @Override
    public void addTask(Task event) throws DuplicateEventException {
        requireNonNull(event);
        tasksAdded.add(event);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
