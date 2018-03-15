package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import seedu.address.model.person.Person;

//@@author Sisyphus25
/**
 * Represent an appointment in the schedule, contains time of the appointment as well as details and personMeet.
 */
public class Appointment implements Event {
    /*
     * The title can not be empty string or spaces only
     */
    public static final String TITLE_VALIDATION_REGEX = "[^\\s].*";

    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    private static final String MESSAGE_TITLE_CONSTRAINTS = "Title must be non empty";
    private static final String MESSAGE_TIME_CONSTRAINTS = "Invalid time stamp";

    private String title;
    private Date time;
    private Date endTime;
    private Person personToMeet;

    //Every field must be present and not null
    public Appointment(String title, Calendar startTime, Calendar endTime) {
        this(title, startTime, endTime, null);
    }

    //Every field except personToMeet must be present and not null
    public Appointment(String title, Calendar startTime, Calendar endTime, Person personToMeet) {
        requireAllNonNull(title, startTime, endTime);
        checkArgument(isValidTitle(title), MESSAGE_TITLE_CONSTRAINTS);
        checkArgument(isValidTime(startTime, endTime), MESSAGE_TIME_CONSTRAINTS);
        this.title = title;
        this.time = startTime.getTime();
        this.endTime = endTime.getTime();
        this.personToMeet = personToMeet;
    }

    public String getTitle() {
        return title;
    }

    public Date getTime() {
        return time;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Person getPersonToMeet() {
        return personToMeet;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getTitle().equals(this.getTitle())
                && otherAppointment.getTime().equals(this.getTime())
                && otherAppointment.getEndTime().equals(this.getEndTime())
                && otherAppointment.getPersonToMeet().equals(this.getPersonToMeet());
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
    public static boolean isValidTime(Calendar startTime, Calendar endTime) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        return endTime.after(startTime) && startTime.after(currentTime);
    }
}
