package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_EXPORT;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.XmlAddressBookStorage;

/**
 * @@author shanmu9898
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
            + " [TYPE] "
            + PREFIX_NAME + " NAME "
            + PREFIX_RANGE + " RANGE "
            + PREFIX_TAG_EXPORT + " TAG "
            + PREFIX_PATH + " PATH \n"
            + "Example 1: " + COMMAND_WORD + " " + PREFIX_NAME + "Exportfile " + PREFIX_RANGE + "all "
            + PREFIX_TAG_EXPORT + " friends " + PREFIX_PATH + "/src/main/data \n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_NAME + "Exportfile " + PREFIX_RANGE + "1 "
            + PREFIX_TAG_EXPORT + " friends " + PREFIX_PATH + "/src/main/data \n"
            + "Example 3: " + COMMAND_WORD + " " + PREFIX_NAME + "Exportfile " + PREFIX_RANGE + "1,2 "
            + PREFIX_TAG_EXPORT + " friends " + PREFIX_PATH + "/src/main/data";


    private Tag tag;
    private final String range;
    private final String path;
    private AddressBook teachConnectBook;
    private AddressBookStorage teachConnectStorage;
    private final String nameOfExportFile;

    /**
     * Creates an ExportCommand to export the specified {@code Persons}
     */
    public ExportCommand(String range, Tag tag, String path, String nameOfExportFile) {
        requireNonNull(range);
        requireNonNull(tag);
        requireNonNull(path);
        requireNonNull(nameOfExportFile);

        this.range = range;
        this.path = path;
        this.tag = tag;
        this.nameOfExportFile = nameOfExportFile;

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
        CommandResult handledRangeSituation = null;
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

        if (!tryStorage()) {
            return new CommandResult(MESSAGE_FAIL);
        }
        return new CommandResult(MESSAGE_SUCCESS);

    }

    /**
     * This method tries creating and storing the export file.
     * @return
     */
    private boolean tryStorage() {
        teachConnectStorage = new XmlAddressBookStorage(path + "/" + nameOfExportFile + ".xml");
        try {
            teachConnectStorage.saveAddressBook(teachConnectBook);
        } catch (IOException e) {
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

    /** - reused
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
