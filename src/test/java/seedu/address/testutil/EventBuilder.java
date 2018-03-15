package seedu.address.testutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import seedu.address.model.event.Appointment;
import seedu.address.model.event.Event;
import seedu.address.model.event.Task;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    private static final Date CURRENT_TIME = new Date();

    private String title;
    private Calendar time;
    private Calendar endTime;
    private Person personToMeet;
    private String eventType;

    public EventBuilder(String title, String time, String endTime, Person personToMeet) {
        this.title = title;
        this.time = Calendar.getInstance();
        this.endTime = Calendar.getInstance();
        try {
            this.time.setTime(DATE_FORMATTER.parse(time));
            this.endTime.setTime(DATE_FORMATTER.parse(endTime));
        } catch (ParseException e) {
            this.time.setTime(CURRENT_TIME);
            this.time.add(Calendar.HOUR_OF_DAY, 1);
            this.time.setTime(CURRENT_TIME);
            this.time.add(Calendar.HOUR_OF_DAY, 2);
        }
        this.personToMeet = personToMeet;
        this.eventType = "Appointment";
    }

    public EventBuilder(String title, String time) {
        this.title = title;
        this.time = Calendar.getInstance();
        try {
            this.time.setTime(DATE_FORMATTER.parse(time));
        } catch (ParseException e) {
            this.time.setTime(CURRENT_TIME);
            this.time.add(Calendar.HOUR_OF_DAY, 1);
        }
        this.endTime = null;
        this.personToMeet = null;
        this.eventType = "Task";
    }

    /**
     * @return an {@code Event} from the data feed to constructor
     */
    public Event build() {
        if (eventType.equals("Appointment")) {
            return new Appointment(title, time, endTime, personToMeet);
        } else if (eventType.equals("Task")) {
            return new Task(title, time);
        } else {
            return null;
        }
    }
}
