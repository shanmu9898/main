package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.ListCommand;

//@@author Sisyphus25
public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "contacts", new ListCommand(ListCommand.TYPE_CONTACT));
        assertParseSuccess(parser, "students", new ListCommand(ListCommand.TYPE_STUDENT));
        assertParseSuccess(parser, "tasks", new ListCommand(ListCommand.TYPE_TASK));
        assertParseSuccess(parser, "appointments", new ListCommand(ListCommand.TYPE_APPOINTMENT));
        assertParseSuccess(parser, "shortcuts", new ListCommand(ListCommand.TYPE_SHORTCUT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "ffffffd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "event",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
