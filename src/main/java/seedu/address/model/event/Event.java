package seedu.address.model.event;

import java.util.Date;

/**
 * Represents an Event in the schedule.
 */
public interface Event {
    public String getTitle();
    public Date getTime();
}
