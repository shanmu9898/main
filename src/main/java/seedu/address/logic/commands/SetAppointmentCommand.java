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
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.person.Person;

//@@author Sisyphus25
/**
 * Adds an appointment with the person at {@code index} in the person list to the address book.
 */
public class SetAppointmentCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "set_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appoinment to the address book.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_START_TIME + "START-DATE START-TIME "
            + PREFIX_END_TIME + "END-DATE END-TIME "
            + PREFIX_PERSON_TO_MEET_INDEX + "PERSON TO MEET\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Meet James "
            + PREFIX_START_TIME + "20/05/2018 10:00 "
            + PREFIX_END_TIME + "20/05/2018 12:00 "
            + PREFIX_PERSON_TO_MEET_INDEX + "3 ";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book";

    private final Appointment baseAppointmentWithoutPerson;
    private final Index index;

    private PersonToMeet personToMeet;

    /**
     * Creates a SetAppointmentCommand without any PersonToMeet
     */
    public SetAppointmentCommand(Appointment baseAppointmentWithoutPerson) {
        this(baseAppointmentWithoutPerson, null);
    }

    /**
     * Creates a SetAppointmentCommand to add the specified {@code Appointment}
     */
    public SetAppointmentCommand(Appointment baseAppointmentWithoutPerson, Index index) {
        requireNonNull(baseAppointmentWithoutPerson);
        this.baseAppointmentWithoutPerson = baseAppointmentWithoutPerson;
        this.index = index;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            Appointment toAdd;
            if (personToMeet != null) {
                toAdd = new Appointment(baseAppointmentWithoutPerson.getTitle(), baseAppointmentWithoutPerson.getTime(),
                        baseAppointmentWithoutPerson.getEndTime(), personToMeet);
            } else {
                toAdd = baseAppointmentWithoutPerson;
            }
            model.addAppointment(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicateEventException e) {
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
                && baseAppointmentWithoutPerson.equals(((SetAppointmentCommand) other).baseAppointmentWithoutPerson));
    }
}
