package seedu.address.storage;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Title;
import seedu.address.model.person.Person;

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
    @XmlElement
    private XmlAdaptedPerson personToMeet = new XmlAdaptedPerson();

    /**
     * Constructs an XmlAdaptedAppointment.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedAppointment() {}

    /**
     * Constructs an {@code XmlAdaptedAppointment} with the given appointment details.
     */
    public XmlAdaptedAppointment(String title, String startTime, String endTime, XmlAdaptedPerson personToMeet) {
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
            personToMeet = new XmlAdaptedPerson(source.getPersonToMeet());
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Appointment object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
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

        Person person = null;
        if (this.personToMeet != null) {
            person = this.personToMeet.toModelType();
        }

        return new Appointment(title, startTime, endTime, person);
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
