package seedu.address.logic.commands;

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

public class DeleteShortcutCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;
    private Model expectedModel;
    private final String validtestingCommandWord = "list";
    private final String validtestingShortcutWord = "l";
    private final String invalidtestingCommandWord = "king";

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullCommandWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteShortcutCommand(null, validtestingShortcutWord);
    }

    @Test
    public void constructor_nullShortcutWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteShortcutCommand(validtestingCommandWord, null);
    }

    @Test
    public void executeUndoCommand_invalidCommandWord_throwsCommandException() throws CommandException {
        thrown.expect(CommandException.class);
        CommandResult commandResult = getDeleteShortcutForCommand(validtestingShortcutWord,
                                                                  invalidtestingCommandWord,
                                                                  model).executeUndoableCommand();
    }

    @Test
    public void executeUndoCommand_inputNotPresent_throwsCommandException() throws CommandException {
        thrown.expect(CommandException.class);
        CommandResult commandResult = getDeleteShortcutForCommand(validtestingShortcutWord,
                                                                  validtestingCommandWord,
                                                                  model).executeUndoableCommand();
    }

    @Test
    public void executeUndoCommand_validInput_commandSuccess()
                                        throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        ShortcutDoubles shortcutDoubles = new ShortcutDoubles(validtestingShortcutWord, validtestingCommandWord);
        model.addCommandShortcut(shortcutDoubles);
        assertCommandSuccess(getDeleteShortcutForCommand(validtestingShortcutWord,
                                                         validtestingCommandWord,
                                                         model),
                                                         model,
                                                         String.format(DeleteShortcutCommand
                                                                       .MESSAGE_DELETE_SHORTCUT_SUCCESS),
                                                         expectedModel);
    }

    @Test
    public void equals() throws Exception {
        DeleteShortcutCommand deleteFirstCommand = getDeleteShortcutForCommand(validtestingShortcutWord,
                                                                               validtestingCommandWord,
                                                                               model);
        DeleteShortcutCommand deleteSecondCommand = getDeleteShortcutForCommand("c",
                                                                                "clear",
                                                                                 model);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteShortcutCommand deleteFirstCommandCopy = getDeleteShortcutForCommand("l",
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

    /**
     * Generates a new AddCommand with the details of the given person.
     */
    private DeleteShortcutCommand getDeleteShortcutForCommand(String shortcutWord, String commandWord, Model model) {
        DeleteShortcutCommand command = new DeleteShortcutCommand(commandWord, shortcutWord);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
}
