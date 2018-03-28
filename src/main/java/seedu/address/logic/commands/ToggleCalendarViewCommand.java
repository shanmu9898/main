package seedu.address.logic.commands;

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
            + "View mode: Day view: d, Week view: w, Month view: m, Year view: y\n"
            + "Example: " + COMMAND_WORD + " d";

    public static final String MESSAGE_VIEW_TOGGLE_SUCCESS = "View changed.";

    private Character viewMode;

    public ToggleCalendarViewCommand(Character viewMode) {
        this.viewMode = viewMode;
    }
    @Override
    public CommandResult execute() throws CommandException {
        EventsCenter.getInstance().post(new ToggleCalendarViewEvent(viewMode));
        return new CommandResult(MESSAGE_VIEW_TOGGLE_SUCCESS);
    }
}
