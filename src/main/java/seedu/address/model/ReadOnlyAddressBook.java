package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of a list of all contacts.
     * This list will not contain any duplicate persons or students.
     */
    ObservableList<Person> getContactList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

    /**
     * Returns an unmodifiable view of the appointments list.
     * This list will not contain any duplicate appointment.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();


    ObservableList<ShortcutDoubles> getCommandsList();


}
