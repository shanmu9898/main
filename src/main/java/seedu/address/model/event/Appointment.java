package seedu.address.model.event;

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
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

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
}
