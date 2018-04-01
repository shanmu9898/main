package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Task;
import seedu.address.model.event.exceptions.DuplicateEventException;

//@@author Sisyphus25
/**
 * Adds a task to the address book.
 */
public class SetTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "set_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_END_TIME + "DATE TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Mark papers "
            + PREFIX_END_TIME + "20/05/2018 12:00 ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Task toAdd;

    /**
     * Creates a SetTaskCommand to add the specified {@code Task}
     */
    public SetTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicateEventException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetTaskCommand // instanceof handles nulls
                && toAdd.equals(((SetTaskCommand) other).toAdd));
    }
}
