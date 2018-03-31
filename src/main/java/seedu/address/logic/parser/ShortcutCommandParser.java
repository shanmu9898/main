package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser
 */
public class ShortcutCommandParser implements Parser<ShortcutCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShortcutCommand
     * and returns an ShortcutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShortcutCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] splitWords = trimmedArgs.split(" ");
        if (splitWords.length > 2 || splitWords.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShortcutCommand.MESSAGE_USAGE));
        } else {
            return new ShortcutCommand(splitWords[0], splitWords[1]);
        }
    }
}
