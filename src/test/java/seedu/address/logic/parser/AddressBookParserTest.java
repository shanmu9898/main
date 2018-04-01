package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.ListCommand.COMMAND_WORD;
import static seedu.address.logic.commands.ListCommand.TYPE_APPOINTMENT;
import static seedu.address.logic.commands.ListCommand.TYPE_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_EXPORT;
import static seedu.address.testutil.ExportCommandBuilder.NAME_NEEDED;
import static seedu.address.testutil.ExportCommandBuilder.PATH_NEEDED;
import static seedu.address.testutil.ExportCommandBuilder.RANGE_ALL;
import static seedu.address.testutil.ExportCommandBuilder.TAG_NEEDED;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ChangeThemeCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SetAppointmentCommand;
import seedu.address.logic.commands.SetTaskCommand;
import seedu.address.logic.commands.ShortcutCommand;
import seedu.address.logic.commands.ToggleCalendarViewCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Task;
import seedu.address.model.event.Title;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getPersonDetails(person));
        assertEquals(new EditCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);

        try {
            parser.parseCommand("histories");
            fail("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(MESSAGE_UNKNOWN_COMMAND, pe.getMessage());
        }
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(COMMAND_WORD + " " + TYPE_CONTACT) instanceof ListCommand);
        assertTrue(parser.parseCommand(COMMAND_WORD + " " + TYPE_APPOINTMENT) instanceof ListCommand);
    }

    @Test
    public void parseCommand_select() throws Exception {
        SelectCommand command = (SelectCommand) parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new SelectCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_undoCommandWord_returnsUndoCommand() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand("undo 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parseCommand("");
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parseCommand("unknownCommand");
    }

    //@@author shanmu9898
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



    //@@author Sisyphus25
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
    //@@author


}
