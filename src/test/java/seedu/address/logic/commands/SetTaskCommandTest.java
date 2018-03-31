package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_2;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Task;
import seedu.address.testutil.modelstub.ModelStub;
import seedu.address.testutil.modelstub.ModelStubAcceptingTaskAdded;
import seedu.address.testutil.modelstub.ModelStubThrowingDuplicateEventException;

//@@author Sisyphus25
public class SetTaskCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SetTaskCommand(null);
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();

        CommandResult commandResult = getSetTaskCommand(TYPICAL_TASK_2, modelStub).execute();

        assertEquals(String.format(SetTaskCommand.MESSAGE_SUCCESS, TYPICAL_TASK_2), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(TYPICAL_TASK_2), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubThrowingDuplicateEventException();

        thrown.expect(CommandException.class);
        thrown.expectMessage(SetTaskCommand.MESSAGE_DUPLICATE_TASK);

        getSetTaskCommand(TYPICAL_TASK_1, modelStub).execute();
    }

    @Test
    public void equals() {
        SetTaskCommand addTask1 =
                new SetTaskCommand(TYPICAL_TASK_1);
        SetTaskCommand addTask2 =
                new SetTaskCommand(TYPICAL_TASK_2);

        // same object -> returns true
        assertTrue(addTask1.equals(addTask1));

        // same values -> returns true
        SetTaskCommand addAppointment1Copy =
                new SetTaskCommand(TYPICAL_TASK_1);
        assertTrue(addTask1.equals(addAppointment1Copy));

        // different types -> returns false
        assertFalse(addTask1.equals(1));

        // null -> returns false
        assertFalse(addTask1.equals(null));

        // different tasks -> returns false
        assertFalse(addTask1.equals(addTask2));
    }

    /**
     * Generates a new SetTaskCommand with the details of the given task.
     */
    private SetTaskCommand getSetTaskCommand(Task task, Model model) {
        SetTaskCommand command = new SetTaskCommand(task);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

}
