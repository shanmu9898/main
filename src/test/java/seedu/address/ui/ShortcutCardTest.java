//package seedu.address.ui;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static seedu.address.testutil.TypicalShortcuts.SHORTCUT_DOUBLES_3;
//import static seedu.address.testutil.TypicalShortcuts.SHORTCUT_DOUBLES_5;
//
//import org.junit.Test;
//
//import seedu.address.model.shortcuts.ShortcutDoubles;
//
//public class ShortcutCardTest {
//
//
//    @Test
//    public void equals() {
//        ShortcutDoubles shortcutDoubles = SHORTCUT_DOUBLES_5;
//        //ShortcutCard shortcutCard = new ShortcutCard(shortcutDoubles, 0);
//
//        // same shortcut, same index -> returns true
//        ShortcutCard copy = new ShortcutCard(shortcutDoubles, 0);
//        assertTrue(shortcutCard.equals(copy));
//
//        // same object -> returns true
//        assertTrue(shortcutCard.equals(shortcutCard));
//
//        // null -> returns false
//        assertFalse(shortcutCard.equals(null));
//
//        // different types -> returns false
//        assertFalse(shortcutCard.equals(0));
//
//        // different shortcut, same index -> returns false
//        ShortcutDoubles differentshortcut = SHORTCUT_DOUBLES_3;
//        assertFalse(shortcutCard.equals(new ShortcutCard(differentshortcut, 0)));
//
//        // same shortcut, different index -> returns false
//        assertFalse(shortcutCard.equals(new ShortcutCard(shortcutDoubles, 1)));
//    }
//}
