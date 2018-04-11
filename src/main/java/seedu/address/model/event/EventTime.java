package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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
    public static final String TIME_VALIDATION_REGEX = "((^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|"
            + "((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|"
            + "[2-9][0-9])\\d\\d)|(^29[\\/]02[\\/](19|[2-9][0-9])"
            + "(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)))"
            + "[ ]([0-1]?[0-9]|2[0-3]):[0-5][0-9]";

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
        checkArgument(isValidTimeStamp(timeStamp), MESSAGE_TIME_CONSTRAINTS);
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

    /**
     * Returns if a given string is a valid time stamp.
     */
    public boolean isValidTimeStamp(String time) {
        return time.matches(TIME_VALIDATION_REGEX);
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
}
