package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Observable;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;

/**
 * Imports contacts from a different Addressbook file
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": imports contacts to the address book."
            + "Parameters: file location...\n"
            + "Example: " + COMMAND_WORD + " main/src/test/data/sandbox/somerandomfile.xml";

    public static final String MESSAGE_SUCCESS = "Contacts have been successfully imported!";

    private AddressBook addressBook1;
    private ObservableList<Person> importedPeople;

    private String filePath;

    public ImportCommand(String importPath) {
        requireNonNull(importPath);
        filePath = importPath;
    }

    @Override
    public CommandResult execute() {
        requireNonNull(model);

        UserPrefs usersPrefs = new UserPrefs();
        usersPrefs.setAddressBookFilePath(filePath);

        ModelManager import_model = new ModelManager(addressBook1, usersPrefs);

        ReadOnlyAddressBook addressBook1 = import_model.getAddressBook();

        importedPeople = addressBook1.getPersonList();

        for(int i = 0; i < importedPeople.size(); i++){
            Person peopleAdded = importedPeople.get(i);
            try {
                model.addPerson(peopleAdded);
            } catch (DuplicatePersonException e) {
                e.printStackTrace();
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }


}




