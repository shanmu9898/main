package seedu.address.testutil;

import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * A utility class to help with building Shortcut objects.
 */
public class ShortcutCommandBuilder {

    private String shortcutWord;
    private String commandWord;

    public ShortcutCommandBuilder(String shortcutWord, String commandWord) {
        this.commandWord = commandWord;
        this.shortcutWord = shortcutWord;
    }


    /**
     * @return an {@code Appointment} from the data feed to constructor
     */
    public ShortcutDoubles build() {
        return new ShortcutDoubles(shortcutWord, commandWord);
    }
}
