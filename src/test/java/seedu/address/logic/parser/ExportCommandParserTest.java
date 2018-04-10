package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

//@@author shanmu9898
public class ExportCommandParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ExportCommandParser exportCommandParser = new ExportCommandParser();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        exportCommandParser.parse(null);
    }

    @Test
    public void parse_differentType_invalidFormatCommandResult() throws ParseException {
        Tag testingTag = new Tag("shouldnotbethistag");
        String testingInput = " n/name r/all p/./data te/random";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name", "normal");
        assertParseFailure(exportCommandParser, testingInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportCommand.MESSAGE_USAGE));


    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Tag testingTag = new Tag("shouldnotbethistag");
        String testingInput = " n/name r/all p/./data te/xml";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name", "xml");
        assertParseSuccess(exportCommandParser, testingInput, expectedCommand);
    }



    @Test
    public void parse_allfieldsPresent_success() {
        Tag testingTag = new Tag("friends");
        String testingInput = " n/name r/all t/friends p/./data te/xml";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name", "xml");
        assertParseSuccess(exportCommandParser, testingInput, expectedCommand);
    }

}
