package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ONLY_STUDENTS;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleListEvent;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all ";

    public static final String TYPE_CONTACT = "contacts";
    public static final String TYPE_STUDENT = "students";
    public static final String TYPE_APPOINTMENT = "appointments";
    public static final String TYPE_TASK = "tasks";
    public static final String TYPE_SHORTCUT = "shortcuts";
    public static final String TYPE_CLASS = "classes";
    private static final String MESSAGE_INVALID_TYPE = "TYPE is missing or invalid";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists selected type. \n"
            + "Parameter: TYPE. "
            + "Accepted TYPEs are " + TYPE_CONTACT + " " + TYPE_STUDENT
            + " " + TYPE_APPOINTMENT + " " + TYPE_TASK
            + " " + TYPE_SHORTCUT + " " + TYPE_CLASS + "\n"
            + "Example: " + COMMAND_WORD + " appointments \n";

    private final String type;

    public ListCommand(String type) {
        this.type = type;
    }

    //@@author Sisyphus25
    @Override
    public CommandResult execute() throws CommandException {
        switch (type) {
        case TYPE_CONTACT:
            evokeToggleListEvent(TYPE_CONTACT);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_CONTACT);

        case TYPE_STUDENT:
            evokeToggleListEvent(TYPE_CONTACT);
            model.updateFilteredPersonList(PREDICATE_SHOW_ONLY_STUDENTS);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_STUDENT);

        case TYPE_APPOINTMENT:
            evokeToggleListEvent(TYPE_APPOINTMENT);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_APPOINTMENT);

        case TYPE_TASK:
            evokeToggleListEvent(TYPE_TASK);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_TASK);

        case TYPE_SHORTCUT:
            evokeToggleListEvent(TYPE_SHORTCUT);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_SHORTCUT);

        case TYPE_CLASS:
            evokeToggleListEvent(TYPE_CLASS);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_CLASS);

        default:
            throw new CommandException(MESSAGE_INVALID_TYPE);
        }
    }

    /** Raises an event to indicate the change of list view */
    private void evokeToggleListEvent(String type) {
        model.changeCurrentActiveListType(type);
        EventsCenter.getInstance().post(new ToggleListEvent(type));
    }

    //@@author
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && this.type.equals(((ListCommand) other).type)); // state check
    }
}
