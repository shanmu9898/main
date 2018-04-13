package seedu.address.model.education;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.event.Time;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;

//@@author randypx
/**
 * Represents a class, of a particular subject, taught by the user, for a specified duration.
 * Also contains a list of student attending the class.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Class {

    private final Name className;
    private final Subject subject;
    private final Time startDate;
    private final Time endDate;
    private ArrayList<Name> attendingStudents;

    /**
     * Every field must be present and not null.
     * Guarantees: current time <= start time < end time
     */
    public Class (Name name, Subject subject, Time start, Time end, List<Name> studentList) {
        this.className = name;
        this.subject = subject;
        this.startDate = start;
        this.endDate = end;
        attendingStudents = new ArrayList<>(studentList);
    }

    public boolean hasConcluded() {
        return endDate.isExpired();
    }

    public Name getName() {
        return className;
    }

    public Subject getSubject() {
        return subject;
    }

    public Time getStartDate() {
        return startDate;
    }

    public Time getEndDate() {
        return endDate;
    }

    public List<Name> getStudents() {
        return attendingStudents;
    }

    /**
     * Removes a student from the list of students attending the class.
     */
    public void removeStudent(Student student) {
        Name studentName = student.getName();
        attendingStudents.remove(studentName);
    }

    /**
     * Adds a student to the list of students attending the class.
     */
    public void addStudent(Student student) {
        Name studentName = student.getName();
        attendingStudents.add(studentName);
    }

    /**
     * Checks if given {@code student} is attending this class.
     */
    public boolean containStudent(Student student) {
        return attendingStudents.contains(student.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Class // instanceof handles nulls
                && this.className.equals(((Class) other).className)
                && this.subject.equals(((Class) other).subject)
                && this.startDate.equals(((Class) other).startDate)
                && this.endDate.equals(((Class) other).endDate)
                && this.attendingStudents.equals(((Class) other).attendingStudents));
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, startDate, endDate, attendingStudents);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Subject: ")
                .append(getSubject())
                .append(" Start: ")
                .append(getStartDate())
                .append(" Address: ")
                .append(getEndDate())
                .append(" Students: ");
        getStudents().forEach(builder::append);
        return builder.toString();
    }
}
