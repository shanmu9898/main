package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ONLY_STUDENTS;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all ";
    private static final String MESSAGE_INVALID_TYPE = "TYPE is missing or invalid";

    public static final String TYPE_STUDENT = "students";
    public static final String TYPE_CONTACT = "contacts";
    private final String typeToListed;

    public ListCommand(String typeArg) {
        typeToListed = typeArg.trim();
    }

    @Override
    public CommandResult execute() throws CommandException {
        switch (typeToListed) {
        case TYPE_CONTACT:
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_CONTACT);

        case TYPE_STUDENT:
            model.updateFilteredPersonList(PREDICATE_SHOW_ONLY_STUDENTS);
            return new CommandResult(MESSAGE_SUCCESS + TYPE_STUDENT);

        default:
            throw new CommandException(MESSAGE_INVALID_TYPE);
        }
    }
}
