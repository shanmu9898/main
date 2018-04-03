//@@author shanmu9898
package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * Few Typical Shortcuts
 */
public class TypicalShortcuts {
    public static final ShortcutDoubles SHORTCUT_DOUBLES_1 =
            new ShortcutCommandBuilder("l", "list").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_2 =
            new ShortcutCommandBuilder("c", "clear").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_3 =
            new ShortcutCommandBuilder("ll", "list").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_4 =
            new ShortcutCommandBuilder("cc", "clear").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_5 =
            new ShortcutCommandBuilder("a", "add").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_6 =
            new ShortcutCommandBuilder("aa", "add").build();

    public static List<ShortcutDoubles> getTypicalShortcuts() {
        return new ArrayList<>(Arrays.asList(SHORTCUT_DOUBLES_1, SHORTCUT_DOUBLES_2));
    }

}
