package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
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
 * The API of the Model component.
 */
public interface Model {
    String LIST_TYPE_CONTACT = "contact";
    String LIST_TYPE_APPOINTMENT = "appointment";
    String LIST_TYPE_TASK = "task";

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Person> PREDICATE_SHOW_ONLY_STUDENTS = person -> person instanceof Student;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Deletes the given person. */
    void deletePerson(Person target) throws PersonNotFoundException;

    /** Deletes the given student. */
    void deleteStudent(Student target) throws PersonNotFoundException;

    /** Adds the given person */
    void addPerson(Person person) throws DuplicatePersonException;


    /** Adds the given student */
    void addStudent(Student student) throws DuplicatePersonException;

    void addCommandShortcut(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException;

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException;

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     *
     * @throws DuplicatePersonException if updating the student's details causes the student to be equivalent to
     *      another existing student in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    void updateStudent(Student target, Student editedStudent)
            throws DuplicatePersonException, PersonNotFoundException;

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Task> getFilteredTaskList();

    /** Returns the item type of the curent active list being shown in the GUI */
    String getCurrentActiveListType();

    /** Deletes the given command shortcut */
    void deleteCommandShortcut(ShortcutDoubles commandShortcut)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException;

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    ObservableList<ShortcutDoubles> getFilteredCommandsList();

    void deleteTag(Tag tag) throws PersonNotFoundException, DuplicatePersonException;

    /** Adds the given appointment */
    void addAppointment(Appointment appointment) throws DuplicateEventException;

    /** Deletes the given appointment. */
    void deleteAppointment(Appointment appointment) throws EventNotFoundException;

    /** Adds the given task */
    void addTask(Task task) throws DuplicateEventException;

    /** Deletes the given task */
    void deleteTask(Task task) throws EventNotFoundException;

    /** Change the current active list that is being displayed in the model */
    void changeCurrentActiveListType(String itemType);
}
