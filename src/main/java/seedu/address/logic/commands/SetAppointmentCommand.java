package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_TO_MEET_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.PersonToMeet;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;

/**
 * Adds an appointment to the address book.
 */
public class SetAppointmentCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "set_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the address book. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME "
            + PREFIX_PERSON_TO_MEET_INDEX + "PERSON TO MEET "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Meet James "
            + PREFIX_START_TIME + "20/05/2018 10:00 "
            + PREFIX_END_TIME + "20/05/2018 12:00 "
            + PREFIX_PERSON_TO_MEET_INDEX + "3 ";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book";

    private final Appointment appointment;
    private final Index index;

    private PersonToMeet personToMeet;

    public SetAppointmentCommand(Appointment appointment) {
        this(appointment, null);
    }

    /**
     * Creates a SetAppointmentCommand to add the specified {@code Appointment}
     */
    public SetAppointmentCommand(Appointment appointment, Index index) {
        requireNonNull(appointment);
        this.appointment = appointment;
        this.index = index;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            Appointment toAdd;
            if (personToMeet != null) {
                toAdd = new Appointment(appointment.getTitle(), appointment.getTime(),
                        appointment.getEndTime(), personToMeet);
            } else {
                toAdd = appointment;
            }
            model.addEvent(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueEventList.DuplicateEventException e) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        if (index != null) {
            List<Person> lastShownList = model.getFilteredPersonList();

            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person person = lastShownList.get(index.getZeroBased());
            personToMeet = new PersonToMeet(person.getName().fullName, person.getEmail().value);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetAppointmentCommand // instanceof handles nulls
                && appointment.equals(((SetAppointmentCommand) other).appointment));
    }
}
