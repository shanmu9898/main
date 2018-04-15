package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.education.Class;
import seedu.address.model.education.Subject;
import seedu.address.model.event.Time;
import seedu.address.model.person.Name;
import seedu.address.model.util.SampleDataUtil;

//@@author randypx
/**
 * A utility class to help with building class objects.
 */
public class ClassBuilder {

    private static final String DEFAULT_NAME = "Class 01";
    private static final String DEFAULT_SUBJECT = "Math";
    private static final String DEFAULT_START_DATE = "10/10/2018";
    private static final String DEFAULT_END_DATE = "20/10/2019";
    private static final String DEFAULT_STUDENT = "Alice Pauline";

    private Name name;
    private Subject subject;
    private Time start;
    private Time end;
    private List<Name> studentNameList;

    public ClassBuilder() {
        name = new Name(DEFAULT_NAME);
        subject = new Subject(DEFAULT_SUBJECT);
        start = new Time(DEFAULT_START_DATE, true);
        end = new Time(DEFAULT_END_DATE, true);
        studentNameList = SampleDataUtil.getStudentList(DEFAULT_STUDENT);
    }

    /**
     * Initializes the ClassBuilder with the data of {@code classToCopy}.
     */
    public ClassBuilder(Class classToCopy) {
        name = classToCopy.getName();
        subject = classToCopy.getSubject();
        start = classToCopy.getStartDate();
        end = classToCopy.getEndDate();
        studentNameList = new ArrayList<>(classToCopy.getStudents());
    }

    /**
     * Sets the {@code Name} of the {@code Class} that we are building.
     */
    public ClassBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Class} that we are building.
     */
    public ClassBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Class} that we are building.
     */
    public ClassBuilder withEndDate(String endDate) {
        this.end = new Time(endDate, true);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Class} that we are building.
     */

    public ClassBuilder withStartDate(String startDate) {
        this.start = new Time(startDate, true);
        return this;
    }

    /**
     * Parses the {@code studentNames} into a {@code List<Name>} and set it to the {@code Class}
     * that we are building.
     */
    public ClassBuilder withStudents(String ... studentNames) {
        this.studentNameList = SampleDataUtil.getStudentList(studentNames);
        return this;
    }

    public Class build() {
        return new Class(name, subject, start, end, studentNameList);
    }
}
