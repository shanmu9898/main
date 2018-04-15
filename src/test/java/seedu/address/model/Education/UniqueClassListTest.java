package seedu.address.model.Education;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.education.UniqueClassList;

public class UniqueClassListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueClassList uniqueClassList = new UniqueClassList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueClassList.asObservableList().remove(0);
    }
}
