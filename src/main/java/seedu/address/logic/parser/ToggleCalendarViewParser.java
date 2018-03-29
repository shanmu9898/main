package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ToggleCalendarViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author Sisyphus25
/**
 * Parser for ToggleCalendarViewCommand
 */
public class ToggleCalendarViewParser implements Parser<ToggleCalendarViewCommand> {
    /**
     * Parses the given {@code viewMode} of arguments in the context of the ToggleCalendarViewParser
     * and returns an ToggleCalendarViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ToggleCalendarViewCommand parse(String args) throws ParseException {
        String viewMode = args.trim();
        if (viewMode.isEmpty() || !isValidViewMode(viewMode)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ToggleCalendarViewCommand.MESSAGE_USAGE));
        }
        return new ToggleCalendarViewCommand(viewMode.charAt(0));
    }

    /**
     *
     * @param str
     * @return whether if the string is a valid view mode or not
     */
    private boolean isValidViewMode(String str) {
        if (str.length() != 1) {
            return false;
        }
        switch (str.charAt(0)) {
        case('w'):
        case('d'):
        case('m'):
            return true;
        default:
            return false;
        }
    }
}
