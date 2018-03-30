package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

/**
 *
 */
public class ShortcutCommand extends Command {

    public static final String COMMAND_WORD = "shortcut";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " :Creates a shortcut for any command word";
    private static final String MESSAGE_SHORTCUT_AVAILABLE = "This shortcut already exists!";
    private static final String MESSAGE_SUCCESS = "Successfully added the shortcut";

    private final String shortcutWord;

    private final String commandWord;

    private List<ShortcutDoubles> commandsList;

    public ShortcutCommand(String commandWord, String shortcutWord) {
        requireNonNull(commandWord, shortcutWord);
        this.shortcutWord = shortcutWord;
        this.commandWord = commandWord;
    }

    @Override
    public CommandResult execute() throws CommandException {
        commandsList = model.getFilteredCommandsList();
        if (commandsList != null) {
            for (ShortcutDoubles s : commandsList) {
                if (s.commandWord.equals(commandWord) || s.shortcutWord.equals(shortcutWord)) {
                    return new CommandResult(String.format(MESSAGE_SHORTCUT_AVAILABLE));
                }
            }
        }

        ShortcutDoubles toAdd = new ShortcutDoubles(shortcutWord, commandWord);
        try {
            model.addCommandShortcut(toAdd);
        } catch (UniqueShortcutDoublesList.DuplicateShortcutDoublesException e) {
            return new CommandResult(String.format(MESSAGE_SHORTCUT_AVAILABLE));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    public List<ShortcutDoubles> getCommandsList() {
        return commandsList;
    }
}
