package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.model.AppointmentListChangedEvent;
import seedu.address.commons.events.model.ClassListChangedEvent;
import seedu.address.commons.events.model.StudentListChangedEvent;
import seedu.address.commons.events.ui.ToggleListEvent;
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
 * Represents the in-memory model of the address book data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final FilteredList<Person> filteredContacts;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<ShortcutDoubles> filteredShortcutCommands;
    private final SortedList<Person> sortedFilteredConatacts;
    private final FilteredList<Class> filteredClass;
    private final FilteredList<Student> filteredStudents;
    private String currentActiveListType;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        filteredContacts = new FilteredList<>(this.addressBook.getContactList());
        filteredAppointments = new FilteredList<>(this.addressBook.getAppointmentList());
        filteredShortcutCommands = new FilteredList<>(this.addressBook.getCommandsList());
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
        sortedFilteredConatacts = new SortedList<Person>(filteredContacts);
        filteredClass = new FilteredList<>(this.addressBook.getClassList());
        currentActiveListType = LIST_TYPE_CONTACT;
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        addressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(addressBook));
    }

    /** Raises an event to indicate the appointment list has changed */
    public void indicateAppointmentListChanged() {
        raise(new AppointmentListChangedEvent(addressBook.getAppointmentList()));
    }

    //@@author randypx
    /** Raises an event to indicate the change of list view */
    private void evokeToggleListEvent(String type) {
        changeCurrentActiveListType(type);
        raise(new ToggleListEvent(type));
    }
    /** Raises an event to indicate the student list has changed due to the addition/deletion of a class*/
    private void indicateStudentListChanged() {
        raise(new StudentListChangedEvent());
    }

    /** Raises an event to indicate the class list has changed due to the deletion/edit of a student*/
    private void indicateClassListChanged() {
        raise(new ClassListChangedEvent());
    }

    //@@author
    @Override
    public synchronized void deletePerson(Person target) throws PersonNotFoundException {
        addressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void deleteStudent(Student target) throws PersonNotFoundException {
        addressBook.removeStudent(target);
        indicateAddressBookChanged();
        indicateClassListChanged();
    }

    @Override
    public synchronized void addPerson(Person person) throws DuplicatePersonException {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        evokeToggleListEvent(LIST_TYPE_CONTACT);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addStudent(Student student) throws DuplicatePersonException {
        addressBook.addStudent(student);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        evokeToggleListEvent(LIST_TYPE_CONTACT);
        indicateAddressBookChanged();
    }

    //@@author shanmu9898
    @Override
    public synchronized void addCommandShortcut(ShortcutDoubles shortcutDoubles)
               throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        addressBook.addShortcutDoubles(shortcutDoubles);
        evokeToggleListEvent(LIST_TYPE_SHORTCUT);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void deleteCommandShortcut(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        addressBook.removeShortcutDouble(shortcutDoubles);
    }
    //@@author
    @Override
    public void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireAllNonNull(target, editedPerson);

        addressBook.updatePerson(target, editedPerson);
        indicateAddressBookChanged();
    }

    @Override
    public void updateStudent(Student target, Student editedStudent)
            throws DuplicatePersonException, PersonNotFoundException {
        requireAllNonNull(target, editedStudent);

        addressBook.updateStudent(target, editedStudent);
        indicateAddressBookChanged();
        indicateClassListChanged();
    }

    //@@author Sisyphus25
    @Override
    public void addAppointment(Appointment appointment) throws DuplicateEventException {
        addressBook.addAppointment(appointment);
        evokeToggleListEvent(LIST_TYPE_APPOINTMENT);
        indicateAddressBookChanged();
        indicateAppointmentListChanged();
    }

    @Override
    public void deleteAppointment(Appointment target) throws EventNotFoundException {
        addressBook.removeAppointment(target);
        indicateAddressBookChanged();
        indicateAppointmentListChanged();
    }

    @Override
    public void addTask(Task task) throws DuplicateEventException {
        addressBook.addTask(task);
        evokeToggleListEvent(LIST_TYPE_TASK);
        indicateAddressBookChanged();
    }

    @Override
    public void deleteTask(Task target) throws EventNotFoundException {
        addressBook.removeTask(target);
        indicateAddressBookChanged();
    }
    //@@author

    @Override
    public void addClass(Class group, List<Student> studentList) throws DuplicateClassException {
        addressBook.addClass(group);
        for (Student student : studentList) {
            student.enterClass(group);
        }
        evokeToggleListEvent(LIST_TYPE_CLASS);
        indicateAddressBookChanged();
        indicateStudentListChanged();
    }

    @Override
    public void deleteClass(Class target) throws StudentClassNotFoundException {
        addressBook.removeClass(target);
        indicateAddressBookChanged();
        indicateStudentListChanged();
    }

    @Override
    public void deleteTag(Tag tag) throws PersonNotFoundException, DuplicatePersonException {
        addressBook.removeTag(tag);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredContacts);
    }
    //@@author LimShiMinJonathan
    @Override
    public void sortByNameFilteredPersonList() {
        addressBook.sortContacts();
        indicateAddressBookChanged();
    }
    //@@author

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return FXCollections.unmodifiableObservableList(filteredAppointments);
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return FXCollections.unmodifiableObservableList(filteredTasks);
    }

    //@@author shanmu9898
    @Override
    public ObservableList<ShortcutDoubles> getFilteredCommandsList() {
        return FXCollections.unmodifiableObservableList(filteredShortcutCommands);
    }

    @Override
    public ObservableList<Student> getFilteredStudentsList() {
        return FXCollections.unmodifiableObservableList(filteredStudents);
    }

    //@@author randypx-reused
    @Override
    public ObservableList<Class> getFilteredClassList() {
        return FXCollections.unmodifiableObservableList(filteredClass);
    }

    //@@author
    @Override
    public String getCurrentActiveListType() {
        return currentActiveListType;
    }

    @Override
    public void changeCurrentActiveListType(String itemType) {
        currentActiveListType = itemType;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && filteredContacts.equals(other.filteredContacts);
    }

}
