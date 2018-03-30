package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SetTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Task;
import seedu.address.model.event.Title;

/**
 * Parses input arguments and creates a new SetTaskCommand object
 */
public class SetTaskCommandParser implements Parser<SetTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetTaskCommand
     * and returns a SetTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetTaskCommand.MESSAGE_USAGE));
        }

        try {
            Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE)).get();
            EventTime time = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_END_TIME)).get();

            Task task = new Task(title, time);

            return new SetTaskCommand(task);
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
