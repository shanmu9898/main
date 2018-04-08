package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

//@@author Sisyphus25
/**
 * Represent an appointment in the schedule, contains time of the appointment as well as details and personMeet.
 */
public class Appointment {
    public static final String MESSAGE_TIME_PERIOD_CONSTRAINTS = "The end time should be after the start time";

    private final Title title;
    private final Time startTime;
    private final Time endTime;
    private final PersonToMeet personToMeet;

    //Every field must be present and not null
    public Appointment(Title title, Time startTime, Time endTime) {
        this(title, startTime, endTime, null);
    }

    //Every field except personToMeet must be present and not null
    public Appointment(Title title, Time startTime, Time endTime, PersonToMeet personToMeet) {
        requireAllNonNull(title, startTime, endTime);
        checkArgument(isValidTime(startTime, endTime), MESSAGE_TIME_PERIOD_CONSTRAINTS);
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.personToMeet = personToMeet;
    }

    public Title getTitle() {
        return title;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public PersonToMeet getPersonToMeet() {
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
                && otherAppointment.getStartTime().equals(this.getStartTime())
                && otherAppointment.getEndTime().equals(this.getEndTime());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(", Start Time: ")
                .append(getStartTime().toString())
                .append(", End Time: ")
                .append(getEndTime().toString());
        if (personToMeet != null) {
            builder.append(", With: ")
                    .append(personToMeet.getName());
        }
        return builder.toString();
    }

    /**
     * Returns true if the given time is valid
     */
    public static boolean isValidTime(Time startTime, Time endTime) {
        return endTime.value.after(startTime.value);
    }
}
