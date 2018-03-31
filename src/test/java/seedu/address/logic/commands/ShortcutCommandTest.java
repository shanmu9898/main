package seedu.address.logic.commands;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

public class ShortcutCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;
    private Model expectedModel;
    private final String validTestingCommandWord = "list";
    private final String validTestingShortcutWord = "l";
    private final String invalidTestingCommandWord = "king";

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }


    @Test
    public void constructor_nullCommandWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutCommand(null, validTestingShortcutWord);
    }

    @Test
    public void constructor_nullShortcutWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutCommand(validTestingCommandWord, null);
    }

    @Test
    public void executeUndoCommand_invalidCommandWord_throwsCommandException() throws CommandException {
        thrown.expect(CommandException.class);
        thrown.expectMessage(ShortcutCommand.MESSAGE_NO_COMMAND_TO_MAP);

        CommandResult commandResult = getAddShortcutForCommand(validTestingShortcutWord,
                                                               invalidTestingCommandWord,
                                                               model).executeUndoableCommand();
    }

    @Test
    public void executeUndoCommand_shortcutWordPresent_throwsCommandException()
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException, CommandException {
        ShortcutDoubles shortcutDoubles = new ShortcutDoubles(validTestingShortcutWord, validTestingCommandWord);
        model.addCommandShortcut(shortcutDoubles);
        CommandResult commandResult = getAddShortcutForCommand(validTestingShortcutWord,
                                                               validTestingCommandWord,
                                                               model).executeUndoableCommand();
        assertEquals(commandResult.feedbackToUser, String.format(ShortcutCommand.MESSAGE_SHORTCUT_AVAILABLE));
    }

    @Test
    public void executeUndoCommand_validInput_commandSuccess()
                             throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        ShortcutDoubles shortcutDoubles = new ShortcutDoubles(validTestingShortcutWord, validTestingCommandWord);
        expectedModel.addCommandShortcut(shortcutDoubles);
        assertCommandSuccess(getAddShortcutForCommand(validTestingShortcutWord,
                                                      validTestingCommandWord,
                                                      model), model,
                                                       String.format(ShortcutCommand.MESSAGE_SUCCESS), expectedModel);
    }

    /**
     * Generates a new AddCommand with the details of the given person.
     */
    private ShortcutCommand getAddShortcutForCommand(String shortcutWord, String commandWord, Model model) {
        ShortcutCommand command = new ShortcutCommand(commandWord, shortcutWord);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    @Test
    public void equals() throws Exception {
        ShortcutCommand deleteFirstCommand = getAddShortcutForCommand(validTestingShortcutWord,
                                                                      validTestingCommandWord,
                                                                      model);
        ShortcutCommand deleteSecondCommand = getAddShortcutForCommand("c",
                                                                       "clear",
                                                                        model);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ShortcutCommand deleteFirstCommandCopy = getAddShortcutForCommand("l",
                                                                          "list",
                                                                           model);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
