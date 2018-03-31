package seedu.address.model.shortcuts;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.util.CollectionUtil;

/**
 *
 */
public class UniqueShortcutDoublesList {

    private final ObservableList<ShortcutDoubles> internalList = FXCollections.observableArrayList();

    public UniqueShortcutDoublesList(){

    }

    /**
     * Adds Shortcut Doubles to the internal list
     * @param toAdd
     * @throws DuplicateShortcutDoublesException
     */
    public void add(ShortcutDoubles toAdd) throws DuplicateShortcutDoublesException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateShortcutDoublesException();
        }
        internalList.add(toAdd);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Returns an ObservableList of the internallist
     * @return
     */
    public ObservableList<ShortcutDoubles> asObservableList() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Gives a duplicate Except
     */
    public static class DuplicateShortcutDoublesException extends DuplicateDataException {
        protected DuplicateShortcutDoublesException() {
            super("Operation would result in duplicate Doubles");
        }
    }

    /**
     * Helps in checking if there are duplicates
     * @param toCheck
     * @return
     */
    public boolean contains(ShortcutDoubles toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    @Override
    public boolean equals(Object other) {
        assert CollectionUtil.elementsAreUnique(internalList);
        return other == this // short circuit if same object
                || (other instanceof UniqueShortcutDoublesList // instanceof handles nulls
                && this.internalList.equals(((UniqueShortcutDoublesList) other).internalList));
    }

    public void setCommandsList(List<ShortcutDoubles> commandsList) {
        requireNonNull(commandsList);
        internalList.setAll(commandsList);
        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Removes the equvivalent command shortcut from the list.
     * @param shortcutDoubles
     *
     * @throws UniqueShortcutDoublesList.CommandShortcutNotFoundException
     */
    public boolean remove(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        requireNonNull(shortcutDoubles);
        final boolean shortcutToBeDeleted = internalList.remove(shortcutDoubles);
        if (!shortcutToBeDeleted) {
            throw new UniqueShortcutDoublesList.CommandShortcutNotFoundException();
        }
        return shortcutToBeDeleted;
    }

    /**
     * Exception when the command shortcut is not present in the list of stored commands
     */
    public static class CommandShortcutNotFoundException extends Exception {}
}
