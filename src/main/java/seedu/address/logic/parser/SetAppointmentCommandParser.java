package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_TO_MEET_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SetAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Title;

/**
 * Parses input arguments and creates a new SetAppointmentCommand object
 */
public class SetAppointmentCommandParser implements Parser<SetAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetAppointmentCommand
     * and returns a SetAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_START_TIME,
                        PREFIX_END_TIME, PREFIX_PERSON_TO_MEET_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetAppointmentCommand.MESSAGE_USAGE));
        }

        try {
            Index index = null;
            Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE)).get();
            EventTime startTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_START_TIME)).get();
            EventTime endTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_END_TIME)).get();
            Optional<Index> optionalIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PERSON_TO_MEET_INDEX));
            if (optionalIndex.isPresent()) {
                index = optionalIndex.get();
            }
            Appointment appointment = new Appointment(title, startTime, endTime);

            return new SetAppointmentCommand(appointment, index);
        } catch (IllegalValueException | IllegalArgumentException ive) {
            throw new ParseException(ive.getMessage(), ive);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
