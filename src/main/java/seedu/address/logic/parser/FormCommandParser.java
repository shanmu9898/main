package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.FormCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.education.Subject;
import seedu.address.model.event.Time;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class FormCommandParser implements Parser<FormCommand> {

    private static final String MESSAGE_TIME_PERIOD_CONSTRAINTS = "The end time should be after the start time";

    /**
     * Parses the given {@code String} of arguments in the context of the FormCommand
     * and returns an FormCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FormCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_INDEX);

        String[] preamble = argMultimap.getPreamble().split(" ");
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_INDEX)
                || preamble.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FormCommand.MESSAGE_USAGE));
        }

        try {
            Subject subject = ParserUtil.parseSubject(preamble[0]);
            Name className = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME)).get();
            Time startTime = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_TIME)).get();
            Time endTime = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_TIME)).get();
            List<Index> indexList = ParserUtil.parseIndexes(argMultimap.getValue(PREFIX_INDEX)).get();

            checkArgument(Time.isValidTime(startTime, endTime), MESSAGE_TIME_PERIOD_CONSTRAINTS);

            return new FormCommand(className, subject, startTime, endTime, indexList);
        } catch (IllegalValueException ive) {
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
