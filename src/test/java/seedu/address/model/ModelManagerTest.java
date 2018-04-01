package seedu.address.model;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NOTUSED;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_3;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_TASK_3;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.model.AppointmentListChangedEvent;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.ui.testutil.EventsCollectorRule;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private AddressBook addressBook = getTypicalAddressBook();
    private UserPrefs userPrefs = new UserPrefs();
    private ModelManager modelManager = new ModelManager(addressBook, userPrefs);

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredPersonList().remove(0);
    }

    @Test
    public void getFilteredCommandList_modifyList_throwsUnsupportedOperationException() {
        ModelManager modelManager = new ModelManager();
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredCommandsList().remove(0);
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredAppointmentList().remove(0);
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredTaskList().remove(0);
    }

    @Test
    public void addPerson_addPersonToAddressBook_evokeAddressBookChangedEvent() throws DuplicatePersonException {
        ModelManager model = new ModelManager(addressBook, userPrefs);
        modelManager.addPerson(IDA);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }

    @Test
    public void removePerson_removePersonFromAddressBook_evokeAddressBookChangedEvent() throws PersonNotFoundException {
        ModelManager model = new ModelManager(addressBook, userPrefs);
        modelManager.deletePerson(ALICE);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }

    @Test
    public void addTask_addTaskToAddressBook_evokeAddressBookChangedEvent()
            throws DuplicateEventException {
        ModelManager model = new ModelManager(addressBook, userPrefs);
        modelManager.addTask(TYPICAL_TASK_3);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }

    @Test
    public void removeTask_removeTaskFromAddressBook_evokeAddressBookChangedEvent()
            throws EventNotFoundException {
        ModelManager model = new ModelManager(addressBook, userPrefs);
        modelManager.deleteTask(TYPICAL_TASK_1);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }

    @Test
    public void addTask_addAppointmentToAddressBook_evokeAppointmentListChangedEvent()
            throws DuplicateEventException {
        ModelManager model = new ModelManager(addressBook, userPrefs);
        modelManager.addAppointment(TYPICAL_APPOINTMENT_3);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AppointmentListChangedEvent);
        TestCase.assertTrue(eventsCollectorRule.eventsCollector.getSize() == 2);
    }

    @Test
    public void removeTask_removeAppointmentFromAddressBook_evokeAppointmentListChangedEvent()
            throws EventNotFoundException {
        ModelManager model = new ModelManager(addressBook, userPrefs);
        modelManager.deleteAppointment(TYPICAL_APPOINTMENT_1);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AppointmentListChangedEvent);
        TestCase.assertTrue(eventsCollectorRule.eventsCollector.getSize() == 2);
    }

    @Test
    public void equals() {
        AddressBook differentAddressBook = new AddressBook();

        // same values -> returns true
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns true
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookName("differentName");
        assertTrue(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void deleteTag_tagNotPresent_modelUnchanged() throws DuplicatePersonException, PersonNotFoundException {
        AddressBook testAddressBook = new AddressBookBuilder().withPerson(AMY).withPerson(BOB).build();
        UserPrefs userPrefs = new UserPrefs();
        ModelManager modelManager = new ModelManager(testAddressBook, userPrefs);
        modelManager.deleteTag(new Tag(VALID_TAG_NOTUSED));

        assertEquals(new ModelManager(testAddressBook, userPrefs), modelManager);
    }


    @Test
    public void deleteTag_tagUsedByMultiplePeople_tagRemoved() throws DuplicatePersonException,
                                                                      PersonNotFoundException {
        AddressBook testAddressBook = new AddressBookBuilder().withPerson(AMY).withPerson(BOB).build();
        ModelManager modelManager = new ModelManager(testAddressBook, userPrefs);
        modelManager.deleteTag(new Tag(VALID_TAG_FRIEND));

        Person amyWithoutFriendTag = new PersonBuilder(AMY).withTags().build();
        Person bobWithoutFriendTag = new PersonBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();
        AddressBook expectedAddressBook = new AddressBookBuilder().withPerson(amyWithoutFriendTag)
                                              .withPerson(bobWithoutFriendTag).build();

        assertEquals(new ModelManager(expectedAddressBook, userPrefs), modelManager);
    }
}
