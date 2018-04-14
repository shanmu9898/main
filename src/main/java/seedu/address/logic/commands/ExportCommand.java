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
