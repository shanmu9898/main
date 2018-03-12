package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//@@author Sisyphus25
/**
 * Represent a Task in the schedule, contains deadline as well as the title
 */
public class Task implements Event {
    public static final String TITLE_VALIDATION_REGEX = "[^\\s].*";

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    private static final String MESSAGE_TITLE_CONSTRAINTS = "Title must be non empty";
    private static final String MESSAGE_TIME_CONSTRAINTS = "Invalid time stamp";

    private String title;
    private Date time;

    //Every field must be present and not null
    public Task(String title, Calendar deadline) {
        requireAllNonNull(title, deadline);
        checkArgument(isValidTitle(title), MESSAGE_TITLE_CONSTRAINTS);
        checkArgument(isValidTime(deadline), MESSAGE_TIME_CONSTRAINTS);
        this.title = title;
        this.time = deadline.getTime();
    }

    public String getTitle() {
        return title;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(this.getTitle())
                && otherTask.getTime().equals(this.getTime());
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(TITLE_VALIDATION_REGEX);
    }

    /**
     * Returns true if the given time is valid
     */
    public static boolean isValidTime(Calendar deadline) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        return deadline.after(currentTime);
    }
}
