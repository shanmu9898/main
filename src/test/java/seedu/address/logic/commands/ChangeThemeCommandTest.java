package seedu.address.logic.commands;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static seedu.address.logic.commands.ChangeThemeCommand.MESSAGE_CHANGE_THEME_SUCCESS;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ThemeChangeEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.testutil.EventsCollectorRule;

//@@author Sisyphus25
public class ChangeThemeCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Test
    public void execute_help_success() throws CommandException {
        String theme = "dark";
        CommandResult result = new ChangeThemeCommand(theme).execute();
        assertEquals(MESSAGE_CHANGE_THEME_SUCCESS, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ThemeChangeEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
