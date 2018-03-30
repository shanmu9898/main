package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTagList tags;
    private final UniqueEventList<Appointment> appointments;
    private final UniqueEventList<Task> tasks;
    private final UniqueShortcutDoublesList shorcutCommands;
    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tags = new UniqueTagList();
        appointments = new UniqueEventList<Appointment>();
        tasks = new UniqueEventList<Task>();
        shorcutCommands = new UniqueShortcutDoublesList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Tags in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setPersons(List<Person> persons) throws DuplicatePersonException {
        this.persons.setPersons(persons);
    }

    public void setTags(Set<Tag> tags) {
        this.tags.setTags(tags);
    }

    public void setAppointments(List<Appointment> appointments)
            throws UniqueEventList.DuplicateEventException {
        this.appointments.setEvents(appointments);
    }

    public void setTasks(List<Task> tasks)
            throws UniqueEventList.DuplicateEventException {
        this.tasks.setEvents(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setTags(new HashSet<>(newData.getTagList()));
        List<Person> syncedPersonList = newData.getPersonList().stream()
                .map(this::syncWithMasterTagList)
                .collect(Collectors.toList());
        List<Appointment> appointmentList = newData.getAppointmentList();
        List<Task> taskList = newData.getTaskList();

        try {
            setPersons(syncedPersonList);
            setAppointments(appointmentList);
            setTasks(taskList);
        } catch (DuplicatePersonException e) {
            throw new AssertionError("AddressBooks should not have duplicate persons");
        } catch (UniqueEventList.DuplicateEventException e) {
            throw new AssertionError("AddressBooks should not have duplicate events");
        }
    }

    //// person-level operations

    /**
     * Adds a person to the address book.
     * Also checks the new person's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the person to point to those in {@link #tags}.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(Person p) throws DuplicatePersonException {
        Person person = syncWithMasterTagList(p);
        // TODO: the tags master list will be updated even though the below line fails.
        // This can cause the tags master list to have additional tags that are not tagged to any person
        // in the person list.
        persons.add(person);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code AddressBook}'s tag list will be updated with the tags of {@code editedPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     *
     * @see #syncWithMasterTagList(Person)
     */
    public void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedPerson);

        Person syncedEditedPerson = syncWithMasterTagList(editedPerson);
        // TODO: the tags master list will be updated even though the below line fails.
        // This can cause the tags master list to have additional tags that are not tagged to any person
        // in the person list.
        persons.setPerson(target, syncedEditedPerson);
        removeUnusedTags();
    }

    /**
     * Removes all {@code Tag}s that are not used by any {@code Person} in this {@code AddressBook}.
     */
    private void removeUnusedTags() {
        Set<Tag> tagsInPersons = persons.asObservableList().stream().map(Person::getTags).flatMap(Set::stream)
                                 .collect(Collectors.toSet());

        tags.setTags(tagsInPersons);
    }

    /**
     *  Updates the master tag list to include tags in {@code person} that are not in the list.
     *  @return a copy of this {@code person} such that every tag in this person points to a Tag object in the master
     *  list.
     */
    private Person syncWithMasterTagList(Person person) {
        final UniqueTagList personTags = new UniqueTagList(person.getTags());
        tags.mergeFrom(personTags);

        // Create map with values = tag object references in the master list
        // used for checking person tag references
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        tags.forEach(tag -> masterTagObjects.put(tag, tag));

        // Rebuild the list of person tags to point to the relevant tags in the master tag list.
        final Set<Tag> correctTagReferences = new HashSet<>();
        personTags.forEach(tag -> correctTagReferences.add(masterTagObjects.get(tag)));
        return new Person(
                person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), correctTagReferences);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws PersonNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removePerson(Person key) throws PersonNotFoundException {
        if (persons.remove(key)) {
            return true;
        } else {
            throw new PersonNotFoundException();
        }
    }

    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

    public void addShortcutDoubles(ShortcutDoubles s)
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        shorcutCommands.add(s);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asObservableList().size() + " persons, "
                + tags.asObservableList().size() +  " tags, "
                + appointments.asObservableList().size() + " appointments, "
                + tasks.asObservableList().size() +  " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asObservableList();
    }

    @Override
    public ObservableList<ShortcutDoubles> getCommandsList() {
        return shorcutCommands.asObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.persons.equals(((AddressBook) other).persons)
                && this.appointments.equals(((AddressBook) other).appointments)
                && this.tasks.equals(((AddressBook) other).tasks)
                && this.tags.equalsOrderInsensitive(((AddressBook) other).tags)
                && this.shorcutCommands.equals(((AddressBook) other).shorcutCommands));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(persons, appointments, tasks, tags);
    }

    /**
     * Removes the particular tag for all people in the AddressBook }.
     */
    public void removeTag(Tag tag) throws DuplicatePersonException, PersonNotFoundException {
        for (Person person : persons) {
            removeTagFromPerson(tag, person);
        }

    }

    /**
     * Removes the particular tag for that particular person in the AddressBook }.
     */
    private void removeTagFromPerson(Tag tag, Person person) throws PersonNotFoundException, DuplicatePersonException {
        Set<Tag> listOfTags = new HashSet<>(person.getTags());

        if (listOfTags.contains(tag)) {
            listOfTags.remove(tag);
        } else {
            return;
        }

        Person updatedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                                          person.getAddress(), listOfTags);

        updatePerson(person, updatedPerson);
    }

    //@@author Sisyphus25
    //event operations

    /**
     * Adds an appointment to the address book.
     *
     * @throws UniqueEventList.DuplicateEventException if an equivalent appointment already exists.
     */
    public void addAppointment(Appointment e) throws UniqueEventList.DuplicateEventException {
        appointments.add(e);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws UniqueEventList.EventNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removeAppointment(Appointment key) throws UniqueEventList.EventNotFoundException {
        if (appointments.remove(key)) {
            return true;
        } else {
            throw new UniqueEventList.EventNotFoundException();
        }
    }

    /**
     * Adds a task to the address book.
     *
     * @throws UniqueEventList.DuplicateEventException if an equivalent appointment already exists.
     */
    public void addTask(Task e) throws UniqueEventList.DuplicateEventException {
        tasks.add(e);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws UniqueEventList.EventNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removeTask(Task key) throws UniqueEventList.EventNotFoundException  {
        if (tasks.remove(key)) {
            return true;
        } else {
            throw new UniqueEventList.EventNotFoundException();
        }
    }
}
