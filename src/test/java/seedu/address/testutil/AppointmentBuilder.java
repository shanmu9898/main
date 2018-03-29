package seedu.address.testutil;

import static seedu.address.model.event.PersonToMeet.EMAIL_SPLITTER;

import seedu.address.model.event.Appointment;

import seedu.address.model.event.EventTime;
import seedu.address.model.event.PersonToMeet;
import seedu.address.model.event.Title;
import seedu.address.model.person.Person;

//@@author Sisyphus25
/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    private Title title;
    private EventTime time;
    private EventTime endTime;
    private PersonToMeet personToMeet;

    public AppointmentBuilder(String title, String time, String endTime) {
        this(title, time, endTime, (String) null);
    }

    public AppointmentBuilder(String title, String time, String endTime, Person personToMeet) {
        this(title, time, endTime, personToMeet.getName() + EMAIL_SPLITTER + personToMeet.getEmail());
    }

    public AppointmentBuilder(String title, String time, String endTime, String personToMeet) {
        this.title = new Title(title);
        this.time = new EventTime(time);
        this.endTime = new EventTime(endTime);
        if (personToMeet != null) {
            String[] components = personToMeet.split(EMAIL_SPLITTER);
            this.personToMeet = new PersonToMeet(components[0], components[1]);
        }
    }

    /**
     * @return an {@code Appointment} from the data feed to constructor
     */
    public Appointment build() {
        return new Appointment(title, time, endTime, personToMeet);
    }
}
