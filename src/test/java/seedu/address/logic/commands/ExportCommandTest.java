package seedu.address.logic.commands;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
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
import seedu.address.model.tag.Tag;

public class ExportCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final Tag testingTag = new Tag("testingTag");
    private final String testingPath = "./test/data/XmlAddressBookStorageTest";
    private final String name = "testingName";
    private final String testingRange = "1,5";


    @Test
    public void constructor_nullRange_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ExportCommand(null, testingTag, testingPath, name);
    }

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ExportCommand(testingRange, testingTag, null, name);
    }

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ExportCommand(testingRange, testingTag, testingPath, null);
    }

    @Test
    public void execute_multiplerange_showsMessageError() {
        String testingMultiRange = "1,2,3";
        ExportCommand exportCommand = new ExportCommand(testingMultiRange, testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_RANGE_ERROR), model);

    }

    @Test
    public void execute_outOfRange_showsMessageError() {
        String testingOutofRange = "0,10000000";
        ExportCommand exportCommand = new ExportCommand(testingOutofRange, testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_OUT_OF_BOUNDS), model);

    }

    @Test
    public void execute_succesfulExport_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand(testingRange, testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void equals() {
        final ExportCommand comparableCommand = new ExportCommand(testingRange, testingTag, testingPath, name);

        // same values -> returns true
        ExportCommand comparedToCommand = new ExportCommand(testingRange, testingTag, testingPath, name);
        assertTrue(comparableCommand.equals(comparedToCommand));

        // same object -> returns true
        assertTrue(comparableCommand.equals(comparableCommand));

        // null -> returns false
        assertFalse(comparableCommand.equals(null));

        // different types -> returns false
        assertFalse(comparableCommand.equals(new ClearCommand()));

        // different range -> returns false
        assertFalse(comparableCommand.equals(new ExportCommand("1,2", testingTag, testingPath, name)));
    }



}
