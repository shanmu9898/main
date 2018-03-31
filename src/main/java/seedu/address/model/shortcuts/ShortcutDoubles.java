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
        return other == this // short circuit if same object
                || (other instanceof ShortcutDoubles // instanceof handles nulls
                && this.shortcutWord.equals(((ShortcutDoubles) other).shortcutWord)
                && this.commandWord.equals(((ShortcutDoubles) other).commandWord)); // state check
    }
}
