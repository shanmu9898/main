package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import seedu.address.testutil.Assert;

//@@author Sisyphus25
public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("invalidTimeStamp"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time(""));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("10/20 10:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("May 17 2018 10:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("17-05-2019 10:00"));
    }

    @Test
    public void isExpired() {
        Time pastTime = new Time("20/10/2013 10:00");
        Time futureTime = new Time("20/10/2100 10:00");
        assertFalse(futureTime.isExpired());

        assertTrue(pastTime.isExpired());
    }
}

