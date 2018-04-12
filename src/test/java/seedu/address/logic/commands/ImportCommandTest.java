package seedu.address.logic.commands;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.StudentBuilder;

//@@author shanmu9898
public class ImportCommandTest {


    private static final String INVALID_FILE_LOCATION = "./data/samplefile.xml";
    private static final String VALID_FILE_LOCATION =
            "src/test/data/XmlAddressBookStorageTest/importsamplefile.xml";
    private static final String VALID_FILE_LOCATION2 =
            "src/test/data/XmlAddressBookStorageTest/importClassAndStudentSample.xml";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ImportCommand(null);
    }

    @Test
    public void execute_importFailure_throwsException() {
        ImportCommand command = prepareCommand(INVALID_FILE_LOCATION);
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandFailure(command, model, String.format(command.MESSAGE_INVALID_FILE));
    }

    @Test
    public void execute_acceptedSuccess_successfulImport() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ClearCommand clearCommand = new ClearCommand();
        clearCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        clearCommand.executeUndoableCommand();

        ImportCommand command = prepareCommand(VALID_FILE_LOCATION);

        assertCommandSuccess(command, model, String.format (command.MESSAGE_SUCCESS, 7, 0, 0, 0, 0, 0), model);
    }

    @Test
    public void execute_duplicateClassesAndStudents_successfulImport() throws DuplicatePersonException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Student studentSample = new StudentBuilder().withName("Mary Jane8").withPhone("98765432")
                .withEmail("MJ@example.com").withAddress("478, Pasir Ris, #03-12").withTags("AStar").build();
        model.addStudent(studentSample);
        ImportCommand command = prepareCommand(VALID_FILE_LOCATION2);
        assertCommandSuccess(command, model, String.format (command.MESSAGE_SUCCESS, 0, 0, 7, 0, 2, 0), model);
    }

    @Test
    public void execute_acceptedSuccess_successfulClassAndStudentImport() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ClearCommand clearCommand = new ClearCommand();
        clearCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        clearCommand.executeUndoableCommand();

        ImportCommand command = prepareCommand(VALID_FILE_LOCATION2);

        assertCommandSuccess(command, model, String.format(command.MESSAGE_SUCCESS, 0, 0, 7, 0, 2, 0), model);
    }

    @Test
    public void equals() {
        final ImportCommand comparableCommand = new ImportCommand(VALID_FILE_LOCATION);

        // same values -> returns true
        ImportCommand comparedToCommand = new ImportCommand(VALID_FILE_LOCATION);
        assertTrue(comparableCommand.equals(comparedToCommand));

        // same object -> returns true
        assertTrue(comparableCommand.equals(comparableCommand));

        // null -> returns false
        assertFalse(comparableCommand.equals(null));

        // different types -> returns false
        assertFalse(comparableCommand.equals(new ClearCommand()));

        // different range -> returns false
        assertFalse(comparableCommand.equals(new ImportCommand("./data/sampleimportfile.xml")));
    }


    /**
     * A method to prepare the Import command based on the path given
     */
    private ImportCommand prepareCommand(String path) {
        ImportCommand importCommand = new ImportCommand(path);
        importCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        return importCommand;
    }

}
