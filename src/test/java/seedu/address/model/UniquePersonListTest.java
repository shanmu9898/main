package seedu.address.model;

import static junit.framework.TestCase.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class UniquePersonListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniquePersonList uniquePersonList = new UniquePersonList();
        thrown.expect(UnsupportedOperationException.class);
        uniquePersonList.asObservableList().remove(0);
    }

    @Test
    public void sortedObservableList_modifyList_showsSortedList(){
        UniquePersonList uniquePersonList = new UniquePersonList();
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        try {
            uniquePersonList.add(bob);
            uniquePersonList.add(alice);
        } catch (DuplicatePersonException dpe) {
            assert false : "Should not happen";
        }
        uniquePersonList.sort();
        List testingList = uniquePersonList.asObservableList();
        assertTrue(testingList.get(0).equals(alice));
    }
}
