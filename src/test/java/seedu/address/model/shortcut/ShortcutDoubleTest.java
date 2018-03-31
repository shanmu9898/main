package seedu.address.model.shortcut;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.shortcuts.ShortcutDoubles;

public class ShortcutDoubleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullCommandWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutDoubles("l", null);
    }

    @Test
    public void constructor_nullShortcutWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutDoubles(null , "list");
    }

    @Test
    public void equals() throws Exception {
        ShortcutDoubles deleteFirstCommand = new ShortcutDoubles("l", "list");
        ShortcutDoubles deleteSecondCommand = new ShortcutDoubles("c", "clear");

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ShortcutDoubles deleteFirstCommandCopy = new ShortcutDoubles("l", "list");
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
