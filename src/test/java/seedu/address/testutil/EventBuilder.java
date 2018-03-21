package seedu.address.testutil;

import static seedu.address.model.event.PersonToMeet.EMAIL_SPLITTER;

import seedu.address.model.event.Appointment;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.PersonToMeet;
import seedu.address.model.event.Task;
import seedu.address.model.event.Title;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    private Title title;
    private EventTime time;
    private EventTime endTime;
    private PersonToMeet personToMeet;
    private String eventType;

    public EventBuilder(String title, String time, String endTime) {
        this(title, time, endTime, (String) null);
    }

    public EventBuilder(String title, String time, String endTime, Person personToMeet) {
        this(title, time, endTime, personToMeet.getName() + EMAIL_SPLITTER + personToMeet.getEmail());
    }

    public EventBuilder(String title, String time, String endTime, String personToMeet) {
        this.title = new Title(title);
        this.time = new EventTime(time);
        this.endTime = new EventTime(endTime);
        if (personToMeet != null) {
            String[] components = personToMeet.split(EMAIL_SPLITTER);
            this.personToMeet = new PersonToMeet(components[0], components[1]);
        }
        this.eventType = "Appointment";
    }

    public EventBuilder(String title, String time) {
        this.title = new Title(title);
        this.time = new EventTime(time);
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
