package seedu.address.model.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.education.Class;
import seedu.address.model.education.Subject;
import seedu.address.model.education.exceptions.DuplicateClassException;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.PersonToMeet;
import seedu.address.model.event.Task;
import seedu.address.model.event.Time;
import seedu.address.model.event.Title;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.shortcuts.ShortcutDoubles;
import seedu.address.model.shortcuts.UniqueShortcutDoublesList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Student(new Name("Robert Leo"), new Phone("99384932"), new Email("robert@example.com"),
                    new Address("Blk 20 Clementi Road 9, #03-17"),
                    getTagSet("attention")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Student(new Name("Tony Tan"), new Phone("93928394"), new Email("tntan@example.com"),
                    new Address("Blk 24 Red Hill Street 44, #13-06"),
                    getTagSet("behind")),
            new Student(new Name("Carilynne Ng"), new Phone("99849343"), new Email("carlng@example.com"),
                    new Address("Blk 50 Clementi Road 3, #43-14"),
                    getTagSet("AStar")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("parent")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"))
        };
    }

    public static Student[] getSampleStudents() {
        return new Student[]{
            new Student(new Name("Jared Clover"), new Phone("9245262"), new Email("4thClover@example.com"),
                    new Address("Blk 333 Lorong 1 Simei, #01-07"), getTagSet("absent"),
                    getSubjectList("CS2103T", "CS2101")),
            new Student(new Name("Hadley Bolton"), new Phone("9236481"), new Email("hadley123@example.com"),
                    new Address("Blk 342 Yishun Street 86, #18-28"), getTagSet("enthusiastic"),
                    getSubjectList("CS2103T", "CS2010")),
            new Student(new Name("Ira Martel"), new Phone("92654727"), new Email("martel98@example.com"),
                    new Address("Blk 400 Lorong 8 Boon Keng, #07-19"), getTagSet("sick", "Fever"),
                    getSubjectList("CS2101")),
            new Student(new Name("Isaac Ellison"), new Phone("92467525"), new Email("newton@example.com"),
                    new Address("Blk 399 Toa Payoh Street 87, #05-06"), getTagSet(),
                    getSubjectList("CS2103T", "CS2101", "CS2010")),
            new Student(new Name("Riley Whittle"), new Phone("88523221"), new Email("whittley@example.com"),
                    new Address("Blk 410 Jurong West Street 88, #07-08"), getTagSet("new"))
        };
    }

    public static Class[] getSampleClass() {
        return new Class[]{
            new Class(new Name("Tutorial Class 02"), new Subject("CS2103T"),
                    new Time("01/01/2018", true),
                    new Time("28/05/2018", true),
                    getNameList("Jared Clover", "Hadley Bolton", "Isaac Ellison")),
            new Class(new Name("Sectional Teaching 02"), new Subject("CS2101"),
                    new Time("04/01/2018", true),
                    new Time("19/06/2018", true),
                    getNameList("Jared Clover", "Ira Martel", "Isaac Ellison")),
            new Class(new Name("Tutorial Class 01"), new Subject("CS2010"),
                    new Time("15/10/2018", true),
                    new Time("10/02/2019", true),
                    getNameList("Hadley Bolton", "Isaac Ellison"))
        };
    }

    public static ShortcutDoubles[] getSampleShortcutDoubles() {
        return new ShortcutDoubles[]{
            new ShortcutDoubles("a", "add"),
            new ShortcutDoubles("s", "shortcut")
        };
    }

    public static Appointment[] getSampleAppointment() {
        return new Appointment[]{
            new Appointment(new Title("Faculty Meeting"),
                        new Time("10/04/2018 15:00", false),
                        new Time("10/04/2018 18:00", false),
                        new PersonToMeet("Bernice Yu", "berniceyu@example.com")),
            new Appointment(new Title("Tutoring Session"),
                        new Time("19/04/2018 14:00", false),
                        new Time("19/04/2018 16:00", false),
                        new PersonToMeet("Tony Tan", "tntan@example.com")),
            new Appointment(new Title("Dinner with friends"),
                        new Time("19/04/2018 18:00", false),
                        new Time("19/04/2018 20:00", false)),
            new Appointment(new Title("Meet up with parents"),
                        new Time("20/04/2018 10:00", false),
                        new Time("20/04/2018 11:00", false),
                        new PersonToMeet("Irfan Ibrahim", "irfan@example.com")),
            new Appointment(new Title("Tutoring session"),
                        new Time("23/04/2018 13:00", false),
                        new Time("23/04/2018 15:00", false),
                        new PersonToMeet("Carilynne Ng", "carlng@example.com")),
            new Appointment(new Title("Faculty Meeting"),
                        new Time("25/04/2018 12:00", false),
                        new Time("25/04/2018 18:00", false))
        };
    }

    public static Task[] getSampleTask() {
        return new Task[]{
            new Task(new Title("Mark papers"), new Time("30/03/2018 18:00", false)),
            new Task(new Title("Collect documents"), new Time("11/04/2018 10:00", false)),
            new Task(new Title("Arrange tutor session"), new Time("18/04/2018 23:00", false)),
            new Task(new Title("Prepare documents for meeting"), new Time("22/04/2018 10:00", false)),
            new Task(new Title("Review final papers"), new Time("26/04/2018 22:00", false))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {

            AddressBook sampleAb = new AddressBook();
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }

            for (Student sampleStudent : getSampleStudents()) {
                sampleAb.addStudent(sampleStudent);
            }

            for (ShortcutDoubles s : getSampleShortcutDoubles()) {
                sampleAb.addShortcutDoubles(s);
            }

            for (Appointment a : getSampleAppointment()) {
                sampleAb.addAppointment(a);
            }

            for (Task t : getSampleTask()) {
                sampleAb.addTask(t);
            }

            for (Class sampleClass : getSampleClass()) {
                sampleAb.addClass(sampleClass);
            }

            return sampleAb;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate contactss", e);
        } catch (UniqueShortcutDoublesList.DuplicateShortcutDoublesException e) {
            throw new AssertionError("sample data cannot contain duplicate command shortcuts", e);
        } catch (DuplicateEventException e) {
            throw new AssertionError("sample data cannot contain duplicate events", e);
        } catch (DuplicateClassException e) {
            throw new AssertionError("smaple date cannot contain duplicate classes", e);
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        HashSet<Tag> tags = new HashSet<>();
        for (String s : strings) {
            tags.add(new Tag(s));
        }

        return tags;
    }

    //@@author randypx
    /**
     * Returns a subject list containing the list of strings given.
     */
    public static List<Subject> getSubjectList(String... strings) {
        ArrayList<Subject> subjectList = new ArrayList<>();
        for (String s : strings) {
            subjectList.add(new Subject(s));
        }
        return subjectList;
    }

    /**
     * Returns a student list containing the list of strings given.
     */
    public static List<Name> getStudentList(String... strings) {
        ArrayList<Name> studentList = new ArrayList<>();
        for (String s : strings) {
            studentList.add(new Name(s));
        }
        return studentList;
    }

    //@@author
        List<Subject> subjects = new ArrayList<>();
        for (String s : strings) {
            subjects.add(new Subject(s));
        }

        return subjects;
    }

    /**
     * Returns a name list containing the list of strings given.
     */
    public static List<Name> getNameList(String... strings) {
        List<Name> nameList = new ArrayList<>();
        for (String s : strings) {
            nameList.add(new Name(s));
        }

        return nameList;
    }

    public static Set<ShortcutDoubles> getSampleShortcutDoublesTagSet(String... strings) {
        HashSet<ShortcutDoubles> shortcutDoubles = new HashSet<>();
        for (String s : strings) {
            shortcutDoubles.add(new ShortcutDoubles(s, s));
        }

        return shortcutDoubles;
    }

}
