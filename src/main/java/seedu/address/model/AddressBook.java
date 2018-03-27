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
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueContactList;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueStudentList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueContactList contacts;
    private final UniquePersonList persons;
    private final UniqueStudentList students;
    private final UniqueTagList tags;
    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        students = new UniqueStudentList();
        contacts = new UniqueContactList(persons, students);
        tags = new UniqueTagList();
        events = new UniqueEventList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Tags in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list operations

    public void setPersons(List<Person> persons) throws DuplicatePersonException {
        this.persons.setPersons(persons);
    }

    public void setStudents(List<Student> students) throws DuplicatePersonException {
        this.students.setStudents(students);
    }

    public void setTags(Set<Tag> tags) {
        this.tags.setTags(tags);
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
        List<Student> syncedStudentList = newData.getStudentList().stream()
                .map(this::syncWithMasterTagList).map(person -> (Student) person)
                .collect(Collectors.toList());

        try {
            setPersons(syncedPersonList);
            setStudents(syncedStudentList);
        } catch (DuplicatePersonException e) {
            throw new AssertionError("AddressBooks should not have duplicate persons");
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
        try {
            persons.add(person);
        } catch (DuplicatePersonException e) {
            removeUnusedTags();
            throw e;
        }
    }

    /**
     * Adds a student to the address book.
     * Also checks the new student's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the student to point to those in {@link #tags}.
     *
     * @throws DuplicatePersonException if an equivalent student already exists.
     */
    public void addStudent(Student s) throws DuplicatePersonException {
        Student student = (Student) syncWithMasterTagList(s);
        try {
            students.add(student);
        } catch (DuplicatePersonException e) {
            removeUnusedTags();
            throw e;
        }
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
        try {
            persons.setPerson(target, syncedEditedPerson);
        } finally {
            removeUnusedTags();
        }
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code AddressBook}'s tag list will be updated with the tags of {@code editedStudent}.
     *
     * @throws DuplicatePersonException if updating the student's details causes the student to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     *
     * @see #syncWithMasterTagList(Person)
     */
    public void updateStudent(Student target, Student editedStudent)
            throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedStudent);

        Student syncedEditedStudent = (Student) syncWithMasterTagList(editedStudent);
        try {
            students.setStudent(target, syncedEditedStudent);
        } finally {
            removeUnusedTags();
        }
    }

    /**
     * Removes all {@code Tag}s that are not used by any {@code Person} or {@code Student} in this {@code AddressBook}.
     */
    private void removeUnusedTags() {
        Set<Tag> tagsInPersons = persons.asObservableList().stream().map(Person::getTags).flatMap(Set::stream)
                                 .collect(Collectors.toSet());
        Set<Tag> tagsInStudents = students.asObservableList().stream().map(Student::getTags).flatMap(Set::stream)
                                 .collect(Collectors.toSet());

        tags.setTags(tagsInPersons);
        tags.setTags(tagsInStudents);
    }

    /**
     *  Updates the master tag list to include tags in {@code person} or {@code student} that are not in the list.
     *  @return a copy of this {@code person} or {@code student} such that every tag in this person points to a Tag
     *  object in the master list.
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

        if (person instanceof Student) {
            return new Student(
                    person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), correctTagReferences);
        } else {
            return new Person(
                    person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), correctTagReferences);
        }
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

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws PersonNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removeStudent(Student key) throws PersonNotFoundException {
        if (students.remove(key)) {
            return true;
        } else {
            throw new PersonNotFoundException();
        }
    }

    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asObservableList().size() + " persons, " + students.asObservableList().size() + " students, "
                + tags.asObservableList().size() +  " tags" + events.asObservableList().size() +  " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asObservableList();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asObservableList();
    }

    @Override
    public ObservableList<Person> getContactList() {
        return contacts.asObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.persons.equals(((AddressBook) other).persons)
                && this.students.equals(((AddressBook) other).students)
                && this.events.equals(((AddressBook) other).events)
                && this.tags.equalsOrderInsensitive(((AddressBook) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(persons, tags);
    }

    /**
     * Removes the particular tag for all people in the AddressBook.
     */
    public void removeTag(Tag tag) throws DuplicatePersonException, PersonNotFoundException {
        for (Person person : persons) {
            removeTagFromPerson(tag, person);
        }
        for (Student student : students) {
            removeTagFromStudent(tag, student);
        }

    }

    /**
     * Removes the particular tag for that particular person in the AddressBook.
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

    /**
     * Removes the particular tag for that particular student in the AddressBook.
     */
    private void removeTagFromStudent(Tag tag, Student student)
            throws PersonNotFoundException, DuplicatePersonException {
        Set<Tag> listOfTags = new HashSet<>(student.getTags());

        if (listOfTags.contains(tag)) {
            listOfTags.remove(tag);
        } else {
            return;
        }

        Person updatedStudent = new Student(student.getName(), student.getPhone(), student.getEmail(),
                                          student.getAddress(), listOfTags);

        updatePerson(student, updatedStudent);
    }
}
