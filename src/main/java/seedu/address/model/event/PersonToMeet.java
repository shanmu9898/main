package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Email.MESSAGE_EMAIL_CONSTRAINTS;
import static seedu.address.model.person.Name.MESSAGE_NAME_CONSTRAINTS;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents an Appointment's personToMeet in the address book.
 * Guarantees: immutable;
 */
public class PersonToMeet {

    public static final String EMAIL_SPLITTER = " Email: ";

    private final String name;
    private final String email;

    public PersonToMeet(Person person) {
        requireNonNull(person);
        this.name = person.getName().fullName;
        this.email = person.getEmail().value;
    }

    public PersonToMeet(String name, String email) {
        requireNonNull(name, email);
        checkArgument(Name.isValidName(name), MESSAGE_NAME_CONSTRAINTS);
        checkArgument(Email.isValidEmail(email), MESSAGE_EMAIL_CONSTRAINTS);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + EMAIL_SPLITTER + email;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonToMeet // instanceof handles nulls
                && this.name.equals(((PersonToMeet) other).name))
                && this.name.equals(((PersonToMeet) other).email); // state check
    }
}
