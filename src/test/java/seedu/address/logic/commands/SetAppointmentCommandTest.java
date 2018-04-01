package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static seedu.address.testutil.TypicalEvents.APPOINTMENT_WITHOUT_PERSON_1;
import static seedu.address.testutil.TypicalEvents.APPOINTMENT_WITHOUT_PERSON_3;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_2;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_3;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Appointment;

//@@author Sisyphus25
public class SetAppointmentCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SetAppointmentCommand(null, null);
    }

    @Test
    public void execute_invalidPersonToMeetIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SetAppointmentCommand command = getSetAppointmentCommand(TYPICAL_APPOINTMENT_3, outOfBoundsIndex, model);

        try {
            command.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException ce) {
            assertEquals(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ce.getMessage());
        }
    }

    @Test
    public void execute_appointmentWithoutPersonToMeetAccepted_addSuccessful() throws Exception {
        CommandResult commandResult =
                getSetAppointmentCommand(APPOINTMENT_WITHOUT_PERSON_3, null, model).execute();

        assertEquals(String.format(
                SetAppointmentCommand.MESSAGE_SUCCESS, APPOINTMENT_WITHOUT_PERSON_3), commandResult.feedbackToUser);
    }

    @Test
    public void execute_appointmentWithPersonToMeetAccepted_addSuccessful() throws Exception {
        CommandResult commandResult =
                getSetAppointmentCommand(APPOINTMENT_WITHOUT_PERSON_3, INDEX_THIRD, model).execute();

        assertEquals(String.format(
                SetAppointmentCommand.MESSAGE_SUCCESS, TYPICAL_APPOINTMENT_3), commandResult.feedbackToUser);
    }

    @Test
    public void execute_duplicateAppointmentsameIndex_throwsCommandException() throws Exception {
        thrown.expect(CommandException.class);
        thrown.expectMessage(SetAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);

        getSetAppointmentCommand(APPOINTMENT_WITHOUT_PERSON_1, INDEX_FIRST, model).execute();
    }

    @Test
    public void equals() {
        SetAppointmentCommand addAppointment1 =
                new SetAppointmentCommand(TYPICAL_APPOINTMENT_1);
        SetAppointmentCommand addAppointment2 =
                new SetAppointmentCommand(TYPICAL_APPOINTMENT_2);

        // same object -> returns true
        assertTrue(addAppointment1.equals(addAppointment1));

        // same values -> returns true
        SetAppointmentCommand addAppointment1Copy =
                new SetAppointmentCommand(TYPICAL_APPOINTMENT_1);
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
    private SetAppointmentCommand getSetAppointmentCommand(Appointment baseAppointment, Index index, Model model) {
        SetAppointmentCommand command = new SetAppointmentCommand(baseAppointment, index);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

}
