package seedu.address.model.education;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
