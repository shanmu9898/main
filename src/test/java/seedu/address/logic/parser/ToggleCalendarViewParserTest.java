package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.ToggleCalendarViewCommand;

//@@author Sisyphus25
public class ToggleCalendarViewParserTest {
    private ToggleCalendarViewParser parser = new ToggleCalendarViewParser();

    @Test
    public void parse_validArgs_returnsToggleCalendarViewCommand() {
        assertParseSuccess(parser, "d", new ToggleCalendarViewCommand('d'));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "day",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ToggleCalendarViewCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "x",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ToggleCalendarViewCommand.MESSAGE_USAGE));
    }
}
