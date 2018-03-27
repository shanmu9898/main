package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a student to the list.
     *
     * @throws DuplicatePersonException if the person to add is a duplicate of an existing student in the list.
     */
    public void add(Student toAdd) throws DuplicatePersonException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     *
     * @throws DuplicatePersonException if the replacement is equivalent to another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    public void setStudent(Student target, Student editedStudent)
            throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedStudent) && internalList.contains(editedStudent)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     *
     * @throws PersonNotFoundException if no such student could be found in the list.
     */
    public boolean remove(Student toRemove) throws PersonNotFoundException {
        requireNonNull(toRemove);
        final boolean studentFoundAndDeleted = internalList.remove(toRemove);
        if (!studentFoundAndDeleted) {
            throw new PersonNotFoundException();
        }
        return studentFoundAndDeleted;
    }

    public void setStudents(UniqueStudentList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setStudents(List<Student> students) throws DuplicatePersonException {
        requireAllNonNull(students);
        final UniqueStudentList replacement = new UniqueStudentList();
        for (final Student student : students) {
            replacement.add(student);
        }
        setStudents(replacement);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    //@@author randypx
    /**
     * Add a listener to the list for any changes.
     * Update {@code contacts} for any changes made.
     */
    public void addListener(UniqueContactList contacts) {
        internalList.addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(Change<? extends Student> c) {
                contacts.updateList(c);
            }
        });
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                && this.internalList.equals(((UniqueStudentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
