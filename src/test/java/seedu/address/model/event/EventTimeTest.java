package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import seedu.address.testutil.Assert;

//@@author Sisyphus25
public class EventTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new EventTime(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        //incorrect format
        //not a time stamp
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("invalidTimeStamp"));
        //blank
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime(""));
        //invalid time stamp format
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("10/20 10:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("May 17 2018 10:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("17-05-2019 10:00"));

        //correct format but invalid time stamp
        //invalid date
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("32/05/2019 10:00"));
        //invalid month
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("32/13/2019 10:00"));
        //invalid date month
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("29/02/2018 10:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("31/04/2018 10:00"));
        //invalid time
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("29/02/2018 25:00"));
        Assert.assertThrows(IllegalArgumentException.class, () -> new EventTime("29/02/2018 23:60"));
    }

    @Test
    public void isExpired() {
        EventTime pastTime = new EventTime("20/10/2013 10:00");
        EventTime futureTime = new EventTime("20/10/2100 10:00");
        assertFalse(futureTime.isExpired());

        assertTrue(pastTime.isExpired());
    }
}

