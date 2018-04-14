package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
        return new Task[] {
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

            for (ShortcutDoubles s : getSampleShortcutDoubles()) {
                sampleAb.addShortcutDoubles(s);
            }

            for (Appointment a : getSampleAppointment()) {
                sampleAb.addAppointment(a);
            }

            for (Task t : getSampleTask()) {
                sampleAb.addTask(t);
            }

            return sampleAb;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        } catch (UniqueShortcutDoublesList.DuplicateShortcutDoublesException e) {
            throw new AssertionError("sample data cannot contain duplicate command shortcuts", e);
        } catch (DuplicateEventException e) {
            throw new AssertionError("sample data cannot contain duplicate events", e);
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

    public static Set<ShortcutDoubles> getSampleShortcutDoublesTagSet(String... strings) {
        HashSet<ShortcutDoubles> shortcutDoubles = new HashSet<>();
        for (String s : strings) {
            shortcutDoubles.add(new ShortcutDoubles(s, s));
        }

        return shortcutDoubles;
    }

}
