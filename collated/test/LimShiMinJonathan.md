# LimShiMinJonathan
###### /java/seedu/address/logic/parser/AddressBookParserTest.java
``` java
    @Test
    public void parseCommand_sort() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD);
        assertTrue(command instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);

    }
```
###### /java/seedu/address/logic/commands/SortCommandTest.java
``` java
public class SortCommandTest {
    private Model model;
    private SortCommand sortCommand;

    @Before
    public void setUp() throws DuplicatePersonException {

        AddressBook unsortedAddressBook = new AddressBook();
        unsortedAddressBook.setPersons(TypicalPersons.getUnsortedPersons());

        model = new ModelManager(unsortedAddressBook, new UserPrefs());

        sortCommand = new SortCommand();
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack());

    }

    @Test
    public void executeSort() throws DuplicatePersonException {
        AddressBook sortedAddressBook = new AddressBook();
        sortedAddressBook.setPersons(TypicalPersons.getTypicalPersons());

        Model expectedModel;
        expectedModel = new ModelManager(sortedAddressBook, new UserPrefs());

        CommandResult result = sortCommand.execute();
        assertEquals(sortCommand.MESSAGE_SUCCESS, result.feedbackToUser);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

}
```
###### /java/seedu/address/testutil/TypicalPersons.java
``` java
    public static List<Person> getUnsortedPersons() {
        return new ArrayList<>(Arrays.asList(ELLE, BENSON, FIONA, DANIEL, ALICE, CARL, GEORGE));
    }
```
