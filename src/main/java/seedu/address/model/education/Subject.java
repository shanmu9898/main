package seedu.address.model.education;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author randypx
/**
 * Represents the subject being taught in a class.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {

    public static final String MESSAGE_SUBJECT_CONSTRAINTS =
            "Subject should only contain alphanumeric characters and should not be blank";

    /*
     * The first character of the subject must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String SUBJECT_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}]*";

    public final String value;

    /**
     * Constructs an {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_SUBJECT_CONSTRAINTS);
        this.value = subject;
    }

    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(SUBJECT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && this.value.equals(((Subject) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
