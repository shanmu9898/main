package seedu.address.logic.commands;

//@@author LimShiMinJonathan

/**
 * Sorts contacts lexicographically.
 */

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts contacts lexicographically "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Contacts have been sorted!";

    @Override
    public CommandResult execute() {

        model.sortByNameFilteredPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

//@@author
