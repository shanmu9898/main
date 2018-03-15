package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ImportCommandParserTest {

    private ImportCommandParser importCommandParser = new ImportCommandParser();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        importCommandParser.parse(null);

    }

    @Test
    public void parse_emptyString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        importCommandParser.parse(" ");

    }

    @Test
    public void parse_moreThanOneBlockOfString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        importCommandParser.parse("invalid two strings");
    }

    @Test
    public void parse_validString_success(){
        String input = "./data/importsamplefile.xml";
        ImportCommand expectedCommand = new ImportCommand("./data/importsamplefile.xml");
        assertParseSuccess(importCommandParser, input, expectedCommand);
    }




}
