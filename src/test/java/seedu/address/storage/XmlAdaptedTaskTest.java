package seedu.address.storage;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.storage.XmlAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_2;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Title;
import seedu.address.testutil.Assert;

//@@author Sisyphus25
public class XmlAdaptedTaskTest {

    private static final String INVALID_TITLE = "  ";
    private static final String INVALID_TIME = "not a time stamp";

    @Test
    public void toModelType_validTaskDetails_returnsPerson() throws Exception {
        XmlAdaptedTask task = new XmlAdaptedTask(TYPICAL_TASK_1);
        assertEquals(TYPICAL_TASK_1, task.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(INVALID_TITLE, VALID_END_TIME);
        String expectedMessage = Title.MESSAGE_TITLE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(VALID_TITLE, INVALID_TIME);
        String expectedMessage = EventTime.MESSAGE_TIME_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(null, VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(VALID_TITLE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Time");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void equals() {
        XmlAdaptedTask task = new XmlAdaptedTask(TYPICAL_TASK_1);

        //same object
        assertTrue(task.equals(task));

        //same value
        XmlAdaptedTask taskCopy = new XmlAdaptedTask(TYPICAL_TASK_1);
        assertTrue(task.equals(taskCopy));

        //different type
        assertFalse(task.equals(TYPICAL_TASK_1));

        //different obj
        XmlAdaptedTask anotherTask = new XmlAdaptedTask(TYPICAL_TASK_2);
        assertFalse(task.equals(anotherTask));
    }
}
