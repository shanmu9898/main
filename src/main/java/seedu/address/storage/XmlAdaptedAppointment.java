package seedu.address.storage;

import static seedu.address.model.event.PersonToMeet.EMAIL_SPLITTER;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.PersonToMeet;
import seedu.address.model.event.Title;

//@@author Sisyphus25
/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    @XmlElement(required = true)
    private String title;
    @XmlElement(required = true)
    private String startTime;
    @XmlElement(required = true)
    private String endTime;
    @XmlElement(required = true)
    private String personToMeet;

    /**
     * Constructs an XmlAdaptedAppointment.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedAppointment() {}

    public XmlAdaptedAppointment(String title, String startTime, String endTime) {
        this(title, startTime, endTime, null);
    }

    /**
     * Constructs an {@code XmlAdaptedAppointment} with the given appointment details.
     */
    public XmlAdaptedAppointment(String title, String startTime, String endTime, String personToMeet) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        if (personToMeet != null) {
            this.personToMeet = personToMeet;
        }
    }

    /**
     * Converts a given Appointment into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedAppointment
     */
    public XmlAdaptedAppointment(Appointment source) {
        title = source.getTitle().toString();
        startTime = source.getTime().toString();
        endTime = source.getEndTime().toString();
        if (source.getPersonToMeet() != null) {
            personToMeet = source.getPersonToMeet().toString();
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Appointment object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment
     */
    public Appointment toModelType() throws IllegalValueException {
        if (this.title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(this.title)) {
            throw new IllegalValueException(Title.MESSAGE_TITLE_CONSTRAINTS);
        }
        final Title title = new Title(this.title);

        if (this.startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Start Time"));
        }
        if (this.endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "End Time"));
        }

        final EventTime startTime = new EventTime(this.startTime);
        final EventTime endTime = new EventTime(this.endTime);

        if (!Appointment.isValidTime(startTime, endTime)) {
            throw new IllegalValueException(Appointment.MESSAGE_TIME_PERIOD_CONSTRAINTS);
        }

        if (this.personToMeet != null) {
            String[] components = this.personToMeet.split(EMAIL_SPLITTER);
            PersonToMeet personToMeet = new PersonToMeet(components[0], components[1]);
            return new Appointment(title, startTime, endTime, personToMeet);
        }

        return new Appointment(title, startTime, endTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedAppointment)) {
            return false;
        }

        XmlAdaptedAppointment otherAppointment = (XmlAdaptedAppointment) other;
        return Objects.equals(title, otherAppointment.title)
                && Objects.equals(startTime, otherAppointment.startTime)
                && Objects.equals(endTime, otherAppointment.endTime)
                && Objects.equals(personToMeet, otherAppointment.personToMeet);
    }
}
