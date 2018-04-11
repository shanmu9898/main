package seedu.address.model.event;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import seedu.address.testutil.Assert;

//@@author Sisyphus25
public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Time(null, false));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("invalidTimeStamp", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("10/20 10:00", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("May 17 2018 10:00", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("17-05-2019 10:00", false));

        //incorrect format
        //not a time stamp
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("invalidTimeStamp", false));
        //blank
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("", false));
        //invalid time stamp format
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("10/20 10:00", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("May 17 2018 10:00", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("17-05-2019 10:00", false));

        //correct format but invalid time stamp
        //invalid date
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("32/05/2019 10:00", false));
        //invalid month
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("32/13/2019 10:00", false));
        //invalid date month
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("29/02/2018 10:00", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("31/04/2018 10:00", false));
        //invalid time
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("29/02/2018 25:00", false));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time("29/02/2018 23:60", false));
    }

    @Test
    public void isExpired() {
        Time pastTime = new Time("20/10/2013 10:00", false);
        Time futureTime = new Time("20/10/2100 10:00", false);
        assertFalse(futureTime.isExpired());

        assertTrue(pastTime.isExpired());
    }
}

