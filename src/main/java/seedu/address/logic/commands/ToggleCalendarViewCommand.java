package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleCalendarViewEvent;
import seedu.address.logic.commands.exceptions.CommandException;

//@@author Sisyphus25
/**
 * Command to change calendar view
 */
public class ToggleCalendarViewCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles calendar view. \n"
            + "Parameter: VIEW_MODE\n"
            + "View mode: Day view: d, Week view: w, Month view: m\n"
            + "Example: " + COMMAND_WORD + " d";

    public static final String MESSAGE_VIEW_TOGGLE_SUCCESS = "View changed.";

    private Character viewMode;

    public ToggleCalendarViewCommand(Character viewMode) {
        requireNonNull(viewMode);
        this.viewMode = viewMode;
    }
    @Override
    public CommandResult execute() throws CommandException {
        EventsCenter.getInstance().post(new ToggleCalendarViewEvent(viewMode));
        return new CommandResult(MESSAGE_VIEW_TOGGLE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ToggleCalendarViewCommand // instanceof handles nulls
                && this.viewMode == ((ToggleCalendarViewCommand) other).viewMode); // state check
    }
}
