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
###### /java/seedu/address/ui/ShortcutCard.java
``` java
package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * An UI component that displays information of a {@code Shortcut Double}
 */
public class ShortcutCard extends UiPart<Region> {

    private static final String FXML = "ShortcutListCard.fxml";

    public  final ShortcutDoubles shortcutDoubles;

    @FXML
    private HBox cardPane;
    @FXML
    private Label shortcut;
    @FXML
    private Label command;
    @FXML
    private Label id;

    public ShortcutCard(ShortcutDoubles shortcutDoubles, int displayedIndex) {
        super(FXML);

        this.shortcutDoubles = shortcutDoubles;
        id.setText(displayedIndex + ". ");
        shortcut.setText("===> " + shortcutDoubles.shortcutWord);
        command.setText(shortcutDoubles.commandWord);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShortcutCard)) {
            return false;
        }

        // state check
        ShortcutCard card = (ShortcutCard) other;
        return id.getText().equals(card.id.getText())
                && shortcutDoubles.equals(card.shortcutDoubles);
    }
}
```
###### /java/seedu/address/logic/Logic.java
``` java
    /** Returns an unmodifiable view of the filtered list of Shortcuts */
    ObservableList<ShortcutDoubles> getFilteredShortcutList();
}
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
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RANGE,
                PREFIX_TAG_EXPORT, PREFIX_PATH, PREFIX_TYPE);

        String[] preambleArgs = argMultiMap.getPreamble().split(" ");
        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_RANGE, PREFIX_PATH, PREFIX_TYPE)
                || preambleArgs.length > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        String name = argMultiMap.getValue(PREFIX_NAME).orElse("");
        String range = argMultiMap.getValue(PREFIX_RANGE).orElse("all");
        String tag = argMultiMap.getValue(PREFIX_TAG_EXPORT).orElse("shouldnotbethistag");
        String path = argMultiMap.getValue(PREFIX_PATH).orElse("");
        String type = argMultiMap.getValue(PREFIX_TYPE).orElse("normal");

        if (!(type.equals("excel") || type.equals("normal"))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        Tag tagExport = new Tag(tag);
        return new ExportCommand(range, tagExport, path, name, type);


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
/**
 *
 * Exports people to an XML file of choice based on tag, index or range
 */
public class ExportCommand extends Command {

    public static final String MESSAGE_FAIL = "TeachConnect faced some error while exporting! Please try again!";
    public static final String MESSAGE_OUT_OF_BOUNDS = "Please check the index bounds!";
    public static final String MESSAGE_SUCCESS = "Contacts have been successfully exported!";
    public static final String MESSAGE_RANGE_ERROR = "Please input valid range";

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
            + PREFIX_TAG_EXPORT + "friends " + PREFIX_PATH + "{Path to store} " + PREFIX_TYPE + "Excel/Normal \n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_NAME + "{Name of file} " + PREFIX_RANGE + "1 "
            + PREFIX_TAG_EXPORT + "friends " + PREFIX_PATH + "{Path to store} " + PREFIX_TYPE + "Excel/Normal \n"
            + "Example 3: " + COMMAND_WORD + " " + PREFIX_NAME + "{Name of file} " + PREFIX_RANGE + "1,2 "
            + PREFIX_TAG_EXPORT + "friends " + PREFIX_PATH + "{Path to store} " + PREFIX_TYPE + "Excel/normal \n";


    private Tag tag;
    private final String range;
    private final String path;
    private AddressBook teachConnectBook;
    private AddressBookStorage teachConnectStorage;
    private final String nameOfExportFile;
    private final String type;

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

    @Override
    public CommandResult execute() {
        String[] rangeGiven;
        try {
            rangeGiven = handleRange();
        } catch (IOException e) {
            return new CommandResult(MESSAGE_RANGE_ERROR);
        }
        CommandResult handledRangeSituation;
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

        if (!tryStorage(type)) {
            return new CommandResult(MESSAGE_FAIL);
        }
        return new CommandResult(MESSAGE_SUCCESS);

    }

    /**
     * This method tries creating and storing the export file.
     * @return
     */
    private boolean tryStorage(String type) {
        teachConnectStorage = new XmlAddressBookStorage(path + "/" + nameOfExportFile + ".xml");
        try {
            teachConnectStorage.saveAddressBook(teachConnectBook);
        } catch (IOException e) {
            return false;
        }
        if (type.equals("excel")) {
            return saveAsCsv();
        }
        return true;
    }

    /**
     * Will save as a CSV file depending on the type of input
     * @return boolean
     */
    private boolean saveAsCsv() {
        File stylesheet = new File("./src/main/resources/Util/style.xsl");
        File xmlSource = new File(path + "/" + nameOfExportFile + ".xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            return false;
        }
        Document document;
        try {
            document = builder.parse(xmlSource);
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        StreamSource styleSource = new StreamSource(stylesheet);
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer(styleSource);
        } catch (TransformerConfigurationException e) {
            return false;
        }
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(path + "/" + nameOfExportFile + ".csv"));
        try {
            transformer.transform(source, outputTarget);
        } catch (TransformerException e) {
            return false;
        }

        return true;
    }

    /**
     * Handles the range array returned by the handleRange() function
     * @param rangeGiven
     * @return
     */
    private CommandResult handleRangeArray(String[] rangeGiven) throws DuplicatePersonException,
                                                                       IndexOutOfBoundsException {
        if (rangeGiven[0].equals("all")) {
            exportAllRange(tag);
        } else {
            if (rangeGiven.length != 1) {
                for (int i = 0; i < rangeGiven.length; i++) {
                    int low = Integer.parseInt(rangeGiven[0]);
                    int high = Integer.parseInt(rangeGiven[1]);
                    if (low >= high) {
                        return new CommandResult(MESSAGE_RANGE_ERROR);
                    } else {
                        exportRange(low, high, tag);
                    }
                }
            } else {
                int low = Integer.parseInt(rangeGiven[0]);
                exportSpecific(low);
            }


        }
        return null;
    }

    /**
     * Adds a specific person to the teachConnectBook
     *
     * @param low
     * @throws DuplicatePersonException
     * @throws IndexOutOfBoundsException
     */
    private void exportSpecific(int low) throws DuplicatePersonException, IndexOutOfBoundsException {
        ObservableList<Person> exportPeople = model.getFilteredPersonList();
        teachConnectBook.addPerson(exportPeople.get(low - 1));
    }

    /**
     * Exports a range of people based on the tag and the index range given
     *
     * @param low
     * @param high
     * @param tag
     * @throws DuplicatePersonException
     * @throws IndexOutOfBoundsException
     */
    private void exportRange(int low, int high, Tag tag) throws DuplicatePersonException, IndexOutOfBoundsException {
        ObservableList<Person> exportPeople = model.getFilteredPersonList();
        ArrayList<Person> exportAddition = new ArrayList<Person>();
        if (tag.equals(new Tag("shouldnotbethistag"))) {
            for (int i = low; i < high; i++) {
                exportAddition.add(exportPeople.get(i - 1));
            }
            teachConnectBook.setPersons(exportAddition);
        } else {
            for (int i = low; i < high; i++) {
                if (exportPeople.get(i - 1).getTags().contains(tag)) {
                    exportAddition.add(exportPeople.get(i - 1));
                }

            }
        }

        teachConnectBook.setPersons(exportAddition);
    }

    /**
     * Exports all the contacts in the TeachConnect book if contain certain tag
     *
     * @param tag
     * @throws DuplicatePersonException
     */
    private void exportAllRange(Tag tag) throws DuplicatePersonException {
        ObservableList<Person> exportPeople = model.getFilteredPersonList();
        if (tag.equals(new Tag("shouldnotbethistag"))) {
            teachConnectBook.setPersons(exportPeople);
        } else {
            ArrayList<Person> exportAddition = new ArrayList<Person>();
            for (int i = 0; i < exportPeople.size(); i++) {
                if (exportPeople.get(i).getTags().contains(tag)) {
                    exportAddition.add(exportPeople.get(i));
                }
            }
            teachConnectBook.setPersons(exportAddition);
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
     *
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
                                               + " :Creates a shortcut for any command word";
    public static final String MESSAGE_SHORTCUT_AVAILABLE = "This shortcut already exists!";
    public static final String MESSAGE_SUCCESS = "Successfully added the shortcut";
    public static final String MESSAGE_NO_COMMAND_TO_MAP = "The command statement is invalid and hence cant be mapped!";

    private final String shortcutWord;

    private final String commandWord;

    private List<ShortcutDoubles> commandsList;

    private final String[] commandsPresent = {"add", "clear", "theme", "delete", "edit", "exit", "export", "find",
                                              "help", "history", "import", "list", "redo", "undo", "select",
                                              "set_appointment", "set_task", "shortcut", "undo", "calendar",
                                              "delete_shortcut", "remove"};

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
    public static final String MESSAGE_SUCCESS = "%1$s contacts have been successfully imported "
            + "and %2$s have been left out!";
    protected static final String MESSAGE_INVALID_FILE = "Please input a valid file location";
    protected Storage storage;
    private AddressBook addressBookImported;
    private AddressBookStorage addressBookStorage;
    private String filePath;
    private int numberAdded = 0;
    private int numberNotAdded = 0;

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
        try {
            if (addressBookStorage.readAddressBook(filePath).isPresent()) {
                this.addressBookImported = new AddressBook(addressBookStorage.readAddressBook().get());
                ObservableList<Person> people = addressBookImported.getPersonList();
                for (int i = 0; i < people.size(); i++) {
                    try {
                        model.addPerson(people.get(i));
                        numberAdded++;
                    } catch (DuplicatePersonException e) {
                        numberNotAdded++;
                    }
                }
            } else {
                throw new CommandException(String.format(MESSAGE_INVALID_FILE));
            }
        } catch (DataConversionException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE));
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, numberAdded, numberNotAdded));
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
###### /java/seedu/address/model/AddressBook.java
``` java
    public void setShorcutCommands(List<ShortcutDoubles> shorcutCommands) {
        this.shorcutCommands.setCommandsList(shorcutCommands);
    }
```
###### /java/seedu/address/model/AddressBook.java
``` java
    /**
     *
     * @param commandShortcut
     * @return a boolean variable
     * @throws UniqueShortcutDoublesList.CommandShortcutNotFoundException
     */
    public boolean removeShortcutDouble(ShortcutDoubles commandShortcut)
            throws UniqueShortcutDoublesList.CommandShortcutNotFoundException {
        if (shorcutCommands.remove(commandShortcut)) {
            return true;
        } else {
            throw new UniqueShortcutDoublesList.CommandShortcutNotFoundException();
        }
    }
    //author

    //// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }
```
###### /java/seedu/address/model/AddressBook.java
``` java
    public void addShortcutDoubles(ShortcutDoubles s)
            throws UniqueShortcutDoublesList.DuplicateShortcutDoublesException {
        shorcutCommands.add(s);
    }
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
