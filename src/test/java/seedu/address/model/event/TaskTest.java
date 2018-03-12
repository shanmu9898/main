package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.Assert;

//@@author Sisyphus25
public class TaskTest {
    private static final String VALID_TITLE = "Grade tests";
    private static final Date CURRENT_DATE = new Date();
    private static final Person VALID_PERSON_TO_MEET = new Person(new Name("John"), new Phone("12345678"),
            new Email("john@gmail.com"), new Address("Clementi"), new HashSet<Tag>());

    private Calendar validDeadline;
    private Calendar invalidDeadline;

    public TaskTest() {
        validDeadline = Calendar.getInstance();
        validDeadline.setTime(CURRENT_DATE);
        validDeadline.add(Calendar.HOUR_OF_DAY, 2);

        invalidDeadline = Calendar.getInstance();
        invalidDeadline.setTime(CURRENT_DATE);
        invalidDeadline.add(Calendar.HOUR_OF_DAY, -1);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () ->
                new Task(null, null));
        Assert.assertThrows(NullPointerException.class, () ->
                new Task(VALID_TITLE, null));
        Assert.assertThrows(NullPointerException.class, () ->
                new Task(null, validDeadline));
    }

    @Test
    public void constructor_invalidTestTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Task(invalidTitle, validDeadline));
    }

    @Test
    public void constructor_invalidTaskDeadline_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Task(VALID_TITLE, invalidDeadline));
    }

    @Test
    public void isValidTitle() {
        // null title
        Assert.assertThrows(NullPointerException.class, () -> Task.isValidTitle(null));

        // invalid Test
        assertFalse(Task.isValidTitle("")); // empty string
        assertFalse(Task.isValidTitle(" ")); // spaces only

        // valid Test
        assertTrue(Task.isValidTitle("Todo"));
        assertTrue(Task.isValidTitle("-")); // one character
    }
}
