package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import seedu.address.model.education.Class;
import seedu.address.model.education.Subject;
import seedu.address.model.tag.Tag;

//@@author randypx
/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated.
 * Contact details are immutable.
 *
 * @see Person
 */
public class Student extends Person {

    private List<Subject> subjectList;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        subjectList = new ArrayList<>();
    }

    /**
     * Construct a new student with a filled subject list
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Subject> subjects) {
        super(name, phone, email, address, tags);
        subjectList = new ArrayList<>(subjects);
    }

    /**
     * Adds a new {@code Class} into the student's {@code classList}.
     */
    public void enterClass(Class newClass) {
        subjectList.add(newClass.getSubject());
    }

    /**
     * Removes a {@code Class} from the student's {@code classList}
     */
    public void exitClass(Class toExit) {
        subjectList.remove(toExit.getSubject());
    }

    /**
     * Returns an immutable subject list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Subject> getSubjectList() {
        return Collections.unmodifiableList(subjectList);
    }

    /**
     * Checks if student is attending the given {@code class}.
     */
    public boolean isAttending(Class group) {
        return subjectList.contains(group.getSubject());
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Subjects: ");
        getSubjectList().forEach(builder::append);
        return builder.toString();
    }

    //@@author shanmu9898
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;
        return otherPerson.getName().equals(this.getName())
                && otherPerson.getPhone().equals(this.getPhone())
                && otherPerson.getEmail().equals(this.getEmail())
                && otherPerson.getAddress().equals(this.getAddress());
    }
}
