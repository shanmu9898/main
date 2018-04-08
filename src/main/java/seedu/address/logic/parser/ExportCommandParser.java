package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.tag.Tag;

//@@author shanmu9898
/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RANGE,
                PREFIX_TAG, PREFIX_PATH, PREFIX_TYPE);

        String[] preambleArgs = argMultiMap.getPreamble().split(" ");
        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_RANGE, PREFIX_PATH, PREFIX_TYPE)
                || preambleArgs.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        String name = argMultiMap.getValue(PREFIX_NAME).orElse("");
        String range = argMultiMap.getValue(PREFIX_RANGE).orElse("all");
        String tag = argMultiMap.getValue(PREFIX_TAG).orElse("shouldnotbethistag");
        String path = argMultiMap.getValue(PREFIX_PATH).orElse("");
        String type = argMultiMap.getValue(PREFIX_TYPE).orElse("normal");

        if (!(type.equals("excel") || type.equals("normal"))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        Tag tagExport = new Tag(tag);
        return new ExportCommand(range, tagExport, path, name, type);


    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }



}
