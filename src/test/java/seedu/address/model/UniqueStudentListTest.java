package seedu.address.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.person.UniqueStudentList;

public class UniqueStudentListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueStudentList uniqueStudentList = new UniqueStudentList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueStudentList.asObservableList().remove(0);
    }
}
