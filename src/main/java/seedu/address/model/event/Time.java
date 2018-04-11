package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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
    public static final String TIME_VALIDATION_REGEX = "((^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|"
            + "((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|"
            + "[2-9][0-9])\\d\\d)|(^29[\\/]02[\\/](19|[2-9][0-9])"
            + "(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)))"
            + "[ ]([0-1]?[0-9]|2[0-3]):[0-5][0-9]";

    public static final String DATE_VALIDATION_REGEX = "((^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|"
            + "((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|"
            + "[2-9][0-9])\\d\\d)|(^29[\\/]02[\\/](19|[2-9][0-9])"
            + "(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)))";

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
            checkArgument(isValidTimeStamp(timeStamp), MESSAGE_DATE_TIME_CONSTRAINTS);
            try {
                this.value.setTime(DATE_FORMATTER.parse(timeStamp));
            } catch (ParseException e) {
                throw new IllegalArgumentException(MESSAGE_DATE_TIME_CONSTRAINTS);
            }
        } else {
            checkArgument(isValidTimeStamp(timeStamp), MESSAGE_DATE_CONSTRAINTS);
            try {
                this.value.setTime(DATE_ONLY_FORMATTER.parse(timeStamp));
            } catch (ParseException e) {
                throw new IllegalArgumentException(MESSAGE_DATE_CONSTRAINTS);
            }
        }
    }

    /**
     * Returns if a given string is a valid time stamp.
     */
    public boolean isValidTimeStamp(String time) {
        if (!isOnlyDate) {
            return time.matches(TIME_VALIDATION_REGEX);
        } else {
            return time.matches(DATE_VALIDATION_REGEX);
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
