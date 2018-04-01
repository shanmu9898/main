package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents an event's time stamp in the address book.
 * Guarantees: immutable
 */
public class EventTime {
    public static final String MESSAGE_TIME_CONSTRAINTS = "Date and time must be in the format: dd/MM/yyyy HH:mm";

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public final Calendar value;

    /**
     * Constructs a {@code EventTime}.
     *
     * @param timeStamp valid timeStamp.
     */
    public EventTime(String timeStamp) {
        requireNonNull(timeStamp);
        value = Calendar.getInstance();
        try {
            this.value.setTime(DATE_FORMATTER.parse(timeStamp));
        } catch (ParseException e) {
            throw new IllegalArgumentException(MESSAGE_TIME_CONSTRAINTS);
        }
    }

    /**
     * Returns true if the given time has already passed the current time
     */
    public boolean isExpired() {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        return value.before(currentTime);
    }

    @Override
    public String toString() {
        return DATE_FORMATTER.format(value.getTime());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventTime // instanceof handles nulls
                && this.value.equals(((EventTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
