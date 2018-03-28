package seedu.address.logic.commands;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static seedu.address.logic.commands.ToggleCalendarViewCommand.MESSAGE_VIEW_TOGGLE_SUCCESS;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ToggleCalendarViewEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.testutil.EventsCollectorRule;

//@@author Sisyphus25
public class ToggleCalendarViewCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Test
    public void execute_help_success() throws CommandException {
        Character viewMode = 'd';
        CommandResult result = new ToggleCalendarViewCommand(viewMode).execute();
        assertEquals(MESSAGE_VIEW_TOGGLE_SUCCESS, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ToggleCalendarViewEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
