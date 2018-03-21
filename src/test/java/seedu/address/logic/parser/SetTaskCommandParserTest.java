package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.SetTaskCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Task;
import seedu.address.model.event.Title;
import seedu.address.testutil.EventBuilder;

public class SetTaskCommandParserTest {
    private SetTaskCommandParser parser = new SetTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedTask = new EventBuilder(VALID_TITLE, VALID_END_TIME).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC + END_TIME_DESC,
                new SetTaskCommand((Task) expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTaskCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE + END_TIME_DESC,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, TITLE_DESC + VALID_END_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE + VALID_END_TIME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + END_TIME_DESC, Title.MESSAGE_TITLE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, TITLE_DESC + INVALID_END_TIME_DESC, EventTime.MESSAGE_TIME_CONSTRAINTS);
    }
}
