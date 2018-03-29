package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_1;
import static seedu.address.testutil.TypicalEvents.TYPICAL_APPOINTMENT_2;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Appointment;
import seedu.address.testutil.modelstub.ModelStub;
import seedu.address.testutil.modelstub.ModelStubAcceptingAppointmentAdded;
import seedu.address.testutil.modelstub.ModelStubThrowingDuplicateEventException;

//@@author Sisyphus25
public class SetAppointmentCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SetAppointmentCommand(null, null);
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();

        CommandResult commandResult = getSetAppointmentCommand(TYPICAL_APPOINTMENT_1, modelStub).execute();

        assertEquals(String.format(
                SetAppointmentCommand.MESSAGE_SUCCESS, TYPICAL_APPOINTMENT_1), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(TYPICAL_APPOINTMENT_1), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubThrowingDuplicateEventException();

        thrown.expect(CommandException.class);
        thrown.expectMessage(SetAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);

        getSetAppointmentCommand(TYPICAL_APPOINTMENT_1, modelStub).execute();
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
    private SetAppointmentCommand getSetAppointmentCommand(Appointment appointment, Model model) {
        SetAppointmentCommand command = new SetAppointmentCommand(appointment, null);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

}
