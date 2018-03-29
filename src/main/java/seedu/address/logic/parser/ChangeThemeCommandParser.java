package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.THEME_LIST;

import java.util.Arrays;

import seedu.address.logic.commands.ChangeThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author Sisyphus25
/**
 * Parses input arguments and creates a new ChangeThemeCommand object
 */
public class ChangeThemeCommandParser implements Parser<ChangeThemeCommand> {
    /**
     * Parses the given {@code viewMode} of arguments in the context of the ChangeThemeCommandParser
     * and returns an ChangeThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeThemeCommand parse(String args) throws ParseException {
        String theme = args.trim();
        if (!isValidTheme(theme)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ChangeThemeCommand.MESSAGE_INVALID_THEME));
        }
        return new ChangeThemeCommand(theme);
    }

    /**
     *
     * @param theme
     * @return whether if {@code theme} is a valid theme name
     */
    private boolean isValidTheme(String theme) {
        return !theme.isEmpty() && Arrays.asList(THEME_LIST).contains(theme);
    }
}
