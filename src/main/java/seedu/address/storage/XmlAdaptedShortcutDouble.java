//@@author shanmu9898
package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 *Class to create a shortcut double and save it as an XML element.
 */
public class XmlAdaptedShortcutDouble {
    @XmlElement
    private String shortcutWord;
    @XmlElement
    private String commandWord;

    public XmlAdaptedShortcutDouble() {}

    public XmlAdaptedShortcutDouble(String shortcutWord, String commandWord) {
        this.shortcutWord = shortcutWord;
        this.commandWord = commandWord;
    }

    public XmlAdaptedShortcutDouble(ShortcutDoubles source) {
        shortcutWord = source.shortcutWord;
        commandWord = source.commandWord;
    }

    public ShortcutDoubles toModelType() throws IllegalValueException {
        return new ShortcutDoubles(shortcutWord, commandWord);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedShortcutDouble)) {
            return false;
        }

        return commandWord.equals(((XmlAdaptedShortcutDouble) other).commandWord)
                && shortcutWord.equals(((XmlAdaptedShortcutDouble) other).shortcutWord);
    }

}
