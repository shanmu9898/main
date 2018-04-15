package seedu.address.model.Education;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.education.Subject;
import seedu.address.testutil.Assert;

public class SubjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void constructor_invalidSubject_throwsIllegalArgumentException() {
        String invalidSubject = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Subject(invalidSubject));
    }

    @Test
    public void isValidSubject() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Subject.isValidSubject(null));

        // invalid name
        assertFalse(Subject.isValidSubject("")); // empty string
        assertFalse(Subject.isValidSubject(" ")); // spaces only
        assertFalse(Subject.isValidSubject("^")); // only non-alphanumeric characters
        assertFalse(Subject.isValidSubject("math*")); // contains non-alphanumeric characters
        assertFalse(Subject.isValidSubject("advanced math")); // multiple words

        // valid name
        assertTrue(Subject.isValidSubject("math")); // alphabets only
        assertTrue(Subject.isValidSubject("12345")); // numbers only
        assertTrue(Subject.isValidSubject("math101")); // alphanumeric characters
        assertTrue(Subject.isValidSubject("Biology")); // with capital letters
    }
}
