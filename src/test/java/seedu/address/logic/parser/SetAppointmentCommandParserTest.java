package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.SetAppointmentCommand;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Title;
import seedu.address.testutil.AppointmentBuilder;

//@@author Sisyphus25
public class SetAppointmentCommandParserTest {
    private SetAppointmentCommandParser parser = new SetAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(VALID_TITLE, VALID_START_TIME, VALID_END_TIME).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC + START_TIME_DESC + END_TIME_DESC,
                new SetAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no personToMeet
        Appointment expectedAppointment = new AppointmentBuilder(VALID_TITLE, VALID_START_TIME, VALID_END_TIME).build();
        assertParseSuccess(parser, TITLE_DESC + START_TIME_DESC + END_TIME_DESC,
                new SetAppointmentCommand((expectedAppointment)));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetAppointmentCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE + START_TIME_DESC + END_TIME_DESC,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, TITLE_DESC + VALID_START_TIME + END_TIME_DESC,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, TITLE_DESC + START_TIME_DESC + VALID_END_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE + VALID_START_TIME + VALID_END_TIME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + START_TIME_DESC + END_TIME_DESC,
                Title.MESSAGE_TITLE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, TITLE_DESC + INVALID_START_TIME_DESC + END_TIME_DESC,
                EventTime.MESSAGE_TIME_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, TITLE_DESC + START_TIME_DESC + INVALID_END_TIME_DESC,
                EventTime.MESSAGE_TIME_CONSTRAINTS);
    }
}
