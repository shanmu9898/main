package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//@@author Sisyphus25
/**
 * Represents an event's time stamp in the address book.
 * Guarantees: immutable
 */
public class Time {
    public static final String MESSAGE_DATE_TIME_CONSTRAINTS = "Date and time must be in the format: DD/MM/YYYY HH:MM";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date must be in the format: DD/MM/YYYY";

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private static final String DATE_ONLY_FORMAT = "dd/MM/yyyy";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    private static final DateFormat DATE_ONLY_FORMATTER = new SimpleDateFormat(DATE_ONLY_FORMAT);

    public final Calendar value;
    private final boolean isOnlyDate;

    /**
     * Constructs a {@code Time} with the given timestamp.
     *
     * @param timeStamp date and/or time argument given by the user.
     */
    public Time(String timeStamp, boolean b) {
        requireNonNull(timeStamp);
        value = Calendar.getInstance();
        isOnlyDate = b;
        if (!isOnlyDate) {
            try {
                this.value.setTime(DATE_FORMATTER.parse(timeStamp));
            } catch (ParseException e) {
                throw new IllegalArgumentException(MESSAGE_DATE_TIME_CONSTRAINTS);
            }
        } else {
            try {
                this.value.setTime(DATE_ONLY_FORMATTER.parse(timeStamp));
            } catch (ParseException e) {
                throw new IllegalArgumentException(MESSAGE_DATE_CONSTRAINTS);
            }
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
     * Returns true if the given times is valid
     */
    public static boolean isValidTime(Time startTime, Time endTime) {
        return endTime.value.after(startTime.value);
    }

    @Override
    public String toString() {
        if (isOnlyDate) {
            return DATE_ONLY_FORMATTER.format(value.getTime());
        } else {
            return DATE_FORMATTER.format(value.getTime());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && this.value.equals(((Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
