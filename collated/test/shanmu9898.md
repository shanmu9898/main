# shanmu9898
###### \java\seedu\address\logic\commands\ExportCommandTest.java
``` java
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
    public void execute_multipleRange_showsMessageError() {
        String testingMultiRange = "1,2,3";
        ExportCommand exportCommand = new ExportCommand(testingMultiRange, testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_RANGE_ERROR), model);

    }

    @Test
    public void execute_outOfRange_showsMessageError() {
        String testingOutOfRange = "0,10000000";
        ExportCommand exportCommand = new ExportCommand(testingOutOfRange, testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_OUT_OF_BOUNDS), model);

    }

    @Test
    public void execute_successfulExport_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand(testingRange, testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_successfulExportWithAllRange_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand("all", testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_successfulExportWithSingleRange_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand("2", testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_rangeNotCorrect_showsMessageError() {
        ExportCommand exportCommand = new ExportCommand("2,1", testingTag, testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_RANGE_ERROR), model);
    }

    @Test
    public void execute_whenTagIsSupposedlyNotGiven_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand("all", new Tag("shouldnotbethistag"), testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_whenTagIsSupposedlyNotGivnAndRangeError_showsMessageError() {
        ExportCommand exportCommand = new ExportCommand("2,1", new Tag("shouldnotbethistag"), testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_RANGE_ERROR), model);
    }

    @Test
    public void execute_whenTagIsSupposedlyNotGivenAndRangeGiven_showsNoMessageError() {
        ExportCommand exportCommand = new ExportCommand(testingRange, new Tag("shouldnotbethistag"), testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void execute_whenRangeIsSelectiveAndOutOfRange_showsMessageError() {
        ExportCommand exportCommand = new ExportCommand("10000000", new Tag("shouldnotbethistag"), testingPath, name);
        exportCommand.setData(new ModelManager(getTypicalAddressBook(), new UserPrefs()), new CommandHistory(),
                new UndoRedoStack());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(exportCommand, model, String.format(exportCommand.MESSAGE_OUT_OF_BOUNDS), model);
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
```
###### \java\seedu\address\logic\commands\ImportCommandTest.java
``` java
public class ImportCommandTest {


    private static final String INVALID_FILE_LOCATION = "./data/samplefile.xml";
    private static final String VALID_FILE_LOCATION =
            "src/test/data/XmlAddressBookStorageTest/importsamplefile.xml";

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

        assertCommandSuccess(command, model, String.format (command.MESSAGE_SUCCESS, "7", "0"), model);
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
###### \java\seedu\address\logic\parser\AddressBookParserTest.java
``` java
    @Test
    public void parseCommand_export() throws Exception {
        ExportCommand command = (ExportCommand) parser.parseCommand(
                ExportCommand.COMMAND_WORD + " " + PREFIX_NAME + NAME_NEEDED + " " + PREFIX_RANGE + RANGE_ALL
                        + " " + PREFIX_TAG_EXPORT + TAG_NEEDED + " " + PREFIX_PATH + PATH_NEEDED);
        assertEquals (new ExportCommand ("all", new Tag ("friends"), "./data",
                "name"), command);
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
###### \java\seedu\address\logic\parser\ExportCommandParserTest.java
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
    public void parse_optionalFieldsMissing_success() {
        Tag testingTag = new Tag("shouldnotbethistag");
        String testingInput = " n/name r/all p/./data";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name");
        assertParseSuccess(exportCommandParser, testingInput, expectedCommand);
    }

    @Test
    public void parse_allfieldsPresent_success() {
        Tag testingTag = new Tag("friends");
        String testingInput = " n/name r/all t/friends p/./data";
        ExportCommand expectedCommand = new ExportCommand("all", testingTag, "./data", "name");
        assertParseSuccess(exportCommandParser, testingInput, expectedCommand);
    }

}
```
###### \java\seedu\address\logic\parser\ImportCommandParserTest.java
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
###### \java\seedu\address\testutil\ExportCommandBuilder.java
``` java
/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class ExportCommandBuilder {
    public static final String RANGE_ALL = "all";
    public static final String TAG_NEEDED = "friends";
    public static final String PATH_NEEDED = "./data";
    public static final String NAME_NEEDED = "name";


}
```
###### \java\seedu\address\testutil\TypicalImportedPersons.java
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
