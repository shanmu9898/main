package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.XmlAddressBookStorage;

//@@author shanmu9898
/**
 * Imports contacts from a different TeachConnect XML file
 */
public class ImportCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": imports contacts to the address book."
            + "Parameters: file location...\n"
            + "Example: " + COMMAND_WORD + " main/src/test/data/sandbox/somerandomfile.xml";
    public static final String MESSAGE_SUCCESS = "%1$s contacts have been successfully imported "
            + "and %2$s have been left out!";
    protected static final String MESSAGE_INVALID_FILE = "Please input a valid file location";
    protected Storage storage;
    private AddressBook addressBookImported;
    private AddressBookStorage addressBookStorage;
    private String filePath;
    private int numberAdded = 0;
    private int numberNotAdded = 0;

    /**
     * Creates an ImportCommand to import the specified TeachConnect XML file
     */
    public ImportCommand(String importPath) {
        requireNonNull(importPath);
        this.filePath = importPath;
        addressBookStorage = new XmlAddressBookStorage(filePath);
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        try {
            if (addressBookStorage.readAddressBook(filePath).isPresent()) {
                this.addressBookImported = new AddressBook(addressBookStorage.readAddressBook().get());
                ObservableList<Person> people = addressBookImported.getPersonList();
                for (int i = 0; i < people.size(); i++) {
                    try {
                        model.addPerson(people.get(i));
                        numberAdded++;
                    } catch (DuplicatePersonException e) {
                        numberNotAdded++;
                    }
                }
            } else {
                throw new CommandException(String.format(MESSAGE_INVALID_FILE));
            }
        } catch (DataConversionException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE));
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, numberAdded, numberNotAdded));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && filePath.equals(((ImportCommand) other).filePath));
    }


}




