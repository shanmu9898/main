package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

/**
 * JAXB-friendly adapted version of the Tag.
 */
public class XmlAdaptedTag {

    @XmlElement(required = true)
    private String tagName;
    @XmlElement(required = true)
    private String tagColorStyle;

    /**
     * Constructs an XmlAdaptedTag.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedTag() {}

    /**
     * Constructs a {@code XmlAdaptedTag} with the given {@code tagName and tagColorStyle}.
     */
    public XmlAdaptedTag(String tagName, String tagColorStyle) {
        this.tagName = tagName;
        this.tagColorStyle = tagColorStyle;
    }

    /**
     * Converts a given Tag into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedTag(Tag source) {
        tagName = source.tagName;
        tagColorStyle = source.tagColorStyle;
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Tag object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_TAG_NAME_CONSTRAINTS);
        }
        if (!Tag.isValidTagColorStyle(tagColorStyle)) {
            throw new IllegalValueException(Tag.MESSAGE_TAG_COLOR_STYLE_CONSTRAINTS);
        }
        return new Tag(tagName, tagColorStyle);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedTag)) {
            return false;
        }

        return tagName.equals(((XmlAdaptedTag) other).tagName)
                && tagColorStyle.equals(((XmlAdaptedTag) other).tagColorStyle);
    }
}
