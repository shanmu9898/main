# shanmu9898
###### /resources/view/ShortcutListCard.fxml
``` fxml
<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.ColumnConstraints?>
<?import javafx.scene.layout.GridPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.Region?>
<?import javafx.scene.layout.VBox?>

<HBox id="cardPane" fx:id="cardPane" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
    <GridPane HBox.hgrow="ALWAYS">
        <columnConstraints>
            <ColumnConstraints hgrow="SOMETIMES" minWidth="10" prefWidth="150" />
        </columnConstraints>
        <VBox alignment="CENTER_LEFT" minHeight="80" GridPane.columnIndex="0">
            <padding>
                <Insets top="5" right="5" bottom="5" left="15" />
            </padding>
            <HBox spacing="5" alignment="CENTER_LEFT">
                <Label fx:id="id" styleClass="cell_big_label">
                    <minWidth>
                        <!-- Ensures that the label text is never truncated -->
                        <Region fx:constant="USE_PREF_SIZE" />
                    </minWidth>
                </Label>
                <Label fx:id="command" text="\$command" styleClass="cell_big_label" />
                <Label fx:id="shortcut" styleClass="cell_big_label_label" text="\$shortcut" />
            </HBox>

        </VBox>
    </GridPane>
</HBox>
```
###### /resources/view/ShortcutListPanel.fxml
``` fxml
<?import javafx.scene.control.ListView?>
<?import javafx.scene.layout.VBox?>

<VBox xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
    <ListView fx:id="shortcutListView" VBox.vgrow="ALWAYS" />
</VBox>
```
###### /java/seedu/address/ui/ShortcutListPanel.java
``` java
package seedu.address.ui;

import java.util.logging.Logger;

import org.fxmisc.easybind.EasyBind;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * Panel containing the list of Shortcuts.
 */
public class ShortcutListPanel extends  UiPart<Region> {
    private static final String FXML = "ShortcutListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<ShortcutCard> shortcutListView;

    public ShortcutListPanel(ObservableList<ShortcutDoubles> shortcutList) {
        super(FXML);
        setConnections(shortcutList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<ShortcutDoubles> shortcutList) {
        ObservableList<ShortcutCard> mappedList = EasyBind.map(shortcutList, (shortcutDoubles) ->
                new ShortcutCard(shortcutDoubles, shortcutList.indexOf(shortcutDoubles) + 1));
        shortcutListView.setItems(mappedList);
        shortcutListView.setCellFactory(listView -> new ShortcutListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code AppointmentCard}.
     */
    class ShortcutListViewCell extends ListCell<ShortcutCard> {

        @Override
        protected void updateItem(ShortcutCard shortcutCard, boolean empty) {
            super.updateItem(shortcutCard, empty);

            if (empty || shortcutCard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(shortcutCard.getRoot());
            }
        }
    }
}
```
###### /java/seedu/address/logic/Logic.java
``` java
    /** Returns an unmodifiable view of the filtered list of Shortcuts */
    ObservableList<ShortcutDoubles> getFilteredShortcutList();

```
###### /java/seedu/address/logic/parser/DeleteShortcutCommandParser.java
``` java
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteShortcutCommand object
 */
public class DeleteShortcutCommandParser implements Parser<DeleteShortcutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteShortcutCommand
     * and returns a DeleteShortcutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteShortcutCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] splitWords = trimmedArgs.split(" ");
        if (splitWords.length > 2 || splitWords.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteShortcutCommand.MESSAGE_USAGE));
        } else {
            return new DeleteShortcutCommand(splitWords[0], splitWords[1]);
        }
    }
}
```
###### /java/seedu/address/logic/parser/ImportCommandParser.java
``` java
/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }

        String[] parameterGetterArray = trimmedArgs.split(" ");

        if (parameterGetterArray.length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        } else {
            return new ImportCommand(parameterGetterArray[0]);
        }

    }



}
```
###### /java/seedu/address/logic/parser/ExportCommandParser.java
``` java
/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PATH, PREFIX_TYPE);
        String[] preambleArgs = argMultiMap.getPreamble().split(" ");

        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_PATH, PREFIX_TYPE)
                || preambleArgs.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        args.trim();
        String[] splitwords = args.split(" ");
        if (splitwords[1].equalsIgnoreCase("classes")) {
            argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PATH, PREFIX_TYPE);
            preambleArgs = argMultiMap.getPreamble().split(" ");

            if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_PATH, PREFIX_TYPE)
                    || preambleArgs.length > 1) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
            }

            String name = argMultiMap.getValue(PREFIX_NAME).orElse("");
            String path = argMultiMap.getValue(PREFIX_PATH).orElse("");
            String type = argMultiMap.getValue(PREFIX_TYPE).orElse("xml");

            if (!(type.equalsIgnoreCase("excel") || type.equalsIgnoreCase("xml"))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
            }

            return new ExportCommand(path, name, type);

        } else {
            argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RANGE,
                    PREFIX_TAG_EXPORT, PREFIX_PATH, PREFIX_TYPE);

            preambleArgs = argMultiMap.getPreamble().split(" ");
            if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_RANGE, PREFIX_PATH, PREFIX_TYPE)
                    || preambleArgs.length > 1) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
            }

            String name = argMultiMap.getValue(PREFIX_NAME).orElse("");
            String range = argMultiMap.getValue(PREFIX_RANGE).orElse("all");
            String tag = argMultiMap.getValue(PREFIX_TAG_EXPORT).orElse("shouldnotbethistag");
            String path = argMultiMap.getValue(PREFIX_PATH).orElse("");
            String type = argMultiMap.getValue(PREFIX_TYPE).orElse("xml");

            if (!(type.equalsIgnoreCase("excel") || type.equalsIgnoreCase("xml"))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
            }

            Tag tagExport = new Tag(tag);
            return new ExportCommand(range, tagExport, path, name, type);
        }

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }



}
```
###### /java/seedu/address/logic/parser/ShortcutCommandParser.java
``` java
/**
 * Parser
 */
public class ShortcutCommandParser implements Parser<ShortcutCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShortcutCommand
     * and returns an ShortcutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShortcutCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] splitWords = trimmedArgs.split(" ");
        if (splitWords.length > 2 || splitWords.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShortcutCommand.MESSAGE_USAGE));
        } else {
            return new ShortcutCommand(splitWords[0], splitWords[1]);
        }
    }
}
```
###### /java/seedu/address/logic/commands/DeleteShortcutCommand.java
``` java
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

/**
 * Deletes a specific shortcut from the addressbook.
 */
public class DeleteShortcutCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "delete_shortcut";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " CommandWord " + " ShortcutWord "
                                               + " :Deletes a shortcut for any command word";
    public static final String MESSAGE_DELETE_SHORTCUT_SUCCESS = "The shortcut has been deleted!";
    private final String shortcutWord;

    private final String commandWord;

    private ShortcutDoubles commandShortcut;


    public DeleteShortcutCommand(String commandWord, String shortcutWord) {
        requireNonNull(commandWord);
        requireNonNull(shortcutWord);
        this.commandWord = commandWord;
        this.shortcutWord = shortcutWord;
        commandShortcut = new ShortcutDoubles(shortcutWord, commandWord);
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(commandShortcut);
        try {
            model.deleteCommandShortcut(commandShortcut);
        } catch (UniqueShortcutDoublesList.CommandShortcutNotFoundException csnf) {
            throw new CommandException("Please enter a valid Shortcut Command you have saved");
        }

        return new CommandResult(String.format(MESSAGE_DELETE_SHORTCUT_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteShortcutCommand // instanceof handles nulls
                && this.shortcutWord.equals(((DeleteShortcutCommand) other).shortcutWord) // state check
                && this.commandWord.equals(((DeleteShortcutCommand) other).commandWord) // state check
                && Objects.equals(this.commandShortcut, ((DeleteShortcutCommand) other).commandShortcut));
    }
}
```
###### /java/seedu/address/logic/commands/ExportCommand.java
``` java
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_EXPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.education.Class;
import seedu.address.model.education.exceptions.DuplicateClassException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.XmlAddressBookStorage;


/**
 * Exports people to an XML file of choice based on tag, index or range
 */
public class ExportCommand extends Command {

    public static final String MESSAGE_FAIL = "TeachConnect faced some error while exporting! Please try again!";
    public static final String MESSAGE_OUT_OF_BOUNDS = "Please check the index bounds!";
    public static final String MESSAGE_SUCCESS = "Export Success!";
    public static final String MESSAGE_RANGE_ERROR = "Please input valid range";
    public static final String MESSAGE_TAG_CONTACT_MISMATCH = "The tag and contact don't match";

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": exports contacts to the TeachConnect Book based "
            + "on index, range or tag \n"
            + "Parameters: "
            + PREFIX_NAME + " NAME "
            + PREFIX_RANGE + " RANGE "
            + PREFIX_TAG_EXPORT + " TAG "
            + PREFIX_PATH + " PATH "
            + PREFIX_TYPE + "FORMAT \n"
            + "Example 1: " + COMMAND_WORD + " " + PREFIX_NAME + "{Name of file} " + PREFIX_RANGE + "all "
            + PREFIX_TAG_EXPORT + "friends " + PREFIX_PATH + "{Path to store} " + PREFIX_TYPE + "excel/xml \n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_NAME + "{Name of file} " + PREFIX_RANGE + "1 "
            + PREFIX_TAG_EXPORT + "friends " + PREFIX_PATH + "{Path to store} " + PREFIX_TYPE + "excel/xml \n"
            + "Example 3: " + COMMAND_WORD + " " + PREFIX_NAME + "{Name of file} " + PREFIX_RANGE + "1,2 "
            + PREFIX_TAG_EXPORT + "friends " + PREFIX_PATH + "{Path to store} " + PREFIX_TYPE + "excel/xml \n"
            + "Example 4: " + COMMAND_WORD + " classes " + PREFIX_NAME + "{Name of file} " + PREFIX_PATH
            + "{Path to store} " + PREFIX_TYPE + "excel/xml \n";


    private Tag tag;
    private final String range;
    private final String path;
    private AddressBook teachConnectBook;
    private AddressBookStorage teachConnectStorage;
    private final String nameOfExportFile;
    private final String type;
    private ArrayList<Person> exportPersonAddition = new ArrayList<Person>();
    private ArrayList<Class> exportClassAddition = new ArrayList<Class>();
    private ArrayList<Student> exportStudentAddition = new ArrayList<Student>();
    private boolean isClassesOrNot = false;

    /**
     * Creates an ExportCommand to export the specified {@code Persons}
     */
    public ExportCommand(String range, Tag tag, String path, String nameOfExportFile, String type) {
        requireNonNull(range);
        requireNonNull(tag);
        requireNonNull(path);
        requireNonNull(nameOfExportFile);
        requireNonNull(type);

        this.range = range;
        this.path = path;
        this.tag = tag;
        this.nameOfExportFile = nameOfExportFile;
        this.type = type;


        teachConnectBook = new AddressBook();
    }

    public ExportCommand(String path, String name, String type) {
        this.range = null;
        this.path = path;
        this.nameOfExportFile = name;
        isClassesOrNot = true;
        this.type = type;


        teachConnectBook = new AddressBook();
    }


    /**
     * Handles exceptions of various messages and takes care of the actual execution of the command.
     */
    @Override
    public CommandResult execute() throws CommandException {
        if (isClassesOrNot) {
            try {
                exportClasses();
            } catch (DuplicateClassException e) {
                return new CommandResult(MESSAGE_FAIL);
            } catch (DuplicatePersonException e) {
                return new CommandResult(MESSAGE_FAIL);
            }
        } else {
            String[] rangeGiven;
            CommandResult handledRangeSituation;

            try {
                rangeGiven = handleRange();
            } catch (IOException e) {
                return new CommandResult(MESSAGE_RANGE_ERROR);
            }

            try {
                handledRangeSituation = handleRangeArray(rangeGiven);
            } catch (DuplicatePersonException e) {
                return new CommandResult(MESSAGE_FAIL);
            } catch (IndexOutOfBoundsException e) {
                return new CommandResult(MESSAGE_OUT_OF_BOUNDS);
            }

            if (handledRangeSituation != null) {
                return handledRangeSituation;
            }

        }
        if (!tryStorage(type, isClassesOrNot)) {
            return new CommandResult(MESSAGE_FAIL);
        }
        return new CommandResult(MESSAGE_SUCCESS);

    }

    /**
     * Exports classes to an xml file
     */
    private void exportClasses() throws DuplicateClassException, DuplicatePersonException {
        ObservableList<Class> classes = model.getFilteredClassList();
        ObservableList<Student> students = model.getFilteredStudentsList();
        for (Class c : classes) {
            List<Name> studentNames = c.getStudents();
            addStudentsToList(students, studentNames);

        }
        for (Class c : classes) {
            exportClassAddition.add(c);
        }
        teachConnectBook.setClasses(exportClassAddition);
        teachConnectBook.setStudents(exportStudentAddition);

    }

    /**
     * Adds students into the list to add to the XML file later for supporting classes
     */
    private void addStudentsToList(ObservableList<Student> students, List<Name> studentNames) {
        for (Student s : students) {
            if (studentNames.contains(s.getName()) && !exportStudentAddition.contains(s)) {
                exportStudentAddition.add(s);
            }
        }
    }

    /**
     * This method saves the file either as an XML file or an CSV file depending on the user preferences.
     *
     * @return a boolean values if the storage has been possible or not
     */
    private boolean tryStorage(String type, boolean isClassesOrNot) throws CommandException {
        if (type.equalsIgnoreCase("xml")) {
            return saveAsXml();
        } else if (type.equalsIgnoreCase("excel")) {
            return saveAsCsv(isClassesOrNot);
        }
        return true;
    }

    /**
     * Tries to save the file as an XML file
     */
    private boolean saveAsXml() {
        teachConnectStorage = new XmlAddressBookStorage(path + "/" + nameOfExportFile + ".xml");
        try {
            teachConnectStorage.saveAddressBook(teachConnectBook);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Will save as a CSV file using a CSVPrinter including the list of tags
     *
     * @return boolean
     */
    private boolean saveAsCsv(boolean isClassesOrNot) throws CommandException {
        CSVPrinter csvPrinter;
        try {
            csvPrinter = csvFileToBeWritten(isClassesOrNot);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAIL));
        }

        if (isClassesOrNot) {
            saveAsCsvClasses(csvPrinter);
        } else {
            saveAsCsvPersons(csvPrinter);
        }

        try {
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Helper for a saving Persons in a CSV format
     *
     * @param csvPrinter
     * @throws CommandException
     */
    private void saveAsCsvPersons(CSVPrinter csvPrinter) throws CommandException {
        for (Person p : exportPersonAddition) {
            try {
                csvPrinter.printRecord(p.getName(), p.getEmail(), p.getPhone(), p.getAddress(), p.getTags());
            } catch (IOException e) {
                throw new CommandException(String.format(MESSAGE_FAIL));
            }
        }
    }

    /**
     * Helper for a saving Classes in a CSV format
     *
     * @param csvPrinter
     * @throws CommandException
     */
    private void saveAsCsvClasses(CSVPrinter csvPrinter) throws CommandException {
        try {
            for (Class c : exportClassAddition) {
                csvPrinter.printRecord(c.getName(), c.getSubject(), c.getStartDate(),
                        c.getEndDate(), c.getStudents());
            }
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAIL));
        }
    }


    /**
     * Returns CSVPrinter which is the file to which the contents are going to be added.
     */
    public CSVPrinter csvFileToBeWritten(boolean isClassesOrNot) throws IOException {
        CSVPrinter csvPrinter;

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(path + "/" + nameOfExportFile + ".csv"));
        if (!isClassesOrNot) {
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Email", "Phone",
                    "Address", "Tags"));
        } else {
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Subject", "Start Date",
                    "End Date", "Students"));
        }

        return csvPrinter;
    }


    /**
     * Handles the range array returned by the handleRange() function
     *
     * @param rangeGiven
     * @return
     */
    private CommandResult handleRangeArray(String[] rangeGiven) throws DuplicatePersonException,
            IndexOutOfBoundsException,
            CommandException {
        if (rangeGiven[0].equals("all")) {
            exportAllRange(tag);
        } else {
            if (exportGivenRange(rangeGiven)) {
                return new CommandResult(MESSAGE_RANGE_ERROR);
            }
        }
        return null;
    }

    /**
     * Exports a particular range considering all the edge constraints.
     */
    private boolean exportGivenRange(String[] rangeGiven) throws DuplicatePersonException, CommandException {
        if (rangeGiven.length != 1) {
            int low = Integer.parseInt(rangeGiven[0]);
            int high = Integer.parseInt(rangeGiven[1]);
            if (low >= high) {
                return true;
            } else {
                exportRange(low, high, tag);
            }

        } else {
            int low = Integer.parseInt(rangeGiven[0]);
            exportSpecific(low, tag);
        }
        return false;
    }

    /**
     * Adds a specific person/student to the teachConnectBook
     * parameters are an integer and a tag
     *
     * @throws DuplicatePersonException
     * @throws IndexOutOfBoundsException
     */
    private void exportSpecific(int low, Tag tag) throws DuplicatePersonException,
            IndexOutOfBoundsException,
            CommandException {
        ObservableList<Person> exportPeople = model.getFilteredPersonList();
        if (exportPeople.get(low - 1).getTags().contains(tag) || tag.equals(new Tag("shouldnotbethistag"))) {
            if (exportPeople.get(low - 1) instanceof Student) {
                exportStudentAddition.add((Student) exportPeople.get(low - 1));
                teachConnectBook.addStudent((Student) exportPeople.get(low - 1));
            } else {
                exportPersonAddition.add(exportPeople.get(low - 1));
                teachConnectBook.addPerson(exportPeople.get(low - 1));
            }

        } else {
            throw new CommandException(String.format(MESSAGE_TAG_CONTACT_MISMATCH));
        }

    }

    /**
     * Exports a range of people based on the tag and the index range given
     *
     * @throws DuplicatePersonException
     * @throws IndexOutOfBoundsException
     */
    private void exportRange(int low, int high, Tag tag) throws DuplicatePersonException, IndexOutOfBoundsException {
        ObservableList<Person> exportPeople = model.getFilteredPersonList();

        if (tag.equals(new Tag("shouldnotbethistag"))) {
            exportWithoutTag(low, high, exportPeople);
        } else {
            exportWithTag(low, high, tag, exportPeople);
        }

        teachConnectBook.setPersons(exportPersonAddition);
        teachConnectBook.setStudents(exportStudentAddition);
    }

    /**
     * Exports people or students when tag is present and range is given
     *
     * @param low  - lower range
     * @param high - higher range
     * @param tag  - tag given based on which people can be exported
     */
    private void exportWithTag(int low, int high, Tag tag, ObservableList<Person> exportPeople) {
        for (int i = low; i <= high; i++) {
            if (exportPeople.get(i - 1).getTags().contains(tag) && exportPeople.get(i - 1) instanceof Student) {
                exportStudentAddition.add((Student) exportPeople.get(i - 1));
            } else if (exportPeople.get(i - 1).getTags().contains(tag)) {
                exportPersonAddition.add(exportPeople.get(i - 1));
            }
        }
    }

    /**
     * Exports people or students when tag is not present and range is given
     *
     * @param low  - lower range
     * @param high - higher range
     */
    private void exportWithoutTag(int low, int high, ObservableList<Person> exportPeople) {
        for (int i = low; i <= high; i++) {
            if (exportPeople.get(i - 1) instanceof Student) {
                exportStudentAddition.add((Student) exportPeople.get(i - 1));
            } else {
                exportPersonAddition.add(exportPeople.get(i - 1));
            }
        }
    }

    /**
     * Exports all the contacts in the TeachConnect book if contain certain tag
     *
     * @throws DuplicatePersonException
     */
    private void exportAllRange(Tag tag) throws DuplicatePersonException {
        ObservableList<Person> exportPeople = model.getFilteredPersonList();
        if (tag.equals(new Tag("shouldnotbethistag"))) {
            exportEveryoneWithoutTag(exportPeople);
        } else {
            exportEveryoneWithTag(tag, exportPeople);
        }
        teachConnectBook.setPersons(exportPersonAddition);
        teachConnectBook.setStudents(exportStudentAddition);
    }

    /**
     * Export everyone with a tag given
     */
    private void exportEveryoneWithTag(Tag tag, ObservableList<Person> exportPeople) {
        for (Person p : exportPeople) {
            if (p.getTags().contains(tag) && p instanceof Student) {
                exportStudentAddition.add((Student) p);
            } else if (p.getTags().contains(tag)) {
                exportPersonAddition.add(p);
            }
        }
    }

    /**
     * Export everyone without a tag given
     */
    private void exportEveryoneWithoutTag(ObservableList<Person> exportPeople) {
        for (Person p : exportPeople) {
            if (p instanceof Student) {
                exportStudentAddition.add((Student) p);
            } else {
                exportPersonAddition.add(p);
            }
        }
    }


    /**
     * Helper method to identify the lower and higher end of the range given
     *
     * @return rangeStringArray
     */
    public String[] handleRange() throws IOException {
        String[] rangeStringArray = this.range.split(",");
        if (rangeStringArray.length > 2) {
            throw new IOException();
        }
        return rangeStringArray;

    }

    /**
     * @param other [in this case ExportCommand]
     * @return a boolean value
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand e = (ExportCommand) other;
        return range.equals(e.range) && path.equals(e.path);
    }

}
```
###### /java/seedu/address/logic/commands/ShortcutCommand.java
``` java
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;

/**
 *
 */
public class ShortcutCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "shortcut";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " CommandWord " + " ShortcutWord "
                                               + " :Creates a shortcut for any command word \n"
                                               + "Example: " + COMMAND_WORD + " list l";
    public static final String MESSAGE_SHORTCUT_AVAILABLE = "This shortcut already exists!";
    public static final String MESSAGE_SUCCESS = "Successfully added the shortcut";
    public static final String MESSAGE_NO_COMMAND_TO_MAP = "The command statement is invalid and hence cant be mapped!";

    private final String shortcutWord;

    private final String commandWord;

    private List<ShortcutDoubles> commandsList;

    private final String[] commandsPresent = {"add", "clear", "theme", "delete", "edit", "exit", "export", "find",
                                              "help", "history", "import", "list", "redo", "undo", "select",
                                              "set_appointment", "set_task", "shortcut", "undo", "calendar",
                                              "delete_shortcut", "remove", "form"};

    public ShortcutCommand(String commandWord, String shortcutWord) {
        requireNonNull(commandWord);
        requireNonNull(shortcutWord);
        this.shortcutWord = shortcutWord;
        this.commandWord = commandWord;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        commandsList = model.getFilteredCommandsList();
        if (commandsList != null) {
            if (checkIfCommandPresent()) {
                return new CommandResult(String.format(MESSAGE_SHORTCUT_AVAILABLE));
            }

        }

        ShortcutDoubles toAdd = new ShortcutDoubles(shortcutWord, commandWord);
        try {
            model.addCommandShortcut(toAdd);
        } catch (UniqueShortcutDoublesList.DuplicateShortcutDoublesException e) {
            return new CommandResult(String.format(MESSAGE_SHORTCUT_AVAILABLE));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    /**
     * Checks if the shortcut command is valid or not
     * @return whether true or false
     */
    private boolean checkIfCommandPresent() throws CommandException {
        if (!containsKeyWord(commandWord) || containsKeyWord(shortcutWord)) {
            throw new CommandException(MESSAGE_NO_COMMAND_TO_MAP);
        }
        for (ShortcutDoubles s : commandsList) {
            if (s.shortcutWord.equals(shortcutWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the command word is in the Array of commands present
     * @param commandWord
     * @return whether true if the command is present in the command word list or false otherwise
     */
    private boolean containsKeyWord(String commandWord) {
        for (String s : commandsPresent) {
            if (s.equals(commandWord)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShortcutCommand // instanceof handles nulls
                && this.shortcutWord.equals(((ShortcutCommand) other).shortcutWord) // state check
                && this.commandWord.equals(((ShortcutCommand) other).commandWord)); // state check
    }
}
```
###### /java/seedu/address/logic/commands/ImportCommand.java
``` java
/**
 * Imports contacts from a different TeachConnect XML file
 */
public class ImportCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": imports contacts to the address book."
            + "Parameters: file location...\n"
            + "Example: " + COMMAND_WORD + " main/src/test/data/sandbox/somerandomfile.xml";
    public static final String MESSAGE_SUCCESS = "%1$s contacts, %3$d students and %5$d classes \n"
            + " have been successfully imported \n"
            + "and %2$s contacts, %4$d students and %6$d classes have been left out!";
    protected static final String MESSAGE_INVALID_FILE = "Please input a valid file location";
    protected Storage storage;
    private AddressBook addressBookImported;
    private AddressBookStorage addressBookStorage;
    private String filePath;
    private int numberOfContactsAdded = 0;
    private int numberOfContactsNotAdded = 0;
    private int numberOfStudentsAdded = 0;
    private int numberOfStudentsNotAdded = 0;
    private int numberOfClassesAdded = 0;
    private int numberOfClassesNotAdded = 0;

    /**
     * Creates an ImportCommand to import the specified TeachConnect XML file
     */
    public ImportCommand(String importPath) {
        requireNonNull(importPath);
        this.filePath = importPath;
        addressBookStorage = new XmlAddressBookStorage(filePath);
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        ObservableList<Person> people;
        ObservableList<Student> students;
        List<Class> classes;
        try {
            if (addressBookStorage.readAddressBook(filePath).isPresent()) {
                this.addressBookImported = new AddressBook(addressBookStorage.readAddressBook().get());
                people = addressBookImported.getPersonList();
                students = addressBookImported.getStudentList();
                classes = addressBookImported.getClassList();
            } else {
                throw new CommandException(String.format(MESSAGE_INVALID_FILE));
            }

            peopleToBeImported(people);
            studentToBeImported(students);
            classesToBeImported(students, classes);

        } catch (DataConversionException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE));
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, numberOfContactsAdded, numberOfContactsNotAdded,
                numberOfStudentsAdded, numberOfStudentsNotAdded, numberOfClassesAdded, numberOfClassesNotAdded));
    }

    /**
     * Adds students and classes to the model because classes need students too.
     * @param students
     * @param classes
     */
    private void classesToBeImported(ObservableList<Student> students, List<Class> classes) {
        for (int i = 0; i < classes.size(); i++) {
            try {
                model.addClass(classes.get(i), students);
                numberOfClassesAdded++;
            } catch (DuplicateClassException e) {
                numberOfClassesNotAdded++;
            }
        }
    }

    /**
     * Adds students to the model
     * @param students
     */
    private void studentToBeImported(ObservableList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            try {
                model.addStudent(students.get(i));
                numberOfStudentsAdded++;
            } catch (DuplicatePersonException e) {
                numberOfStudentsNotAdded++;
            }
        }
    }

    /**
     * Adds people to the model
     * @param people
     */
    private void peopleToBeImported(ObservableList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            try {
                model.addPerson(people.get(i));
                numberOfContactsAdded++;
            } catch (DuplicatePersonException e) {
                numberOfContactsNotAdded++;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && filePath.equals(((ImportCommand) other).filePath));
    }


}




```
###### /java/seedu/address/storage/XmlAdaptedShortcutDouble.java
``` java
package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 *
 */
public class XmlAdaptedShortcutDouble {
    @XmlElement
    private String shortcutWord;
    @XmlElement
    private String commandWord;

    public XmlAdaptedShortcutDouble() {}

    public XmlAdaptedShortcutDouble(String shortcutWord, String commandWord) {
        this.shortcutWord = shortcutWord;
        this.commandWord = commandWord;
    }

    public XmlAdaptedShortcutDouble(ShortcutDoubles source) {
        shortcutWord = source.shortcutWord;
        commandWord = source.commandWord;
    }

    public ShortcutDoubles toModelType() throws IllegalValueException {
        return new ShortcutDoubles(shortcutWord, commandWord);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedShortcutDouble)) {
            return false;
        }

        return commandWord.equals(((XmlAdaptedShortcutDouble) other).commandWord)
                && shortcutWord.equals(((XmlAdaptedShortcutDouble) other).shortcutWord);
    }

}
```
###### /java/seedu/address/model/person/Student.java
``` java
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;
        return otherPerson.getName().equals(this.getName())
                && otherPerson.getPhone().equals(this.getPhone())
                && otherPerson.getEmail().equals(this.getEmail())
                && otherPerson.getAddress().equals(this.getAddress());
    }
}
```
###### /java/seedu/address/model/AddressBook.java
``` java
    public void setShorcutCommands(List<ShortcutDoubles> shorcutCommands) {
        this.shortcutCommands.setCommandsList(shorcutCommands);
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java
    public void addShortcutDoubles(ShortcutDoubles s)
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        shortcutCommands.add(s);
    }

    /**
     *
     * @param commandShortcut
     * @return a boolean variable
     * @throws UniqueShortcutDoublesList.CommandShortcutNotFoundException
     */
    public boolean removeShortcutDouble(ShortcutDoubles commandShortcut)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        if (shortcutCommands.remove(commandShortcut)) {
            return true;
        } else {
            throw new UniqueShortcutDoublesList.CommandShortcutNotFoundException();
        }
    }

    //// tag-level operations
```
###### /java/seedu/address/model/AddressBook.java
``` java
    /**
     * Removes the particular tag for all people in the AddressBook.
     */
    public void removeTag(Tag tag) throws DuplicatePersonException, PersonNotFoundException {
        for (Person person : persons) {
            removeTagFromPerson(tag, person);
        }
        for (Student student : students) {
            removeTagFromStudent(tag, student);
        }

    }

    /**
     * Removes all {@code Tag}s that are not used by any {@code Person} or {@code Student} in this {@code AddressBook}.
     */
    private void removeUnusedTags() {
        Set<Tag> tagsInContacts = contacts.asObservableList().stream().map(Person::getTags).flatMap(Set::stream)
                .collect(Collectors.toSet());

        tags.setTags(tagsInContacts);
    }

    /**
     * Removes the particular tag for that particular person in the AddressBook.
     */
    private void removeTagFromPerson(Tag tag, Person person) throws PersonNotFoundException, DuplicatePersonException {
        Set<Tag> listOfTags = new HashSet<>(person.getTags());

        if (listOfTags.contains(tag)) {
            listOfTags.remove(tag);
        } else {
            return;
        }

        Person updatedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                person.getAddress(), listOfTags);

        updatePerson(person, updatedPerson);
    }

```
###### /java/seedu/address/model/ModelManager.java
``` java
    @Override
    public synchronized void addCommandShortcut(ShortcutDoubles shortcutDoubles)
               throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        addressBook.addShortcutDoubles(shortcutDoubles);
        evokeToggleListEvent(LIST_TYPE_SHORTCUT);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void deleteCommandShortcut(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        addressBook.removeShortcutDouble(shortcutDoubles);
    }
```
###### /java/seedu/address/model/ModelManager.java
``` java
    @Override
    public ObservableList<ShortcutDoubles> getFilteredCommandsList() {
        return FXCollections.unmodifiableObservableList(filteredShortcutCommands);
    }

    @Override
    public ObservableList<Student> getFilteredStudentsList() {
        return FXCollections.unmodifiableObservableList(filteredStudents);
    }

```
###### /java/seedu/address/model/shortcuts/ShortcutDoubles.java
``` java
package seedu.address.model.shortcuts;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Command Double
 */
public class ShortcutDoubles {

    public final String shortcutWord;
    public final String commandWord;

    public ShortcutDoubles(String shortcutWord, String commandWord) {
        requireNonNull(shortcutWord);
        requireNonNull(commandWord);
        this.shortcutWord = shortcutWord;
        this.commandWord = commandWord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ShortcutDoubles)) {
            return false;
        }

        ShortcutDoubles otherShortcut = (ShortcutDoubles) other;
        return otherShortcut.commandWord.equals(this.commandWord)
                && otherShortcut.shortcutWord.equals(this.shortcutWord);
    }
}
```
###### /java/seedu/address/model/shortcuts/UniqueShortcutDoublesList.java
``` java
package seedu.address.model.shortcuts;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.util.CollectionUtil;

/**
 *
 */
public class UniqueShortcutDoublesList {

    private final ObservableList<ShortcutDoubles> internalList = FXCollections.observableArrayList();

    public UniqueShortcutDoublesList(){

    }

    /**
     * Adds Shortcut Doubles to the internal list
     * @param toAdd
     * @throws DuplicateShortcutDoublesException
     */
    public void add(ShortcutDoubles toAdd) throws DuplicateShortcutDoublesException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateShortcutDoublesException();
        }
        internalList.add(toAdd);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Returns an ObservableList of the internallist
     * @return
     */
    public ObservableList<ShortcutDoubles> asObservableList() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Gives a duplicate Except
     */
    public static class DuplicateShortcutDoublesException extends DuplicateDataException {
        protected DuplicateShortcutDoublesException() {
            super("Operation would result in duplicate Doubles");
        }
    }

    /**
     * Helps in checking if there are duplicates
     * @param toCheck
     * @return
     */
    public boolean contains(ShortcutDoubles toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    @Override
    public boolean equals(Object other) {
        assert CollectionUtil.elementsAreUnique(internalList);
        return other == this // short circuit if same object
                || (other instanceof UniqueShortcutDoublesList // instanceof handles nulls
                && this.internalList.equals(((UniqueShortcutDoublesList) other).internalList));
    }

    public void setCommandsList(List<ShortcutDoubles> commandsList) {
        requireNonNull(commandsList);
        internalList.setAll(commandsList);
        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Removes the equvivalent command shortcut from the list.
     * @param shortcutDoubles
     *
     * @throws UniqueShortcutDoublesList.CommandShortcutNotFoundException
     */
    public boolean remove(ShortcutDoubles shortcutDoubles)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        requireNonNull(shortcutDoubles);
        final boolean shortcutToBeDeleted = internalList.remove(shortcutDoubles);
        if (!shortcutToBeDeleted) {
            throw new UniqueShortcutDoublesList.CommandShortcutNotFoundException();
        }
        return shortcutToBeDeleted;
    }

    /**
     * Exception when the command shortcut is not present in the list of stored commands
     */
    public static class CommandShortcutNotFoundException extends Exception {}
}
```
