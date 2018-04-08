package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.education.Class;
import seedu.address.model.education.Subject;
import seedu.address.model.event.Time;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedClass {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Class' %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String subject;
    @XmlElement(required = true)
    private String startDate;
    @XmlElement(required = true)
    private String endDate;

    @XmlElement
    private List<String> students = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedClass.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedClass() {}

    /**
     * Constructs an {@code XmlAdaptedclass} with the given class details.
     */
    public XmlAdaptedClass(String name, String subject, String startDate, String endDate,
                           List<String> students) {
        this.name = name;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
        if (students != null) {
            this.students = new ArrayList<>(students);
        }
    }

    /**
     * Converts a given Class into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedClass.
     */
    public XmlAdaptedClass(Class source) {
        name = source.getName().fullName;
        subject = source.getSubject().value;
        startDate = source.getStartDate().toString();
        endDate = source.getEndDate().toString();
        students = new ArrayList<>();
        for (Name studentName : source.getStudents()) {
            students.add(studentName.fullName);
        }
    }

    /**
     * Converts this jaxb-friendly adapted class object into the model's Class object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted class
     */
    public Class toModelType() throws IllegalValueException {
        final List<Name> studentList = new ArrayList<>();
        for (String studentName : students) {
            studentList.add(new Name(studentName));
        }

        if (this.name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.name)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name name = new Name(this.name);

        if (this.subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName()));
        }
        if (!Subject.isValidSubject(this.subject)) {
            throw new IllegalValueException(Subject.MESSAGE_SUBJECT_CONSTRAINTS);
        }
        final Subject subject = new Subject(this.subject);

        if (this.startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        final Time startDate = new Time(this.startDate, true);

        if (this.endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        final Time endDate = new Time(this.endDate, true);

        if (!Time.isValidTime(startDate, endDate)) {
            throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }

        return new Class(name, subject, startDate, endDate, studentList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedClass)) {
            return false;
        }

        XmlAdaptedClass otherClass = (XmlAdaptedClass) other;
        return Objects.equals(name, otherClass.name)
                && Objects.equals(subject, otherClass.subject)
                && Objects.equals(startDate, otherClass.startDate)
                && Objects.equals(endDate, otherClass.endDate)
                && students.equals(otherClass.students);
    }
}
