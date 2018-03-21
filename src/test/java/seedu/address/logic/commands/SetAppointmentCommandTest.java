package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

public class SetAppointmentCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SetAppointmentCommand(null);
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();

        CommandResult commandResult = getSetAppointmentCommand(
                (Appointment) TYPICAL_APPOINTMENT_1, modelStub).execute();

        assertEquals(String.format(SetAppointmentCommand.MESSAGE_SUCCESS, TYPICAL_APPOINTMENT_1), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(TYPICAL_APPOINTMENT_1), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubThrowingDuplicateEventException();

        thrown.expect(CommandException.class);
        thrown.expectMessage(SetAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);

        getSetAppointmentCommand((Appointment) TYPICAL_APPOINTMENT_1, modelStub).execute();
    }

    @Test
    public void equals() {
        SetAppointmentCommand addAppointment1 =
                new SetAppointmentCommand((Appointment) TYPICAL_APPOINTMENT_1);
        SetAppointmentCommand addAppointment2 =
                new SetAppointmentCommand((Appointment) TYPICAL_APPOINTMENT_2);

        // same object -> returns true
        assertTrue(addAppointment1.equals(addAppointment1));

        // same values -> returns true
        SetAppointmentCommand addAppointment1Copy =
                new SetAppointmentCommand((Appointment) TYPICAL_APPOINTMENT_1);
        assertTrue(addAppointment1.equals(addAppointment1Copy));

        // different types -> returns false
        assertFalse(addAppointment1.equals(1));

        // null -> returns false
        assertFalse(addAppointment1.equals(null));

        // different appointments -> returns false
        assertFalse(addAppointment1.equals(addAppointment2));
    }

    /**
     * Generates a new SetAppointmentCommand with the details of the given appointment.
     */
    private SetAppointmentCommand getSetAppointmentCommand(Appointment appointment, Model model) {
        SetAppointmentCommand command = new SetAppointmentCommand(appointment);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addPerson(Person person) throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void deletePerson(Person target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(Person target, Person editedPerson)
                throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag) {
            fail("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) throws UniqueEventList.DuplicateEventException {
            fail("This method should not be called.");
        };

        @Override
        public void deleteEvent(Event event) throws UniqueEventList.EventNotFoundException {
            fail("This method should not be called.");
        };

        @Override
        public void updateEvent(Event target, Event editedEvent)
                throws UniqueEventList.DuplicateEventException, UniqueEventList.EventNotFoundException {
            fail("This method should not be called.");
        };
    }

    /**
     * A Model stub that always throw a DuplicateEventException when trying to add an appointment.
     */
    private class ModelStubThrowingDuplicateEventException extends ModelStub {
        @Override
        public void addEvent(Event event) throws UniqueEventList.DuplicateEventException {
            throw new UniqueEventList.DuplicateEventException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public void addEvent(Event event) throws UniqueEventList.DuplicateEventException {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
