package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

/**
 *
 */
public class ShortcutCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "shortcut";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " CommandWord " + " ShortcutWord "
                                               + " :Creates a shortcut for any command word";
    public static final String MESSAGE_SHORTCUT_AVAILABLE = "This shortcut already exists!";
    public static final String MESSAGE_SUCCESS = "Successfully added the shortcut";
    public static final String MESSAGE_NO_COMMAND_TO_MAP = "The command word is invalid and hence cant be mapped!";

    private final String shortcutWord;

    private final String commandWord;

    private List<ShortcutDoubles> commandsList;

    private final String[] commandsPresent = {"add", "clear", "delete", "edit", "exit", "export", "find",
                                              "help", "history", "import", "list", "redo", "undo", "select",
                                              "set_appointment", "set_task", "shortcut", "undo", "calendar",
                                              "delete_shortcut"};

    public ShortcutCommand(String commandWord, String shortcutWord) {
        requireNonNull(commandWord);
        requireNonNull(shortcutWord);
        this.shortcutWord = shortcutWord;
        this.commandWord = commandWord;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        commandsList = model.getFilteredCommandsList();
        if (commandsList != null) {
            if (checkIfCommandPresent()) {
                return new CommandResult(String.format(MESSAGE_SHORTCUT_AVAILABLE));
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

    /**
     * Checks if the shortcut command is valid or not
     * @return whether true or false
     */
    private boolean checkIfCommandPresent() throws CommandException {
        if (!containsKeyWord(commandWord)) {
            throw new CommandException(MESSAGE_NO_COMMAND_TO_MAP);
        }
        for (ShortcutDoubles s : commandsList) {
            if (s.shortcutWord.equals(shortcutWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the command word is in the Array of commands present
     * @param commandWord
     * @return whether true or false
     */
    private boolean containsKeyWord(String commandWord) {
        for (String s : commandsPresent) {
            if (s.equals(commandWord)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutCommand // instanceof handles nulls
                && this.shortcutWord.equals(((ShortcutCommand) other).shortcutWord) // state check
                && this.commandWord.equals(((ShortcutCommand) other).commandWord)); // state check
    }
}
