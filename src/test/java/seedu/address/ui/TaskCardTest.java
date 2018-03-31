package seedu.address.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_2;

import org.junit.Test;

import seedu.address.model.event.Task;

//@@author Sisyphus25
public class TaskCardTest extends GuiUnitTest {

    @Test
    public void equals() {
        Task task = TYPICAL_TASK_2;
        TaskCard taskCard = new TaskCard(task, 0);

        // same task, same index -> returns true
        TaskCard copy = new TaskCard(task, 0);
        assertTrue(taskCard.equals(copy));

        // same object -> returns true
        assertTrue(taskCard.equals(taskCard));

        // null -> returns false
        assertFalse(taskCard.equals(null));

        // different types -> returns false
        assertFalse(taskCard.equals(0));

        // different task, same index -> returns false
        Task differentTask = TYPICAL_TASK_1;
        assertFalse(taskCard.equals(new TaskCard(differentTask, 0)));

        // same task, different index -> returns false
        assertFalse(taskCard.equals(new TaskCard(task, 1)));
    }

}
