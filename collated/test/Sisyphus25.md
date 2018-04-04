# Sisyphus25
###### /java/seedu/address/ui/AppointmentCardTest.java
``` java
public class AppointmentCardTest extends GuiUnitTest {

    @Test
    public void equals() {
        Appointment appointment = TYPICAL_APPOINTMENT_2;
        AppointmentCard appointmentCard = new AppointmentCard(appointment, 0);

        // same appointment, same index -> returns true
        AppointmentCard copy = new AppointmentCard(appointment, 0);
        assertTrue(appointmentCard.equals(copy));

        // same object -> returns true
        assertTrue(appointmentCard.equals(appointmentCard));

        // null -> returns false
        assertFalse(appointmentCard.equals(null));

        // different types -> returns false
        assertFalse(appointmentCard.equals(0));

        // different appointment, same index -> returns false
        Appointment differentAppointment = TYPICAL_APPOINTMENT_3;
        assertFalse(appointmentCard.equals(new AppointmentCard(differentAppointment, 0)));

        // same appointment, different index -> returns false
        assertFalse(appointmentCard.equals(new AppointmentCard(appointment, 1)));
    }

}
```
###### /java/seedu/address/ui/TaskCardTest.java
``` java
public class TaskCardTest extends GuiUnitTest {

    @Test
    public void equals() {
        Task task = TYPICAL_TASK_2;
        TaskCard taskCard = new TaskCard(task, 0);

        // same task, same index -> returns true
        TaskCard copy = new TaskCard(task, 0);
        assertTrue(taskCard.equals(copy));

        // same object -> returns true
        assertTrue(taskCard.equals(taskCard));

        // null -> returns false
        assertFalse(taskCard.equals(null));

        // different types -> returns false
        assertFalse(taskCard.equals(0));

        // different task, same index -> returns false
        Task differentTask = TYPICAL_TASK_1;
        assertFalse(taskCard.equals(new TaskCard(differentTask, 0)));

        Task expiredTask = TYPICAL_TASK_EXPIRED;
        TaskCard expiredTaskCard = new TaskCard(TYPICAL_TASK_EXPIRED, 1);
        // same task, different index -> returns false
        assertFalse(taskCard.equals(expiredTaskCard));
    }
}
```
###### /java/seedu/address/logic/parser/RemoveCommandParserTest.java
``` java
public class RemoveCommandParserTest {
    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "appointment" + " 1", new RemoveCommand(INDEX_FIRST, "appointment"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "not valid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "Appointment" + " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "appointment" + " -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
    }
}
```
###### /java/seedu/address/logic/parser/ChangeThemeCommandParserTest.java
``` java
/**
 * Test scope: similar to ToggleCalendarViewCommandParser Test
 */
public class ChangeThemeCommandParserTest {
    private ChangeThemeCommandParser parser = new ChangeThemeCommandParser();

    @Test
    public void parse_validArgs_returnsToggleCalendarViewCommand() {
        assertParseSuccess(parser, "dark ", new ChangeThemeCommand("dark"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "not a theme",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_INVALID_THEME));
        assertParseFailure(parser, "x",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_INVALID_THEME));
    }
}
```
###### /java/seedu/address/logic/parser/SetAppointmentCommandParserTest.java
``` java
public class SetAppointmentCommandParserTest {
    private SetAppointmentCommandParser parser = new SetAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(VALID_TITLE, VALID_START_TIME, VALID_END_TIME).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC + START_TIME_DESC + END_TIME_DESC,
                new SetAppointmentCommand(expectedAppointment));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no personToMeet
        Appointment expectedAppointment = new AppointmentBuilder(VALID_TITLE, VALID_START_TIME, VALID_END_TIME).build();
        assertParseSuccess(parser, TITLE_DESC + START_TIME_DESC + END_TIME_DESC,
                new SetAppointmentCommand((expectedAppointment)));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetAppointmentCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE + START_TIME_DESC + END_TIME_DESC,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, TITLE_DESC + VALID_START_TIME + END_TIME_DESC,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, TITLE_DESC + START_TIME_DESC + VALID_END_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE + VALID_START_TIME + VALID_END_TIME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + START_TIME_DESC + END_TIME_DESC,
                Title.MESSAGE_TITLE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, TITLE_DESC + INVALID_START_TIME_DESC + END_TIME_DESC,
                EventTime.MESSAGE_TIME_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, TITLE_DESC + START_TIME_DESC + INVALID_END_TIME_DESC,
                EventTime.MESSAGE_TIME_CONSTRAINTS);
    }
}
```
###### /java/seedu/address/logic/parser/SetTaskCommandParserTest.java
``` java
public class SetTaskCommandParserTest {
    private SetTaskCommandParser parser = new SetTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new Task(new Title(VALID_TITLE), new EventTime(VALID_END_TIME));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC + END_TIME_DESC,
                new SetTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTaskCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE + END_TIME_DESC,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, TITLE_DESC + VALID_END_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE + VALID_END_TIME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + END_TIME_DESC, Title.MESSAGE_TITLE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, TITLE_DESC + INVALID_END_TIME_DESC, EventTime.MESSAGE_TIME_CONSTRAINTS);
    }
}
```
###### /java/seedu/address/logic/parser/ListCommandParserTest.java
``` java
public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "contacts", new ListCommand(ListCommand.TYPE_CONTACT));
        assertParseSuccess(parser, "students", new ListCommand(ListCommand.TYPE_STUDENT));
        assertParseSuccess(parser, "tasks", new ListCommand(ListCommand.TYPE_TASK));
        assertParseSuccess(parser, "appointments", new ListCommand(ListCommand.TYPE_APPOINTMENT));
        assertParseSuccess(parser, "shortcuts", new ListCommand(ListCommand.TYPE_SHORTCUT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "ffffffd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "event",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
```
###### /java/seedu/address/logic/parser/AddressBookParserTest.java
``` java
    @Test
    public void parseCommand_toggleCalendarView() throws Exception {
        ToggleCalendarViewCommand command =
                (ToggleCalendarViewCommand) parser.parseCommand(ToggleCalendarViewCommand.COMMAND_WORD + " " + "m");
        assertEquals(new ToggleCalendarViewCommand('m'), command);
    }

    @Test
    public void parseCommand_setAppointment() throws Exception {
        SetAppointmentCommand command =
                (SetAppointmentCommand) parser.parseCommand(SetAppointmentCommand.COMMAND_WORD
                + TITLE_DESC + START_TIME_DESC + END_TIME_DESC);
        Appointment appointment = new AppointmentBuilder(VALID_TITLE, VALID_START_TIME, VALID_END_TIME).build();
        assertEquals(new SetAppointmentCommand(appointment), command);
    }

    @Test
    public void parseCommand_setTask() throws Exception {
        SetTaskCommand command =
                (SetTaskCommand) parser.parseCommand(SetTaskCommand.COMMAND_WORD + TITLE_DESC + END_TIME_DESC);
        Task task = new Task(new Title(VALID_TITLE), new EventTime(VALID_END_TIME));
        assertEquals(new SetTaskCommand(task), command);
    }

    @Test
    public void parseCommand_changeTheme() throws Exception {
        ChangeThemeCommand command =
                (ChangeThemeCommand) parser.parseCommand(ChangeThemeCommand.COMMAND_WORD + " " + "dark");
        assertEquals(new ChangeThemeCommand("dark"), command);
    }

    @Test
    public void parseCommand_remove() throws Exception {
        RemoveCommand commandRemoveAppointment =
                (RemoveCommand) parser.parseCommand(RemoveCommand.COMMAND_WORD + " " + "appointment" + " " + "1");
        RemoveCommand commandRemoveTask =
                (RemoveCommand) parser.parseCommand(RemoveCommand.COMMAND_WORD + " " + "task" + " " + "2");
        assertEquals(new RemoveCommand(Index.fromOneBased(1), "appointment"), commandRemoveAppointment);
        assertEquals(new RemoveCommand(Index.fromOneBased(2), "task"), commandRemoveTask);
    }
```
###### /java/seedu/address/logic/parser/ParserUtilTest.java
``` java
    @Test
    public void parseTitle_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((Optional<String>) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsIllegalValueException() {
        Assert.assertThrows(IllegalValueException.class, () -> ParserUtil.parseTitle(" "));
        Assert.assertThrows(IllegalValueException.class, () -> ParserUtil.parseTitle(Optional.of("  ")));
    }

    @Test
    public void parseTitle_optionalEmpty_returnsOptionalEmpty() throws Exception {
        assertFalse(ParserUtil.parseTitle(Optional.empty()).isPresent());
    }

    @Test
    public void parseTitle_validValue_returnsTitle() throws Exception {
        String validTitle = "Hanging out";
        Title expectedTitle = new Title(validTitle);
        assertEquals(expectedTitle, ParserUtil.parseTitle(validTitle));
        assertEquals(Optional.of(expectedTitle), ParserUtil.parseTitle(Optional.of(validTitle)));
    }

    @Test
    public void parseEventTime_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseEventTime((String) null));
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseEventTime((Optional<String>) null));
    }

    @Test
    public void parseEventTime_optionalEmpty_returnsOptionalEmpty() throws Exception {
        assertFalse(ParserUtil.parseEventTime(Optional.empty()).isPresent());
    }

    @Test
    public void parseEventTime_validValue_returnsEventTime() throws Exception {
        String validTime = "20/10/2018 10:00";
        EventTime expectedEventTime = new EventTime(validTime);
        assertEquals(expectedEventTime, ParserUtil.parseEventTime(validTime));
        assertEquals(Optional.of(expectedEventTime), ParserUtil.parseEventTime(Optional.of(validTime)));
    }

```
###### /java/seedu/address/logic/parser/ToggleCalendarViewParserTest.java
``` java
public class ToggleCalendarViewParserTest {
    private ToggleCalendarViewParser parser = new ToggleCalendarViewParser();

    @Test
    public void parse_validArgs_returnsToggleCalendarViewCommand() {
        assertParseSuccess(parser, "d", new ToggleCalendarViewCommand('d'));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "day",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ToggleCalendarViewCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "x",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ToggleCalendarViewCommand.MESSAGE_USAGE));
    }
}
```
###### /java/seedu/address/logic/commands/RemoveCommandTest.java
``` java

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Task;

/**
 * Contains Test for {@code RemoveCommand}
 */
public class RemoveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexRemoveAppointment_success() throws Exception {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Appointment appointmentToDelete = model.getFilteredAppointmentList().get(INDEX_FIRST.getZeroBased());
        RemoveCommand removeCommandRemovingAppointment = prepareCommand(INDEX_FIRST, "appointment");
        String expectedMessage =
                String.format(RemoveCommand.MESSAGE_DELETE_EVENT_SUCCESS, "appointment", appointmentToDelete);
        expectedModel.deleteAppointment(appointmentToDelete);
        assertCommandSuccess(removeCommandRemovingAppointment, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexRemoveTask_success() throws Exception {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        RemoveCommand removeCommandRemovingTask = prepareCommand(INDEX_FIRST, "task");
        String expectedMessage =
                String.format(RemoveCommand.MESSAGE_DELETE_EVENT_SUCCESS, "task", taskToDelete);
        expectedModel.deleteTask(taskToDelete);
        assertCommandSuccess(removeCommandRemovingTask, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() throws Exception {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        RemoveCommand removeCommandRemovingTask = prepareCommand(outOfBoundIndex, "task");

        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        RemoveCommand removeCommandRemovingAppointment = prepareCommand(outOfBoundIndex2, "appointment");

        assertCommandFailure(removeCommandRemovingTask, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        assertCommandFailure(removeCommandRemovingAppointment,
                model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() throws Exception {
        RemoveCommand removeCommandRemovingAppointment = prepareCommand(INDEX_FIRST, "appointment");
        RemoveCommand removeCommandRemovingTask = prepareCommand(INDEX_SECOND, "task");

        // same object -> returns true
        assertTrue(removeCommandRemovingAppointment.equals(removeCommandRemovingAppointment));

        // same values -> returns true
        RemoveCommand removeCommandRemovingAppointmentCopy = prepareCommand(INDEX_FIRST, "appointment");
        assertTrue(removeCommandRemovingAppointment.equals(removeCommandRemovingAppointmentCopy));

        // one command preprocessed when previously equal -> returns false
        removeCommandRemovingAppointmentCopy.preprocessUndoableCommand();
        assertFalse(removeCommandRemovingAppointment.equals(removeCommandRemovingAppointmentCopy));

        // different types -> returns false
        assertFalse(removeCommandRemovingAppointment.equals(1));

        // null -> returns false
        assertFalse(removeCommandRemovingAppointment.equals(null));

        // different person -> returns false
        assertFalse(removeCommandRemovingAppointment.equals(removeCommandRemovingTask));
    }

    /**
     * Returns a {@code RemoveCommand} with the parameter {@code index}, {@code eventType}.
     */
    private RemoveCommand prepareCommand(Index index, String eventType) {
        RemoveCommand removeCommand = new RemoveCommand(index, eventType);
        removeCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return removeCommand;
    }
}
```
###### /java/seedu/address/logic/commands/ToggleCalendarViewCommandTest.java
``` java
public class ToggleCalendarViewCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Test
    public void execute_help_success() throws CommandException {
        Character viewMode = 'd';
        CommandResult result = new ToggleCalendarViewCommand(viewMode).execute();
        assertEquals(MESSAGE_VIEW_TOGGLE_SUCCESS, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ToggleCalendarViewEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
```
###### /java/seedu/address/logic/commands/SetTaskCommandTest.java
``` java
public class SetTaskCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new SetTaskCommand(null);
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();

        CommandResult commandResult = getSetTaskCommand(TYPICAL_TASK_2, modelStub).execute();

        assertEquals(String.format(SetTaskCommand.MESSAGE_SUCCESS, TYPICAL_TASK_2), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(TYPICAL_TASK_2), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubThrowingDuplicateEventException();

        thrown.expect(CommandException.class);
        thrown.expectMessage(SetTaskCommand.MESSAGE_DUPLICATE_TASK);

        getSetTaskCommand(TYPICAL_TASK_1, modelStub).execute();
    }

    @Test
    public void equals() {
        SetTaskCommand addTask1 =
                new SetTaskCommand(TYPICAL_TASK_1);
        SetTaskCommand addTask2 =
                new SetTaskCommand(TYPICAL_TASK_2);

        // same object -> returns true
        assertTrue(addTask1.equals(addTask1));

        // same values -> returns true
        SetTaskCommand addAppointment1Copy =
                new SetTaskCommand(TYPICAL_TASK_1);
        assertTrue(addTask1.equals(addAppointment1Copy));

        // different types -> returns false
        assertFalse(addTask1.equals(1));

        // null -> returns false
        assertFalse(addTask1.equals(null));

        // different tasks -> returns false
        assertFalse(addTask1.equals(addTask2));
    }

    /**
     * Generates a new SetTaskCommand with the details of the given task.
     */
    private SetTaskCommand getSetTaskCommand(Task task, Model model) {
        SetTaskCommand command = new SetTaskCommand(task);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

}
```
###### /java/seedu/address/logic/commands/SetAppointmentCommandTest.java
``` java
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
```
###### /java/seedu/address/logic/commands/ChangeThemeCommandTest.java
``` java
public class ChangeThemeCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Test
    public void execute_help_success() throws CommandException {
        String theme = "dark";
        CommandResult result = new ChangeThemeCommand(theme).execute();
        assertEquals(MESSAGE_CHANGE_THEME_SUCCESS, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ThemeChangeEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
```
###### /java/seedu/address/storage/XmlAdaptedTaskTest.java
``` java
public class XmlAdaptedTaskTest {

    private static final String INVALID_TITLE = "  ";
    private static final String INVALID_TIME = "not a time stamp";

    @Test
    public void toModelType_validTaskDetails_returnsPerson() throws Exception {
        XmlAdaptedTask task = new XmlAdaptedTask(TYPICAL_TASK_1);
        assertEquals(TYPICAL_TASK_1, task.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(INVALID_TITLE, VALID_END_TIME);
        String expectedMessage = Title.MESSAGE_TITLE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(VALID_TITLE, INVALID_TIME);
        String expectedMessage = EventTime.MESSAGE_TIME_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(null, VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        XmlAdaptedTask task =
                new XmlAdaptedTask(VALID_TITLE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Time");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void equals() {
        XmlAdaptedTask task = new XmlAdaptedTask(TYPICAL_TASK_1);

        //same object
        assertTrue(task.equals(task));

        //same value
        XmlAdaptedTask taskCopy = new XmlAdaptedTask(TYPICAL_TASK_1);
        assertTrue(task.equals(taskCopy));

        //different type
        assertFalse(task.equals(TYPICAL_TASK_1));

        //different obj
        XmlAdaptedTask anotherTask = new XmlAdaptedTask(TYPICAL_TASK_2);
        assertFalse(task.equals(anotherTask));
    }
}
```
###### /java/seedu/address/storage/XmlAdaptedAppointmentTest.java
``` java
public class XmlAdaptedAppointmentTest {

    private static final String INVALID_TITLE = "  ";
    private static final String VALID_PERSON_TO_MEET = "Alice Email: alice@gmail.com";
    private static final String INVALID_TIME = "not a time stamp";

    @Test
    public void toModelType_validAppointmentDetails_returnsPerson() throws Exception {
        XmlAdaptedAppointment appointment = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_1);
        assertEquals(TYPICAL_APPOINTMENT_1, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(INVALID_TITLE, VALID_START_TIME, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = Title.MESSAGE_TITLE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, INVALID_TIME, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = EventTime.MESSAGE_TIME_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStartEndTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, VALID_START_TIME, INVALID_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = EventTime.MESSAGE_TIME_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(null, VALID_START_TIME, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, null, VALID_END_TIME, VALID_PERSON_TO_MEET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Start Time");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, VALID_START_TIME, null, VALID_PERSON_TO_MEET);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "End Time");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidTimePeriod_throwsIllegalValueException() {
        XmlAdaptedAppointment appointment =
                new XmlAdaptedAppointment(VALID_TITLE, "20/10/2018 10:00", "20/10/2018 09:00");
        String expectedMessage = Appointment.MESSAGE_TIME_PERIOD_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void equals() {
        XmlAdaptedAppointment appointment = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_1);

        //same object
        assertTrue(appointment.equals(appointment));

        //same value
        XmlAdaptedAppointment appointmentCopy = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_1);
        assertTrue(appointment.equals(appointmentCopy));

        //different type
        assertFalse(appointment.equals(TYPICAL_APPOINTMENT_1));

        //different obj
        XmlAdaptedAppointment anotherAppointment = new XmlAdaptedAppointment(TYPICAL_APPOINTMENT_2);
        assertFalse(appointment.equals(anotherAppointment));
    }
}
```
###### /java/seedu/address/model/event/AppointmentTest.java
``` java
public class AppointmentTest {
    private static final Title VALID_TITLE = new Title("Meet Student");
    private static final EventTime VALID_START_TIME = new EventTime("05/04/2018 10:00");
    private static final EventTime VALID_END_TIME = new EventTime("05/04/2018 11:00");
    private static final EventTime INVALID_END_TIME = new EventTime("05/04/2018 09:00");

    @Test
    public void constructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Appointment(VALID_TITLE, VALID_START_TIME, INVALID_END_TIME));
    }

    @Test
    public void isValidTime() {
        // invalid time stamps
        assertFalse(Appointment.isValidTime(VALID_START_TIME, INVALID_END_TIME)); //End time is before Start Time

        // valid time stamps
        assertTrue(Appointment.isValidTime(VALID_START_TIME, VALID_END_TIME));
    }
}
```
###### /java/seedu/address/testutil/modelstub/ModelStubThrowingDuplicateEventException.java
``` java
/**
 * A Model stub that always throw a DuplicateEventException when trying to add an appointment/task.
 */
public class ModelStubThrowingDuplicateEventException extends ModelStub {
    @Override
    public void addAppointment (Appointment appointment) throws DuplicateEventException {
        throw new DuplicateEventException();
    }

    @Override
    public void addTask (Task task) throws DuplicateEventException {
        throw new DuplicateEventException();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
```
###### /java/seedu/address/testutil/modelstub/ModelStubAcceptingTaskAdded.java
``` java
/**
 * A Model stub that always accept the task being added.
 */
public class ModelStubAcceptingTaskAdded extends ModelStub {
    public final ArrayList<Task> tasksAdded = new ArrayList<>();

    @Override
    public void addTask(Task event) throws DuplicateEventException {
        requireNonNull(event);
        tasksAdded.add(event);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
```
###### /java/seedu/address/testutil/AppointmentBuilder.java
``` java
/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    private Title title;
    private EventTime time;
    private EventTime endTime;
    private PersonToMeet personToMeet;

    public AppointmentBuilder(String title, String time, String endTime) {
        this(title, time, endTime, (String) null);
    }

    public AppointmentBuilder(String title, String time, String endTime, Person personToMeet) {
        this(title, time, endTime, personToMeet.getName() + EMAIL_SPLITTER + personToMeet.getEmail());
    }

    public AppointmentBuilder(String title, String time, String endTime, String personToMeet) {
        this.title = new Title(title);
        this.time = new EventTime(time);
        this.endTime = new EventTime(endTime);
        if (personToMeet != null) {
            String[] components = personToMeet.split(EMAIL_SPLITTER);
            this.personToMeet = new PersonToMeet(components[0], components[1]);
        }
    }

    /**
     * @return an {@code Appointment} from the data feed to constructor
     */
    public Appointment build() {
        return new Appointment(title, time, endTime, personToMeet);
    }
}
```
###### /java/seedu/address/testutil/TypicalEvents.java
``` java
/**
 * A utility class containing a list of event objects to be used in tests.
 */
public class TypicalEvents {
    public static final Appointment TYPICAL_APPOINTMENT_1 =
            new AppointmentBuilder("Meeting with parents", "09/10/2018 10:00", "09/10/2018 11:00", ALICE).build();
    public static final Appointment TYPICAL_APPOINTMENT_2 =
            new AppointmentBuilder("Consultation session", "04/07/2018 10:00", "04/07/2018 11:00", BOB).build();
    public static final Appointment TYPICAL_APPOINTMENT_3 =
            new AppointmentBuilder("Tutoring session", "30/04/2018 10:00", "30/04/2018 11:00", CARL).build();
    public static final Appointment APPOINTMENT_WITHOUT_PERSON_1 =
            new AppointmentBuilder("Meeting with parents", "09/10/2018 10:00", "09/10/2018 11:00").build();
    public static final Appointment APPOINTMENT_WITHOUT_PERSON_2 =
            new AppointmentBuilder("Consultation session", "04/07/2018 10:00", "04/07/2018 11:00", BOB).build();
    public static final Appointment APPOINTMENT_WITHOUT_PERSON_3 =
            new AppointmentBuilder("Tutoring session", "30/04/2018 10:00", "30/04/2018 11:00").build();


    public static final Task TYPICAL_TASK_1 =
            new Task(new Title("To do"), new EventTime("10/10/2018 10:00"));
    public static final Task TYPICAL_TASK_2 =
            new Task(new Title("Mark papers"), new EventTime("15/04/2018 23:00"));
    public static final Task TYPICAL_TASK_3 =
            new Task(new Title("Purchase markers"), new EventTime("19/04/2018 10:00"));
    public static final Task TYPICAL_TASK_EXPIRED =
            new Task(new Title("Expired task"), new EventTime("19/04/2013 10:00"));

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(TYPICAL_APPOINTMENT_1, TYPICAL_APPOINTMENT_2));
    }
    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TYPICAL_TASK_1, TYPICAL_TASK_2));
    }
}
```
