package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_TAG_NAME_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String MESSAGE_TAG_COLOR_STYLE_CONSTRAINTS = "Tag color style is invalid or not supported";
    private static final String TAG_VALIDATION_REGEX = "\\p{Alnum}+";
    //@@author Sisyphus25-reused
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    private static final String[] TAG_COLOR_STYLES = {"teal", "red", "yellow", "blue", "orange", "brown",
        "green", "pink", "black", "grey"};
    //@@author
    public final String tagColorStyle;
    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        this(tagName, "default");
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     * @param tagColorStyle A valid tag color style
     */
    public Tag(String tagName, String tagColorStyle) {
        requireNonNull(tagName);
        requireNonNull(tagColorStyle);

        if (tagColorStyle.equals("default")) {
            tagColorStyle = getTagColorStyle(tagName);
        }

        checkArgument(isValidTagName(tagName), MESSAGE_TAG_NAME_CONSTRAINTS);
        checkArgument(isValidTagColorStyle(tagColorStyle), MESSAGE_TAG_COLOR_STYLE_CONSTRAINTS);
        this.tagName = tagName;
        this.tagColorStyle = tagColorStyle;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(TAG_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid tag color style.
     */
    public static boolean isValidTagColorStyle(String tagColorStyle) {
        return Arrays.asList(TAG_COLOR_STYLES).contains(tagColorStyle);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && this.tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

    //@@author Sisyphus25-reused
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    /**
     * Returns a color style for {@code tagName}
     */
    private String getTagColorStyle(String tagName) {
        // we use the hash code of the tag name to generate a random color, so that the color remain consistent
        // between different runs of the program while still making it random enough between tags.
        return TAG_COLOR_STYLES[Math.abs(tagName.hashCode()) % TAG_COLOR_STYLES.length];
    }


}
