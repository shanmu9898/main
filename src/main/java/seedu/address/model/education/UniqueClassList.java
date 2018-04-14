package seedu.address.model.education;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.education.exceptions.DuplicateClassException;
import seedu.address.model.education.exceptions.StudentClassNotFoundException;

//@@author randypx-reused
/**
 * A list of class that enforces uniqueness between its elements and does not allow nulls.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Class#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueClassList implements Iterable<Class> {

    private final ObservableList<Class> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent class as the given argument.
     */
    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a class to the list.
     *
     * @throws DuplicateClassException if the class to add is a duplicate of an existing class in the list.
     */
    public void add(Class toAdd) throws DuplicateClassException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the class {@code target} in the list with {@code editedClass}.
     *
     * @throws DuplicateClassException if the replacement is equivalent to another existing class in the list.
     * @throws StudentClassNotFoundException  if {@code target} could not be found in the list.
     */
    public void setClass(Class target, Class editedClass)
            throws DuplicateClassException, StudentClassNotFoundException {
        requireNonNull(editedClass);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentClassNotFoundException();
        }

        if (!target.equals(editedClass) && internalList.contains(editedClass)) {
            throw new DuplicateClassException();
        }

        internalList.set(index, editedClass);
    }

    /**
     * Removes the equivalent class from the list.
     *
     * @throws StudentClassNotFoundException if no such class could be found in the list.
     */
    public boolean remove(Class toRemove) throws StudentClassNotFoundException {
        requireNonNull(toRemove);
        final boolean classFoundAndDeleted = internalList.remove(toRemove);
        if (!classFoundAndDeleted) {
            throw new StudentClassNotFoundException();
        }
        return classFoundAndDeleted;
    }

    public void setClasses(UniqueClassList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setClasses(List<Class> classes) throws DuplicateClassException {
        requireAllNonNull(classes);
        final UniqueClassList replacement = new UniqueClassList();
        for (final Class group : classes) {
            replacement.add(group);
        }
        setClasses(replacement);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Class> asObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Class> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueClassList // instanceof handles nulls
                && this.internalList.equals(((UniqueClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
