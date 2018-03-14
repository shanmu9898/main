package seedu.address.testutil;

import seedu.address.model.event.Appointment;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventTime;
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
    private Person personToMeet;
    private String eventType;

    public EventBuilder(String title, String time, String endTime, Person personToMeet) {
        this.title = new Title(title);
        this.time = new EventTime(time);
        this.endTime = new EventTime(endTime);
        this.personToMeet = personToMeet;
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
