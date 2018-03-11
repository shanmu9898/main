package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.util.SampleDataUtil;

public class StudentBuilder extends PersonBuilder {

    public StudentBuilder() {
        super();
    }

    public StudentBuilder(Student personToCopy) {
        super(personToCopy);
    }

    @Override
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    @Override
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    @Override
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    @Override
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    @Override
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    @Override
    public Student build() {
        return new Student(name, phone, email, address, tags);
    }
}
