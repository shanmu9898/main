//@@author shanmu9898
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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.XmlAddressBookStorage;


/**
 *
 * Exports people to an XML file of choice based on tag, index or range
 */
public class ExportCommand extends Command {

    public static final String MESSAGE_FAIL = "TeachConnect faced some error while exporting! Please try again!";
    public static final String MESSAGE_OUT_OF_BOUNDS = "Please check the index bounds!";
    public static final String MESSAGE_SUCCESS = "Contacts have been successfully exported!";
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
            + PREFIX_TAG_EXPORT + "friends " + PREFIX_PATH + "{Path to store} " + PREFIX_TYPE + "excel/xml \n";


    private Tag tag;
    private final String range;
    private final String path;
    private AddressBook teachConnectBook;
    private AddressBookStorage teachConnectStorage;
    private final String nameOfExportFile;
    private final String type;
    private ArrayList<Person> exportAddition = new ArrayList<Person>();

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

    /**
     * Handles exceptions of various messages and takes care of the actual execution of the command.
     */
    @Override
    public CommandResult execute() throws CommandException {
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

        if (!tryStorage(type)) {
            return new CommandResult(MESSAGE_FAIL);
        }

        return new CommandResult(MESSAGE_SUCCESS);

    }

    /**
     * This method saves the file either as an XML file or an CSV file depending on the user preferences.
     * @return a boolean values if the storage has been possible or not
     */
    private boolean tryStorage(String type) throws CommandException {
        if (type.equalsIgnoreCase("xml")) {
            teachConnectStorage = new XmlAddressBookStorage(path + "/" + nameOfExportFile + ".xml");
            try {
                teachConnectStorage.saveAddressBook(teachConnectBook);
            } catch (IOException e) {
                return false;
            }

        } else if (type.equalsIgnoreCase("excel")) {
            return saveAsCsv();
        }
        return true;
    }

    /**
     * Will save as a CSV file using a CSVPrinter including the list of tags
     * @return boolean
     */
    private boolean saveAsCsv() throws CommandException {
        CSVPrinter csvPrinter;
        try {
            csvPrinter = csvFileToBeWritten();
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAIL));
        }

        try {
            for (Person p : exportAddition) {
                csvPrinter.printRecord(p.getName(), p.getEmail(), p.getPhone(), p.getAddress(), p.getTags());
            }

            csvPrinter.flush();

        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAIL));
        }
        return true;
    }


    /**
     * Returns CSVPrinter which is the file to which the contents are going to be added.
     */
    public CSVPrinter csvFileToBeWritten() throws IOException {
        CSVPrinter csvPrinter;

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(path + "/" + nameOfExportFile + ".csv"));
        csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Email", "Phone", "Address", "Tags"));

        return csvPrinter;
    }


    /**
     * Handles the range array returned by the handleRange() function
     * @param rangeGiven
     * @return
     */
    private CommandResult handleRangeArray(String[] rangeGiven) throws DuplicatePersonException,
                                                                       IndexOutOfBoundsException,
                                                                       CommandException {
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
                exportSpecific(low, tag);
            }


        }
        return null;
    }

    /**
     * Adds a specific person to the teachConnectBook
     *
     * parameters are an integer and a tag
     * @throws DuplicatePersonException
     * @throws IndexOutOfBoundsException
     */
    private void exportSpecific(int low, Tag tag) throws DuplicatePersonException,
                                                         IndexOutOfBoundsException,
                                                         CommandException {
        ObservableList<Person> exportPeople = model.getFilteredPersonList();
        if (exportPeople.get(low - 1).getTags().contains(tag) || tag.equals(new Tag("shouldnotbethistag"))) {
            exportAddition.add(exportPeople.get(low - 1));
            teachConnectBook.addPerson(exportPeople.get(low - 1));
        } else {
            throw new CommandException(String.format(MESSAGE_TAG_CONTACT_MISMATCH));
        }

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
        exportAddition = new ArrayList<Person>();
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
            exportAddition = new ArrayList<Person>();
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
