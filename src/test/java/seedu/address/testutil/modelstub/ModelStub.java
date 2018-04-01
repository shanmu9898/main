package seedu.address.testutil.modelstub;

import static org.junit.Assert.fail;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;
import seedu.address.model.tag.Tag;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void addPerson(Person person) throws DuplicatePersonException {
        fail("This method should not be called.");
    }

    @Override
    public void addStudent(Student student) throws DuplicatePersonException {
        fail("This method should not be called.");
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        fail("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public ObservableList<ShortcutDoubles> getFilteredCommandsList() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public void deletePerson(Person target) throws PersonNotFoundException {
        fail("This method should not be called.");
    }

    @Override
    public void deleteStudent(Student target) throws PersonNotFoundException {
        fail("This method should not be called.");
    }

    @Override
    public void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException {
        fail("This method should not be called.");
    }

    @Override
    public void updateStudent(Student target, Student editedStudent)
            throws DuplicatePersonException, PersonNotFoundException {
        fail("This method should not be called.");
    }

    @Override
    public void addCommandShortcut(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        fail("This method should not be called.");
    }

    @Override
    public void deleteCommandShortcut(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        fail("This method should not be called");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public String getCurrentActiveListType() {
        fail("This method should not be called.");
        return null;
    }

    @Override
    public void changeCurrentActiveListType(String type) {
        fail("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        fail("This method should not be called.");
    }

    @Override
    public void deleteTag(Tag tag) {
        fail("This method should not be called.");
    }

    @Override
    public void addAppointment(Appointment appointment) throws DuplicateEventException {
        fail("This method should not be called.");
    }

    @Override
    public void deleteAppointment(Appointment appointment) throws EventNotFoundException {
        fail("This method should not be called.");
    }

    @Override
    public void addTask(Task task) throws DuplicateEventException {
        fail("This method should not be called.");
    }

    @Override
    public void deleteTask(Task task) throws EventNotFoundException {
        fail("This method should not be called.");
    }
}
