package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.ListCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.ListCommand.TYPE_APPOINTMENT;
import static seedu.address.logic.commands.ListCommand.TYPE_CONTACT;
import static seedu.address.logic.commands.ListCommand.TYPE_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ToggleListEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.ui.testutil.EventsCollectorRule;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model;
    private Model expectedModel;
    private ListCommand listCommand;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_personListIsNotFiltered_showsSameList() {
        listCommand = new ListCommand(TYPE_CONTACT);
        listCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        assertCommandSuccess(listCommand, model, MESSAGE_SUCCESS + TYPE_CONTACT, expectedModel);
    }

    @Test
    public void execute_personListIsFiltered_showsEverything() {
        listCommand = new ListCommand(TYPE_CONTACT);
        listCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        showPersonAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(listCommand, model, MESSAGE_SUCCESS + TYPE_CONTACT, expectedModel);
    }

    //@@author Sisyphus25
    @Test
    public void execute_listEvent_success() throws CommandException {
        assertListEventSuccess(TYPE_APPOINTMENT);
        assertListEventSuccess(TYPE_TASK);
    }

    /**
     * assert if execution of listing of event is successful or not
     * @throws CommandException
     */
    private void assertListEventSuccess(String eventType) throws CommandException {
        listCommand = new ListCommand(eventType);
        listCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        CommandResult result = listCommand.execute();
        assertEquals(MESSAGE_SUCCESS + eventType, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ToggleListEvent);
    }
}
