package seedu.address.model.shortcuts;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Command Double
 */
public class ShortcutDoubles {

    public final String shortcutWord;
    public final String commandWord;

    public ShortcutDoubles(String shortcutWord, String commandWord) {
        requireNonNull(shortcutWord);
        requireNonNull(commandWord);
        this.shortcutWord = shortcutWord;
        this.commandWord = commandWord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ShortcutDoubles)) {
            return false;
        }

        ShortcutDoubles otherShortcut = (ShortcutDoubles) other;
        return otherShortcut.commandWord.equals(this.commandWord)
                && otherShortcut.shortcutWord.equals(this.shortcutWord);
    }
}
