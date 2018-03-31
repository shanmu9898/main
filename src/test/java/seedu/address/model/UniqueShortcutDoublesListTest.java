package seedu.address.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

public class UniqueShortcutDoublesListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueShortcutDoublesList uniqueShortcutDoublesList = new UniqueShortcutDoublesList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueShortcutDoublesList.asObservableList().remove(0);
    }
}
