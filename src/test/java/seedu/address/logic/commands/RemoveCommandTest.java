package seedu.address.logic.commands;

//@@author Sisyphus25

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Appointment;

/**
 * Contains Test for {@code RemoveCommand}
 */
public class RemoveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws Exception {
        Appointment eventToDelete = model.getFilteredAppointmentList().get(INDEX_FIRST.getZeroBased());
        RemoveCommand removeCommandRemovingAppointment = prepareCommand(INDEX_FIRST, "appointment");
        String expectedMessage =
                String.format(RemoveCommand.MESSAGE_DELETE_EVENT_SUCCESS, "appointment", eventToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAppointment(eventToDelete);

        assertCommandSuccess(removeCommandRemovingAppointment, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() throws Exception {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        RemoveCommand removeCommandRemovingTask = prepareCommand(outOfBoundIndex, "task");

        assertCommandFailure(removeCommandRemovingTask, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }
    @Test
    public void equals() throws Exception {
        RemoveCommand removeCommandRemovingAppointment = prepareCommand(INDEX_FIRST, "appointment");
        RemoveCommand removeCommandRemovingTask = prepareCommand(INDEX_SECOND, "task");

        // same object -> returns true
        assertTrue(removeCommandRemovingAppointment.equals(removeCommandRemovingAppointment));

        // same values -> returns true
        RemoveCommand removeCommandRemovingAppointmentCopy = prepareCommand(INDEX_FIRST, "appointment");
        assertTrue(removeCommandRemovingAppointment.equals(removeCommandRemovingAppointmentCopy));

        // one command preprocessed when previously equal -> returns false
        removeCommandRemovingAppointmentCopy.preprocessUndoableCommand();
        assertFalse(removeCommandRemovingAppointment.equals(removeCommandRemovingAppointmentCopy));

        // different types -> returns false
        assertFalse(removeCommandRemovingAppointment.equals(1));

        // null -> returns false
        assertFalse(removeCommandRemovingAppointment.equals(null));

        // different person -> returns false
        assertFalse(removeCommandRemovingAppointment.equals(removeCommandRemovingTask));
    }

    /**
     * Returns a {@code RemoveCommand} with the parameter {@code index}, {@code eventType}.
     */
    private RemoveCommand prepareCommand(Index index, String eventType) {
        RemoveCommand removeCommand = new RemoveCommand(index, eventType);
        removeCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return removeCommand;
    }
}
