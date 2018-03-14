package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Person;

//@@author Sisyphus25
/**
 * Represent an appointment in the schedule, contains time of the appointment as well as details and personMeet.
 */
public class Appointment implements Event {
    public static final String MESSAGE_TIME_PERIOD_CONSTRAINTS = "The end time should be after the start time";

    private Title title;
    private EventTime time;
    private EventTime endTime;
    private Person personToMeet;

    //Every field must be present and not null
    public Appointment(Title title, EventTime startTime, EventTime endTime) {
        this(title, startTime, endTime, null);
    }

    //Every field except personToMeet must be present and not null
    public Appointment(Title title, EventTime startTime, EventTime endTime, Person personToMeet) {
        requireAllNonNull(title, startTime, endTime);
        checkArgument(isValidTime(startTime, endTime), MESSAGE_TIME_PERIOD_CONSTRAINTS);
        this.title = title;
        this.time = startTime;
        this.endTime = endTime;
        this.personToMeet = personToMeet;
    }

    public Title getTitle() {
        return title;
    }

    public EventTime getTime() {
        return time;
    }

    public EventTime getEndTime() {
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
     * Returns true if the given time is valid
     */
    public static boolean isValidTime(EventTime startTime, EventTime endTime) {
        return endTime.value.after(startTime.value);
    }
}
