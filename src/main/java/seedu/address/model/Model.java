package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.education.Class;
import seedu.address.model.education.exceptions.DuplicateClassException;
import seedu.address.model.education.exceptions.StudentClassNotFoundException;
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
    String LIST_TYPE_CONTACT = "contacts";
    String LIST_TYPE_APPOINTMENT = "appointments";
    String LIST_TYPE_TASK = "tasks";
    String LIST_TYPE_CLASS = "classes";
    String LIST_TYPE_SHORTCUT = "shortcuts";

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

    /** Adds the given shortcut */
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

    /** Returns an unmodifiable view of the filtered commands list */
    ObservableList<ShortcutDoubles> getFilteredCommandsList();

    /** Returns an unmodifiable view of the filtered students list */
    ObservableList<Student> getFilteredStudentsList();

    /** Returns an unmodifiable view of the filtered class list */
    ObservableList<Class> getFilteredClassList();

    /** Returns the item type of the curent active list being shown in the GUI */
    String getCurrentActiveListType();

    /** Deletes the given command shortcut */
    void deleteCommandShortcut(ShortcutDoubles commandShortcut)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException;

    /** Returns a sorted list of contacts*/
    void sortByNameFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    /** Delete the given tag */
    void deleteTag(Tag tag) throws PersonNotFoundException, DuplicatePersonException;

    /** Adds the given appointment */
    void addAppointment(Appointment appointment) throws DuplicateEventException;

    /** Deletes the given appointment. */
    void deleteAppointment(Appointment appointment) throws EventNotFoundException;

    /** Adds the given task */
    void addTask(Task task) throws DuplicateEventException;

    /** Deletes the given task */
    void deleteTask(Task task) throws EventNotFoundException;

    /** Adds the given class group */
    void addClass(Class group, List<Student> studentList) throws DuplicateClassException;

    /** Deletes the given class */
    void deleteClass(Class target) throws StudentClassNotFoundException;

    /** Change the current active list that is being displayed in the model */
    void changeCurrentActiveListType(String itemType);

    /** Raises an event to indicate the appointment list has changed */
    void indicateAppointmentListChanged();
}
