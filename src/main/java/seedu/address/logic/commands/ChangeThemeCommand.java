package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ThemeChangeEvent;
import seedu.address.logic.commands.exceptions.CommandException;

//@@author Sisyphus25
/**
 * Change theme of the GUI.
 */
public class ChangeThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Change the theme of TeachConnect.\n"
            + "Parameters: THEME\n"
            + "Example: " + COMMAND_WORD + " dark";

    public static final String MESSAGE_CHANGE_THEME_SUCCESS = "Theme changed";

    public static final String MESSAGE_INVALID_THEME = "Not a valid theme";

    private final String theme;

    public ChangeThemeCommand(String theme) {
        requireNonNull(theme);
        this.theme = theme;
    }

    @Override
    public CommandResult execute() throws CommandException {
        EventsCenter.getInstance().post(new ThemeChangeEvent(theme));
        return new CommandResult(MESSAGE_CHANGE_THEME_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChangeThemeCommand // instanceof handles nulls
                && this.theme.equals(((ChangeThemeCommand) other).theme)); // state check
    }
}
