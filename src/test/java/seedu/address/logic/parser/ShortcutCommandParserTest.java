package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ShortcutCommandParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ShortcutCommandParser shortcutCommandParser = new ShortcutCommandParser();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        shortcutCommandParser.parse(null);

    }

    @Test
    public void parse_emptyString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        shortcutCommandParser.parse(" ");

    }

    @Test
    public void parse_validString_success() {
        String inputCommandWord = "list";
        String inputShortcutWord = "l";
        String input = "list l";
        ShortcutCommand expectedCommand = new ShortcutCommand(inputCommandWord, inputShortcutWord);
        assertParseSuccess(shortcutCommandParser, input, expectedCommand);
    }
}
