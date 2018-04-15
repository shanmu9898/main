package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.education.Class;
import seedu.address.model.education.Subject;
import seedu.address.model.education.exceptions.DuplicateClassException;
import seedu.address.model.event.Time;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Forms a class in TeachConnect.
 */
public class FormCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "form";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": forms a class in TeachConnect.\n"
            + "Parameters: "
            + "SUBJECT "
            + PREFIX_NAME + "CLASS-NAME "
            + PREFIX_START_TIME + "START-DATE "
            + PREFIX_END_TIME + "END-DATE "
            + PREFIX_INDEX + "INDEX,...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Physics "
            + PREFIX_NAME + "class1 "
            + PREFIX_START_TIME + "10/01/2019 "
            + PREFIX_END_TIME + "12/07/2019 "
            + PREFIX_INDEX + "1,9,20,4";

    public static final String MESSAGE_SUCCESS = "New class %1$s formed.";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists";

    private final Name className;
    private final Subject subject;
    private final Time startTime;
    private final Time endTime;
    private final List<Index> indexList;

    private List<Student> studentList = new ArrayList<>();
    private List<Name> studentNames = new ArrayList<>();
    private Class toAdd;

    /**
     * Creates an FormCommand to form the specified {@code Class}
     */
    public FormCommand(Name name, Subject subj, Time start, Time end, List<Index> indexes) {
        requireNonNull(name);
        requireNonNull(subj);
        requireNonNull(start);
        requireNonNull(end);
        requireNonNull(indexes);
        className = name;
        subject = subj;
        startTime = start;
        endTime = end;
        indexList = indexes;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            model.addClass(toAdd, studentList);
        } catch (DuplicateClassException e) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, className));
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        for (Index targetIndex: indexList) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_INDEX, targetIndex.getOneBased()));
            }

            if (lastShownList.get(targetIndex.getZeroBased()) instanceof Student) {
                Student enteringStudent = (Student) lastShownList.get(targetIndex.getZeroBased());
                studentList.add(enteringStudent);
                studentNames.add(enteringStudent.getName());
            } else {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_STUDENT_INDEX,
                        targetIndex.getOneBased()));
            }
        }

        toAdd = new Class(className, subject, startTime, endTime, studentNames);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FormCommand // instanceof handles nulls
                && this.className.equals(((FormCommand) other).className)
                && this.subject.equals(((FormCommand) other).subject)
                && this.startTime.equals(((FormCommand) other).startTime)
                && this.endTime.equals(((FormCommand) other).endTime)
                && this.indexList.equals(((FormCommand) other).indexList));
    }
}
