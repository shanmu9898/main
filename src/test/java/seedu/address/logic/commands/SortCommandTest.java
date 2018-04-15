package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.TypicalPersons;

//@@author LimShiMinJonathan
public class SortCommandTest {
    private Model model;
    private SortCommand sortCommand;

    @Before
    public void setUp() throws DuplicatePersonException {

        AddressBook unsortedAddressBook = new AddressBook();
        unsortedAddressBook.setPersons(TypicalPersons.getUnsortedPersons());

        model = new ModelManager(unsortedAddressBook, new UserPrefs());

        sortCommand = new SortCommand();
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack());

    }

    @Test
    public void executeSort() throws DuplicatePersonException {
        AddressBook sortedAddressBook = new AddressBook();
        sortedAddressBook.setPersons(TypicalPersons.getTypicalPersons());

        Model expectedModel;
        expectedModel = new ModelManager(sortedAddressBook, new UserPrefs());

        CommandResult result = sortCommand.execute();
        assertEquals(sortCommand.MESSAGE_SUCCESS, result.feedbackToUser);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

}
//@@author
