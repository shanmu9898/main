package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteShortcutCommand object
 */
public class DeleteShortcutCommandParser implements Parser<DeleteShortcutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteShortcutCommand
     * and returns a DeleteShortcutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteShortcutCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] splitWords = trimmedArgs.split(" ");
        if (splitWords.length > 2 || splitWords.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteShortcutCommand.MESSAGE_USAGE));
        } else {
            return new DeleteShortcutCommand(splitWords[0], splitWords[1]);
        }
    }
}
