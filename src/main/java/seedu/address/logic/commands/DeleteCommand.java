package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_DELETE_UNSUPPORTED;
import static seedu.address.model.Model.LIST_TYPE_APPOINTMENT;
import static seedu.address.model.Model.LIST_TYPE_CLASS;
import static seedu.address.model.Model.LIST_TYPE_CONTACT;
import static seedu.address.model.Model.LIST_TYPE_TASK;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.education.Class;
import seedu.address.model.education.exceptions.StudentClassNotFoundException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.PersonNotFoundException;

//@@author Sisyphus25
/**
 * Deletes an entry identified using it's last displayed index.
 */
public class DeleteCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the entry identified by the index number used in the last shown listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";
    public static final String MESSAGE_DELETE_CLASS_SUCCESS = "Deleted Class: %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;

    private Object toDelete;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult executeUndoableCommand() {
        if (toDelete instanceof Student) {
            try {
                model.deleteStudent((Student) toDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, toDelete));
            } catch (PersonNotFoundException pnfe) {
                throw new AssertionError("The target student cannot be missing");
            }
        } else if (toDelete instanceof Person) {
            try {
                model.deletePerson((Person) toDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, toDelete));
            } catch (PersonNotFoundException pnfe) {
                throw new AssertionError("The target person cannot be missing");
            }
        } else if (toDelete instanceof Appointment) {
            try {
                model.deleteAppointment((Appointment) toDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, toDelete));
            } catch (EventNotFoundException enfe) {
                throw new AssertionError("The target appointment cannot be missing");
            }
        } else if (toDelete instanceof Task) {
            try {
                model.deleteTask((Task) toDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, toDelete));
            } catch (EventNotFoundException enfe) {
                throw new AssertionError("The target task cannot be missing");
            }
        } else if (toDelete instanceof Class) {
            try {
                model.deleteClass((Class) toDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_CLASS_SUCCESS, toDelete));
            } catch (StudentClassNotFoundException scnfe) {
                throw new AssertionError("The target class cannot be missing");
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        String listType = model.getCurrentActiveListType();
        switch (listType) {

        case LIST_TYPE_CONTACT: {
            List<Person> lastShownList = model.getFilteredPersonList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            toDelete = lastShownList.get(targetIndex.getZeroBased());
            break;
        }

        case LIST_TYPE_APPOINTMENT: {
            List<Appointment> lastShownList = model.getFilteredAppointmentList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
            }
            toDelete = lastShownList.get(targetIndex.getZeroBased());
            break;
        }

        case LIST_TYPE_TASK: {
            List<Task> lastShownList = model.getFilteredTaskList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
            }
            toDelete = lastShownList.get(targetIndex.getZeroBased());
            break;
        }

        case LIST_TYPE_CLASS: {
            List<Class> lastShownList = model.getFilteredClassList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
            }
            toDelete = lastShownList.get(targetIndex.getZeroBased());
            break;
        }

        default:
            throw new CommandException(MESSAGE_DELETE_UNSUPPORTED);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && this.targetIndex.equals(((DeleteCommand) other).targetIndex) // state check
                && Objects.equals(this.toDelete, ((DeleteCommand) other).toDelete));
    }
}
