package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.LIST_TYPE_APPOINTMENT;
import static seedu.address.model.Model.LIST_TYPE_TASK;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;
import seedu.address.model.event.exceptions.EventNotFoundException;

//@@author Sisyphus25
/**
 * Remove an appointment or task identified using its last displayed index from the address book.
 */
public class RemoveCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the event identified by the index number used in the last event listing.\n"
            + "Parameters: "
            + " EVENT_TYPE (could be appointment or task)"
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " appointment " + " 1";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Removed %1$s: %2$s";

    private final Index targetIndex;

    private String eventTypeOfDeletedTarget;

    private Object eventToBeDeleted;

    public RemoveCommand(Index targetIndex, String eventTypeOfDeletedTarget) {
        this.eventTypeOfDeletedTarget = eventTypeOfDeletedTarget;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(eventToBeDeleted);
        try {
            if (eventTypeOfDeletedTarget.equals(LIST_TYPE_APPOINTMENT)) {
                model.deleteAppointment((Appointment) eventToBeDeleted);
            } else if (eventTypeOfDeletedTarget.equals(LIST_TYPE_TASK)) {
                model.deleteTask((Task) eventToBeDeleted);
            }
        } catch (EventNotFoundException ive) {
            throw new AssertionError(String.format("The target %s cannot be missing", eventTypeOfDeletedTarget));
        }
        return new CommandResult(
                String.format(MESSAGE_DELETE_EVENT_SUCCESS, eventTypeOfDeletedTarget, eventToBeDeleted));
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        if (eventTypeOfDeletedTarget.equals(LIST_TYPE_APPOINTMENT)) {
            List<Appointment> lastShownList = model.getFilteredAppointmentList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
            }
            eventToBeDeleted = lastShownList.get(targetIndex.getZeroBased());
        } else if (eventTypeOfDeletedTarget.equals(LIST_TYPE_TASK)) {
            List<Task> lastShownList = model.getFilteredTaskList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
            }
            eventToBeDeleted = lastShownList.get(targetIndex.getZeroBased());
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && this.targetIndex.equals(((RemoveCommand) other).targetIndex) // state check
                && Objects.equals(this.eventToBeDeleted, ((RemoveCommand) other).eventToBeDeleted));
    }
}
