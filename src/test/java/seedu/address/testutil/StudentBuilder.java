package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.education.Subject;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

//@@author randypx
/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    private static final String DEFAULT_NAME = "Alice Pauline";
    private static final String DEFAULT_PHONE = "85355255";
    private static final String DEFAULT_EMAIL = "alice@gmail.com";
    private static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    private static final String DEFAULT_TAGS = "friends";
    private static final String DEFAULT_SUBJECT = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<Subject> subjects;

    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = SampleDataUtil.getTagSet(DEFAULT_TAGS);
        subjects = SampleDataUtil.getSubjectList(DEFAULT_SUBJECT);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        subjects = new ArrayList<>(personToCopy.getSubjectList());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code subjects} into a {@code List<Subject>} and set it to the {@code Student}
     * that we are building.
     */
    public StudentBuilder withSubjects(String ... subjects) {
        this.subjects = SampleDataUtil.getSubjectList(subjects);
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, address, tags, subjects);
    }
}
