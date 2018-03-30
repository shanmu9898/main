package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author Sisyphus25
/**
 * Parser for ListCommand
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code args} of arguments in the context of the ListCommandParser
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String item = args.trim();
        if (!isValidItem(item)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListCommand.MESSAGE_USAGE));
        }
        return new ListCommand(item);
    }

    /**
     * @param str
     * @return whether if the string is a valid view mode or not
     */
    private boolean isValidItem(String str) {
        switch (str) {
        case(ListCommand.TYPE_CONTACT):
        case(ListCommand.TYPE_STUDENT):
        case(ListCommand.TYPE_APPOINTMENT):
        case(ListCommand.TYPE_TASK):
            return true;
        default:
            return false;
        }
    }
}
