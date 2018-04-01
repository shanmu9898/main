package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class EventTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new EventTime(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("invalidTimeStamp"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime(""));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("10/20 10:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("May 17 2018 10:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("17-05-2019 10:00"));
    }

    @Test
    public void isExpired() {
        EventTime pastTime = new EventTime("20/10/2013 10:00");
        EventTime futureTime = new EventTime("20/10/2100 10:00");
        assertFalse(futureTime.isExpired());

        assertTrue(pastTime.isExpired());
    }
}

