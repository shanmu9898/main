package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * An Immutable AddressBook that is serializable to XML format
 */
@XmlRootElement(name = "addressbook")
public class XmlSerializableAddressBook {

    @XmlElement
    private List<XmlAdaptedPerson> persons;
    @XmlElement
    private List<XmlAdaptedStudent> students;
    @XmlElement
    private List<XmlAdaptedTag> tags;
    @XmlElement
    private List<XmlAdaptedAppointment> appointments;
    @XmlElement
    private List<XmlAdaptedTask> tasks;
    @XmlElement
    private List<XmlAdaptedShortcutDouble> commandsList;

    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableAddressBook() {
        persons = new ArrayList<>();
        students = new ArrayList<>();
        tags = new ArrayList<>();
        appointments = new ArrayList<>();
        tasks = new ArrayList<>();
        commandsList = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableAddressBook(ReadOnlyAddressBook src) {
        this();
        persons.addAll(src.getPersonList().stream().map(XmlAdaptedPerson::new).collect(Collectors.toList()));
        students.addAll(src.getStudentList().stream().map(XmlAdaptedStudent::new).collect(Collectors.toList()));
        tags.addAll(src.getTagList().stream().map(XmlAdaptedTag::new).collect(Collectors.toList()));
        appointments.addAll(src.getAppointmentList().stream().map(
                XmlAdaptedAppointment::new).collect(Collectors.toList()));
        tasks.addAll(src.getTaskList().stream().map(
                XmlAdaptedTask::new).collect(Collectors.toList()));
        commandsList.addAll(src.getCommandsList().stream().map(XmlAdaptedShortcutDouble::new)
                    .collect(Collectors.toList()));
    }

    /**
     * Converts this addressbook into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedPerson},{@code XmlAdaptedTag}, {@code XmlAdaptedAppointment}, {@code XmlAdaptedTask}.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (XmlAdaptedTag t : tags) {
            addressBook.addTag(t.toModelType());
        }
        for (XmlAdaptedPerson p : persons) {
            addressBook.addPerson(p.toModelType());
        }
        for (XmlAdaptedStudent s : students) {
            addressBook.addStudent(s.toModelType());
        }
        for (XmlAdaptedAppointment a: appointments) {
            addressBook.addAppointment(a.toModelType());
        }
        for (XmlAdaptedTask t: tasks) {
            addressBook.addTask(t.toModelType());
        }
        for (XmlAdaptedShortcutDouble s : commandsList) {
            addressBook.addShortcutDoubles(s.toModelType());
        }
        return addressBook;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableAddressBook)) {
            return false;
        }
        XmlSerializableAddressBook otherAb = (XmlSerializableAddressBook) other;
        return persons.equals(otherAb.persons)
                && students.equals(otherAb.students)
                && tags.equals(otherAb.tags)
                && appointments.equals(otherAb.appointments)
                && tasks.equals(otherAb.tasks)
                && commandsList.equals(otherAb.commandsList);
    }
}

