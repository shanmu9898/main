package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

/**
 * Deletes a specific shortcut from the addressbook.
 */
public class DeleteShortcutCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "delete_shortcut";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " CommandWord " + " ShortcutWord "
                                               + " :Deletes a shortcut for any command word";
    public static final String MESSAGE_DELETE_SHORTCUT_SUCCESS = "The shortcut has been deleted!";
    private final String shortcutWord;

    private final String commandWord;

    private ShortcutDoubles commandShortcut;


    public DeleteShortcutCommand(String commandWord, String shortcutWord) {
        requireNonNull(commandWord);
        requireNonNull(shortcutWord);
        this.commandWord = commandWord;
        this.shortcutWord = shortcutWord;
        commandShortcut = new ShortcutDoubles(shortcutWord, commandWord);
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(commandShortcut);
        try {
            model.deleteCommandShortcut(commandShortcut);
        } catch (UniqueShortcutDoublesList.CommandShortcutNotFoundException csnf) {
            throw new CommandException("Please enter a valid Shortcut Command you have saved");
        }

        return new CommandResult(String.format(MESSAGE_DELETE_SHORTCUT_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteShortcutCommand // instanceof handles nulls
                && this.shortcutWord.equals(((DeleteShortcutCommand) other).shortcutWord) // state check
                && this.commandWord.equals(((DeleteShortcutCommand) other).commandWord) // state check
                && Objects.equals(this.commandShortcut, ((DeleteShortcutCommand) other).commandShortcut));
    }
}
