package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_MATH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_PHYS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALD_END_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLASS_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_SUBJECT_MATH;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_SUBJECT_PHYS;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_MATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FormCommand;
import seedu.address.model.education.Subject;
import seedu.address.model.event.Time;
import seedu.address.model.person.Name;

//@@author randypx
public class FormCommandParserTest {
    private FormCommandParser parser = new FormCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Subject expectedSubject = new Subject(VALID_SUBJECT_MATH);
        Name expectedClassName = new Name(VALID_CLASS_MATH);
        Time expectedStartDate = new Time(VALID_START_DATE, true);
        Time expectedEndDate = new Time(VALID_END_DATE, true);
        List<Index> expectedIndex =
                new ArrayList<>(Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2)));

        // subject preamble with whitespace
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + "  " + CLASS_MATH_DESC
                + "  " + START_DATE_DESC + "  " + END_DATE_DESC
                + "  " + PREFIX_INDEX + "1,2   ", new FormCommand(expectedClassName, expectedSubject,
                expectedStartDate, expectedEndDate, expectedIndex));

        // multiple names - last name accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_PHYS_DESC + CLASS_MATH_DESC
                        + START_DATE_DESC + END_DATE_DESC + " " + PREFIX_INDEX + "1,2",
                new FormCommand(expectedClassName, expectedSubject, expectedStartDate, expectedEndDate,
                        expectedIndex));

        // multiple start dates - last start date accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC
                + " " + PREFIX_START_TIME + VALID_DATE + START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", new FormCommand(expectedClassName,
                expectedSubject, expectedStartDate, expectedEndDate, expectedIndex));

        // multiple end date - last end date accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC
                + START_DATE_DESC + " " + PREFIX_END_TIME + VALID_DATE
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", new FormCommand(expectedClassName,
                expectedSubject, expectedStartDate, expectedEndDate, expectedIndex));

        // multiple index - last index accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC
                        + START_DATE_DESC + " " + PREFIX_END_TIME + VALID_DATE
                        + END_DATE_DESC + " " + PREFIX_INDEX + "3,4" + " " + PREFIX_INDEX + "1,2",
                new FormCommand(expectedClassName, expectedSubject, expectedStartDate, expectedEndDate,
                        expectedIndex));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FormCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + VALID_CLASS_MATH + START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + VALID_START_DATE
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", expectedMessage);

        // missing end date prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + START_DATE_DESC
                + VALID_END_DATE + " " + PREFIX_INDEX + "1,2", expectedMessage);

        // missing index prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + START_DATE_DESC
                + END_DATE_DESC + " 1,2", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SUBJECT_MATH + VALID_CLASS_MATH + VALID_START_DATE
                + VALID_END_DATE + " 1,2", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + INVALID_CLASS_NAME_DESC
                + START_DATE_DESC + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", Name.MESSAGE_NAME_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + INVALID_START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", Time.MESSAGE_DATE_CONSTRAINTS);

        // invalid index
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "a,b", MESSAGE_INVALID_INDEX);

        // invalid timeline
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + " " + PREFIX_START_TIME
                        + VALID_END_DATE + " " + PREFIX_END_TIME + VALID_END_DATE + " " + PREFIX_INDEX + "1,2",
                Time.MESSAGE_TIME_PERIOD_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + INVALID_CLASS_NAME_DESC
                        + START_DATE_DESC + INVALD_END_DATE_DESC + " " + PREFIX_INDEX + "1,2",
                Name.MESSAGE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_multiplePreamble_failure() {
        assertParseFailure(parser, PREAMBLE_SUBJECT_PHYS + PREAMBLE_SUBJECT_MATH
                        + VALID_CLASS_MATH + START_DATE_DESC + END_DATE_DESC + " " + PREFIX_INDEX + "1,2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FormCommand.MESSAGE_USAGE));

    }
}
