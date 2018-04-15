# shanmu9898
###### /java/seedu/address/logic/parser/ImportCommandParserTest.java
``` java
public class ImportCommandParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ImportCommandParser importCommandParser = new ImportCommandParser();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        importCommandParser.parse(null);

    }

    @Test
    public void parse_emptyString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        importCommandParser.parse(" ");

    }

    @Test
    public void parse_moreThanOneBlockOfString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        importCommandParser.parse("invalid two strings");
    }

    @Test
    public void parse_validString_success() {
        String input = "./src/test/data/XmlAddressBookStorgageTest/importsamplefile.xml";
        ImportCommand expectedCommand = new ImportCommand(input);
        assertParseSuccess(importCommandParser, input, expectedCommand);
    }




}
```
###### /java/seedu/address/logic/parser/AddressBookParserTest.java
``` java
    @Test
    public void parseCommand_export() throws Exception {
        ExportCommand command = (ExportCommand) parser.parseCommand(
                ExportCommand.COMMAND_WORD + " " + PREFIX_NAME + NAME_NEEDED + " " + PREFIX_RANGE + RANGE_ALL
                        + " " + PREFIX_TAG + TAG_NEEDED + " " + PREFIX_PATH + PATH_NEEDED + " " + PREFIX_TYPE
                        + TYPE_NEEDED);
        assertEquals (new ExportCommand ("all", new Tag ("friends"), "./data",
                "name", "xml"), command);
    }

    @Test
    public void parseCommand_import() throws Exception {
        ImportCommand command = (ImportCommand) parser.parseCommand(
                ImportCommand.COMMAND_WORD + " "
                        + "src/test/data/XmlAddressBookStorageTest/importsamplefile.xml");
        assertEquals(new ImportCommand("src/test/data/XmlAddressBookStorageTest/importsamplefile.xml"),
                command);
    }

    @Test
    public void parseCommand_shortcut() throws Exception {
        ShortcutCommand command = (ShortcutCommand) parser.parseCommand(
                ShortcutCommand.COMMAND_WORD + " " + "list" + " " + "l");
        assertEquals(new ShortcutCommand("list", "l"), command);
    }

    @Test
    public void parseCommand_deleteShortcut() throws Exception {
        DeleteShortcutCommand command = (DeleteShortcutCommand) parser.parseCommand(
                DeleteShortcutCommand.COMMAND_WORD + " " + "list" + " " + "l");
        assertEquals(new DeleteShortcutCommand("list", "l"), command);
    }
```
###### /java/seedu/address/logic/parser/DeleteShortcutCommandParserTest.java
``` java
package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteShortcutCommandParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private DeleteShortcutCommandParser deleteShortcutCommandParser = new DeleteShortcutCommandParser();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        deleteShortcutCommandParser.parse(null);

    }

    @Test
    public void parse_emptyString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        deleteShortcutCommandParser.parse(" ");

    }

    @Test
    public void parse_validString_success() {
        String inputCommandWord = "list";
        String inputShortcutWord = "l";
        String input = "list l";
        DeleteShortcutCommand expectedCommand = new DeleteShortcutCommand(inputCommandWord, inputShortcutWord);
        assertParseSuccess(deleteShortcutCommandParser, input, expectedCommand);
    }
}
```
###### /java/seedu/address/logic/parser/ExportCommandParserTest.java
``` java
public class ExportCommandParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ExportCommandParser exportCommandParser = new ExportCommandParser();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        exportCommandParser.parse(null);
    }

    @Test
    public void parse_differentType_invalidFormatCommandResult() throws ParseException {
        Tag testingTag = new Tag("shouldnotbethistag");
        String testingInput = " n/name r/all p/./data te/random";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name", "normal");
        assertParseFailure(exportCommandParser, testingInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportCommand.MESSAGE_USAGE));


    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Tag testingTag = new Tag("shouldnotbethistag");
        String testingInput = " n/name r/all p/./data te/xml";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name", "xml");
        assertParseSuccess(exportCommandParser, testingInput, expectedCommand);
    }



    @Test
    public void parse_allfieldsPresent_success() {
        Tag testingTag = new Tag("friends");
        String testingInput = " n/name r/all t/friends p/./data te/xml";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name", "xml");
        assertParseSuccess(exportCommandParser, testingInput, expectedCommand);
    }

}
```
###### /java/seedu/address/logic/parser/ShortcutCommandParserTest.java
``` java
package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ShortcutCommandParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ShortcutCommandParser shortcutCommandParser = new ShortcutCommandParser();

    @Test
    public void parse_nullString_throwsNullPointerException() throws ParseException {
        thrown.expect(NullPointerException.class);
        shortcutCommandParser.parse(null);

    }

    @Test
    public void parse_emptyString_throwsParseException() throws ParseException {
        thrown.expect(ParseException.class);
        shortcutCommandParser.parse(" ");

    }

    @Test
    public void parse_validString_success() {
        String inputCommandWord = "list";
        String inputShortcutWord = "l";
        String input = "list l";
        ShortcutCommand expectedCommand = new ShortcutCommand(inputCommandWord, inputShortcutWord);
        assertParseSuccess(shortcutCommandParser, input, expectedCommand);
    }
}
```
###### /java/seedu/address/logic/commands/ImportCommandTest.java
``` java
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
```
###### /java/seedu/address/logic/commands/DeleteShortcutCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

public class DeleteShortcutCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;
    private Model expectedModel;
    private final String validtestingCommandWord = "list";
    private final String validtestingShortcutWord = "l";
    private final String invalidtestingCommandWord = "king";

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullCommandWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteShortcutCommand(null, validtestingShortcutWord);
    }

    @Test
    public void constructor_nullShortcutWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new DeleteShortcutCommand(validtestingCommandWord, null);
    }

    @Test
    public void executeUndoCommand_invalidCommandWord_throwsCommandException() throws CommandException {
        thrown.expect(CommandException.class);
        CommandResult commandResult = getDeleteShortcutForCommand(validtestingShortcutWord,
                                                                  invalidtestingCommandWord,
                                                                  model).executeUndoableCommand();
    }

    @Test
    public void executeUndoCommand_inputNotPresent_throwsCommandException() throws CommandException {
        thrown.expect(CommandException.class);
        CommandResult commandResult = getDeleteShortcutForCommand(validtestingShortcutWord,
                                                                  validtestingCommandWord,
                                                                  model).executeUndoableCommand();
    }

    @Test
    public void executeUndoCommand_validInput_commandSuccess()
                                        throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        ShortcutDoubles shortcutDoubles = new ShortcutDoubles(validtestingShortcutWord, validtestingCommandWord);
        model.addCommandShortcut(shortcutDoubles);
        assertCommandSuccess(getDeleteShortcutForCommand(validtestingShortcutWord,
                                                         validtestingCommandWord,
                                                         model),
                                                         model,
                                                         String.format(DeleteShortcutCommand
                                                                       .MESSAGE_DELETE_SHORTCUT_SUCCESS),
                                                         expectedModel);
    }

    @Test
    public void equals() throws Exception {
        DeleteShortcutCommand deleteFirstCommand = getDeleteShortcutForCommand(validtestingShortcutWord,
                                                                               validtestingCommandWord,
                                                                               model);
        DeleteShortcutCommand deleteSecondCommand = getDeleteShortcutForCommand("c",
                                                                                "clear",
                                                                                 model);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteShortcutCommand deleteFirstCommandCopy = getDeleteShortcutForCommand("l",
                                                                           "list",
                                                                            model);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Generates a new AddCommand with the details of the given person.
     */
    private DeleteShortcutCommand getDeleteShortcutForCommand(String shortcutWord, String commandWord, Model model) {
        DeleteShortcutCommand command = new DeleteShortcutCommand(commandWord, shortcutWord);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
}
```
###### /java/seedu/address/logic/commands/ListCommandTest.java
``` java
    @Test
    public void execute_listShortcut_success() throws CommandException {
        listCommand = new ListCommand(TYPE_SHORTCUT);
        listCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        CommandResult result = listCommand.execute();
        assertEquals(MESSAGE_SUCCESS + TYPE_SHORTCUT, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ToggleListEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
```
###### /java/seedu/address/logic/commands/ShortcutCommandTest.java
``` java
package seedu.address.logic.commands;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

public class ShortcutCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model;
    private Model expectedModel;
    private final String validTestingCommandWord = "list";
    private final String validTestingShortcutWord = "l";
    private final String invalidTestingCommandWord = "king";

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }


    @Test
    public void constructor_nullCommandWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutCommand(null, validTestingShortcutWord);
    }

    @Test
    public void constructor_nullShortcutWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutCommand(validTestingCommandWord, null);
    }

    @Test
    public void executeUndoCommand_invalidCommandWord_throwsCommandException() throws CommandException {
        thrown.expect(CommandException.class);
        thrown.expectMessage(ShortcutCommand.MESSAGE_NO_COMMAND_TO_MAP);

        CommandResult commandResult = getAddShortcutForCommand(validTestingShortcutWord,
                                                               invalidTestingCommandWord,
                                                               model).executeUndoableCommand();
    }

    @Test
    public void executeUndoCommand_shortcutWordPresent_throwsCommandException()
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException, CommandException {
        ShortcutDoubles shortcutDoubles = new ShortcutDoubles(validTestingShortcutWord, validTestingCommandWord);
        model.addCommandShortcut(shortcutDoubles);
        CommandResult commandResult = getAddShortcutForCommand(validTestingShortcutWord,
                                                               validTestingCommandWord,
                                                               model).executeUndoableCommand();
        assertEquals(commandResult.feedbackToUser, String.format(ShortcutCommand.MESSAGE_SHORTCUT_AVAILABLE));
    }

    @Test
    public void executeUndoCommand_validInput_commandSuccess()
                             throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        ShortcutDoubles shortcutDoubles = new ShortcutDoubles(validTestingShortcutWord, validTestingCommandWord);
        expectedModel.addCommandShortcut(shortcutDoubles);
        assertCommandSuccess(getAddShortcutForCommand(validTestingShortcutWord,
                                                      validTestingCommandWord,
                                                      model), model,
                                                       String.format(ShortcutCommand.MESSAGE_SUCCESS), expectedModel);
    }

    /**
     * Generates a new AddCommand with the details of the given person.
     */
    private ShortcutCommand getAddShortcutForCommand(String shortcutWord, String commandWord, Model model) {
        ShortcutCommand command = new ShortcutCommand(commandWord, shortcutWord);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    @Test
    public void equals() throws Exception {
        ShortcutCommand deleteFirstCommand = getAddShortcutForCommand(validTestingShortcutWord,
                                                                      validTestingCommandWord,
                                                                      model);
        ShortcutCommand deleteSecondCommand = getAddShortcutForCommand("c",
                                                                       "clear",
                                                                        model);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ShortcutCommand deleteFirstCommandCopy = getAddShortcutForCommand("l",
                                                                          "list",
                                                                           model);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
```
###### /java/seedu/address/logic/commands/ExportCommandTest.java
``` java
public class ExportCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final Tag testingTag = new Tag("testingTag");
    private final String testingPath = "./test/data/XmlAddressBookStorageTest";
    private final String name = "testingName";
    private final String testingRange = "1,5";
    private final String fileTypeNormal = "xml";
    private final String fileTypeExcel = "excel";


    @Test
    public void constructor_nullRange_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ExportCommand(null, testingTag, testingPath, name, fileTypeExcel);
    }

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ExportCommand(testingRange, testingTag, null, name, fileTypeNormal);
    }

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ExportCommand(testingRange, testingTag, testingPath, null, fileTypeNormal);
    }

    @Test
    public void constructor_nullType_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ExportCommand(testingRange, testingTag, testingPath, name, null);
    }

    @Test
    public void execute_multipleRange_showsMessageError() {
        String testingMultiRange = "1,2,3";
        ExportCommand exportCommand = new ExportCommand(testingMultiRange, testingTag, testingPath,
                name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_RANGE_ERROR), model);

    }

    @Test
    public void execute_outOfRange_showsMessageError() {
        String testingOutOfRange = "0,10000000";
        ExportCommand exportCommand = new ExportCommand(testingOutOfRange, testingTag, testingPath,
                name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_OUT_OF_BOUNDS), model);

    }

    @Test
    public void execute_successfulExport_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand(testingRange, testingTag, testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_successfulExportWithAllRange_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand("all", testingTag, testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_exportWithSingleRangeAndMismatchTag_showsMessageError() {
        ExportCommand exportCommand = new ExportCommand("2", testingTag, testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandFailure(exportCommand, model, String.format(exportCommand.MESSAGE_TAG_CONTACT_MISMATCH));
    }

    @Test
    public void execute_successfulExportWithSingleRange_showsNoMessageError() {
        Tag friendsTag = new Tag("friends");
        ExportCommand exportCommand = new ExportCommand("2", friendsTag, testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_successfulExportWithExcel_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand("1,6", testingTag, testingPath, name, fileTypeExcel);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_successfulExportWithAllRangeExcel_showsNoMessageError() {
        Tag colleguesTag = new Tag("collegues");
        ExportCommand exportCommand = new ExportCommand("all", colleguesTag, testingPath, name, fileTypeExcel);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_rangeNotCorrect_showsMessageError() {
        ExportCommand exportCommand = new ExportCommand("2,1", testingTag, testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_RANGE_ERROR), model);
    }

    @Test
    public void execute_whenTagIsSupposedlyNotGiven_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand("all", new Tag("shouldnotbethistag"),
                testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_whenTagIsSupposedlyNotGivnAndRangeError_showsMessageError() {
        ExportCommand exportCommand = new ExportCommand("2,1", new Tag("shouldnotbethistag"),
                testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_RANGE_ERROR), model);
    }

    @Test
    public void execute_whenTagIsSupposedlyNotGivenAndRangeGiven_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand("1,6", new Tag("shouldnotbethistag"),
                testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }



    @Test
    public void execute_whenRangeIsSelectiveAndOutOfRange_showsMessageError() {
        ExportCommand exportCommand = new ExportCommand("10000000", new Tag("shouldnotbethistag"),
                testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_OUT_OF_BOUNDS), model);
    }

    @Test
    public void execute_classesBeingExported_showsNoError() {
        ExportCommand exportCommand = new ExportCommand(testingPath, name, fileTypeExcel);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_classesBeingExportedXml_showsNoError() {
        ExportCommand exportCommand = new ExportCommand(testingPath, name, fileTypeNormal);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }


    @Test
    public void equals() {
        final ExportCommand comparableCommand = new ExportCommand(testingRange, testingTag, testingPath,
                name, fileTypeNormal);

        // same values -> returns true
        ExportCommand comparedToCommand = new ExportCommand(testingRange, testingTag, testingPath,
                name, fileTypeNormal);
        assertTrue(comparableCommand.equals(comparedToCommand));

        // same object -> returns true
        assertTrue(comparableCommand.equals(comparableCommand));

        // null -> returns false
        assertFalse(comparableCommand.equals(null));

        // different types -> returns false
        assertFalse(comparableCommand.equals(new ClearCommand()));

        // different range -> returns false
        assertFalse(comparableCommand.equals(new ExportCommand("1,2", testingTag, testingPath, name,
                fileTypeNormal)));
    }



}
```
###### /java/seedu/address/model/shortcut/ShortcutDoubleTest.java
``` java
package seedu.address.model.shortcut;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.shortcuts.ShortcutDoubles;

public class ShortcutDoubleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullCommandWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutDoubles("l", null);
    }

    @Test
    public void constructor_nullShortcutWord_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ShortcutDoubles(null , "list");
    }

    @Test
    public void equals() throws Exception {
        ShortcutDoubles deleteFirstCommand = new ShortcutDoubles("l", "list");
        ShortcutDoubles deleteSecondCommand = new ShortcutDoubles("c", "clear");

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ShortcutDoubles deleteFirstCommandCopy = new ShortcutDoubles("l", "list");
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
```
###### /java/seedu/address/model/UniqueShortcutDoublesListTest.java
``` java
package seedu.address.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

public class UniqueShortcutDoublesListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueShortcutDoublesList uniqueShortcutDoublesList = new UniqueShortcutDoublesList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueShortcutDoublesList.asObservableList().remove(0);
    }
}
```
###### /java/seedu/address/model/AddressBookTest.java
``` java
    @Test
    public void getShortcutList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getCommandsList().remove(0);
    }

```
###### /java/seedu/address/model/AddressBookTest.java
``` java
    @Test
    public void removeTag_tagNotPresent_addressBookUnchanged() throws PersonNotFoundException,
            DuplicatePersonException {
        AddressBook testAddressBook = new AddressBookBuilder().withPerson(BOB).withPerson(AMY).build();

        testAddressBook.removeTag(new Tag(VALID_TAG_NOTUSED));

        AddressBook expectedAddressBook = new AddressBookBuilder().withPerson(BOB).withPerson(AMY).build();

        assertEquals(expectedAddressBook, testAddressBook);
    }

    @Test
    public void removeTag_tagUsedByMultiplePeople_tagRemoved() throws PersonNotFoundException,
            DuplicatePersonException {
        AddressBook testAddressBook = new AddressBookBuilder().withPerson(BOB).withPerson(AMY).build();
        testAddressBook.removeTag(new Tag(VALID_TAG_FRIEND));

        Person amyWithoutFriendTag = new PersonBuilder(AMY).withTags().build();
        Person bobWithoutFriendTag = new PersonBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();

        AddressBook expectedAddressBook = new AddressBookBuilder().withPerson(bobWithoutFriendTag)
                .withPerson(amyWithoutFriendTag).build();

        assertEquals(expectedAddressBook, testAddressBook);
    }
```
###### /java/seedu/address/model/ModelManagerTest.java
``` java
    @Test
    public void getFilteredCommandList_modifyList_throwsUnsupportedOperationException() {
        ModelManager modelManager = new ModelManager();
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredCommandsList().remove(0);
    }
```
###### /java/seedu/address/model/ModelManagerTest.java
``` java
    @Test
    public void addShortcut_addShortcutToAddressBook_evokeAddressBookChangedEvent()
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        ModelManager modelManager = new ModelManager(addressBook, userPrefs);
        modelManager.addCommandShortcut(SHORTCUT_DOUBLES_1);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }
```
###### /java/seedu/address/model/ModelManagerTest.java
``` java
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
```
###### /java/seedu/address/testutil/ExportCommandHelper.java
``` java
/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class ExportCommandHelper {
    public static final String RANGE_ALL = "all";
    public static final String TAG_NEEDED = "friends";
    public static final String PATH_NEEDED = "./data";
    public static final String NAME_NEEDED = "name";
    public static final String TYPE_NEEDED = "xml";


}
```
###### /java/seedu/address/testutil/TypicalImportedPersons.java
``` java
package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STUDENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicatePersonException;

```
###### /java/seedu/address/testutil/TypicalImportedPersons.java
``` java
/**
 * Special Util class to get people for ImportCommandTest class.
 */
public class TypicalImportedPersons {
    public static final Person FLICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("85355255")
            .withTags("friends").build();
    public static final Person FENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person FARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person FANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").build();
    public static final Person FLLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person GIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Student FEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();
    public static final Student FVAN = new StudentBuilder().withName("Ivan Kutz").withPhone("9867723")
            .withEmail("wolf@example.com").withAddress("Centre Street").build();
    public static final Student FOHN = new StudentBuilder().withName("John Blake").withPhone("9575232")
            .withEmail("star@example.com").withAddress("Hollywood").build();

    // Manually added
    public static final Person FOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person FDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    public static final Student FTUDENT_HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Student FTUDENT_IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person FMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person FOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final Student FTUDENT_AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_STUDENT).build();
    public static final Student FTUDENT_BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_STUDENT)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalImportedPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getImportedAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getImportedPersons()) {
            try {
                ab.addPerson(person);
            } catch (DuplicatePersonException e) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

    public static List<Person> getImportedPersons() {
        return new ArrayList<>(Arrays.asList(FLICE, FENSON, FARL, FANIEL, FLLE, GIONA, FEORGE));
    }
}
```
###### /java/seedu/address/testutil/TypicalShortcuts.java
``` java
package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * Few Typical Shortcuts
 */
public class TypicalShortcuts {
    public static final ShortcutDoubles SHORTCUT_DOUBLES_1 =
            new ShortcutCommandBuilder("l", "list").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_2 =
            new ShortcutCommandBuilder("c", "clear").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_3 =
            new ShortcutCommandBuilder("ll", "list").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_4 =
            new ShortcutCommandBuilder("cc", "clear").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_5 =
            new ShortcutCommandBuilder("a", "add").build();
    public static final ShortcutDoubles SHORTCUT_DOUBLES_6 =
            new ShortcutCommandBuilder("aa", "add").build();

    public static List<ShortcutDoubles> getTypicalShortcuts() {
        return new ArrayList<>(Arrays.asList(SHORTCUT_DOUBLES_1, SHORTCUT_DOUBLES_2));
    }

}
```
###### /java/seedu/address/testutil/ShortcutCommandBuilder.java
``` java
package seedu.address.testutil;

import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * A utility class to help with building Shortcut objects.
 */
public class ShortcutCommandBuilder {

    private String shortcutWord;
    private String commandWord;

    public ShortcutCommandBuilder(String shortcutWord, String commandWord) {
        this.commandWord = commandWord;
        this.shortcutWord = shortcutWord;
    }


    /**
     * @return an {@code Appointment} from the data feed to constructor
     */
    public ShortcutDoubles build() {
        return new ShortcutDoubles(shortcutWord, commandWord);
    }
}
```
###### /java/seedu/address/testutil/modelstub/ModelStub.java
``` java
    @Override
    public void addCommandShortcut(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        fail("This method should not be called.");
    }

    @Override
    public void deleteCommandShortcut(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        fail("This method should not be called");
    }

```
