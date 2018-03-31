package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteShortcutCommandParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private DeleteShortcutCommandParser deleteShortcutCommandParser = new DeleteShortcutCommandParser();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        deleteShortcutCommandParser.parse(null);

    }

    @Test
    public void parse_emptyString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        deleteShortcutCommandParser.parse(" ");

    }

    @Test
    public void parse_validString_success() {
        String inputCommandWord = "list";
        String inputShortcutWord = "l";
        String input = "list l";
        DeleteShortcutCommand expectedCommand = new DeleteShortcutCommand(inputCommandWord, inputShortcutWord);
        assertParseSuccess(deleteShortcutCommandParser, input, expectedCommand);
    }
}
