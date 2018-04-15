# randypx
###### /java/seedu/address/logic/parser/FormCommandParserTest.java
``` java
public class FormCommandParserTest {
    private FormCommandParser parser = new FormCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Subject expectedSubject = new Subject(VALID_SUBJECT_MATH);
        Name expectedClassName = new Name(VALID_CLASS_MATH);
        Time expectedStartDate = new Time(VALID_START_DATE, true);
        Time expectedEndDate = new Time(VALID_END_DATE, true);
        List<Index> expectedIndex =
                new ArrayList<>(Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2)));

        // subject preamble with whitespace
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + "  " + CLASS_MATH_DESC
                + "  " + START_DATE_DESC + "  " + END_DATE_DESC
                + "  " + PREFIX_INDEX + "1,2   ", new FormCommand(expectedClassName, expectedSubject,
                expectedStartDate, expectedEndDate, expectedIndex));

        // multiple names - last name accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_PHYS_DESC + CLASS_MATH_DESC
                        + START_DATE_DESC + END_DATE_DESC + " " + PREFIX_INDEX + "1,2",
                new FormCommand(expectedClassName, expectedSubject, expectedStartDate, expectedEndDate,
                        expectedIndex));

        // multiple start dates - last start date accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC
                + " " + PREFIX_START_TIME + VALID_DATE + START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", new FormCommand(expectedClassName,
                expectedSubject, expectedStartDate, expectedEndDate, expectedIndex));

        // multiple end date - last end date accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC
                + START_DATE_DESC + " " + PREFIX_END_TIME + VALID_DATE
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", new FormCommand(expectedClassName,
                expectedSubject, expectedStartDate, expectedEndDate, expectedIndex));

        // multiple index - last index accepted
        assertParseSuccess(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC
                        + START_DATE_DESC + " " + PREFIX_END_TIME + VALID_DATE
                        + END_DATE_DESC + " " + PREFIX_INDEX + "3,4" + " " + PREFIX_INDEX + "1,2",
                new FormCommand(expectedClassName, expectedSubject, expectedStartDate, expectedEndDate,
                        expectedIndex));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FormCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + VALID_CLASS_MATH + START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", expectedMessage);

        // missing start date prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + VALID_START_DATE
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", expectedMessage);

        // missing end date prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + START_DATE_DESC
                + VALID_END_DATE + " " + PREFIX_INDEX + "1,2", expectedMessage);

        // missing index prefix
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + START_DATE_DESC
                + END_DATE_DESC + " 1,2", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SUBJECT_MATH + VALID_CLASS_MATH + VALID_START_DATE
                + VALID_END_DATE + " 1,2", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + INVALID_CLASS_NAME_DESC
                + START_DATE_DESC + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", Name.MESSAGE_NAME_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + INVALID_START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "1,2", Time.MESSAGE_DATE_CONSTRAINTS);

        // invalid index
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + START_DATE_DESC
                + END_DATE_DESC + " " + PREFIX_INDEX + "a,b", MESSAGE_INVALID_INDEX);

        // invalid timeline
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + CLASS_MATH_DESC + " " + PREFIX_START_TIME
                        + VALID_END_DATE + " " + PREFIX_END_TIME + VALID_END_DATE + " " + PREFIX_INDEX + "1,2",
                Time.MESSAGE_TIME_PERIOD_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, PREAMBLE_SUBJECT_MATH + INVALID_CLASS_NAME_DESC
                        + START_DATE_DESC + INVALD_END_DATE_DESC + " " + PREFIX_INDEX + "1,2",
                Name.MESSAGE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_multiplePreamble_failure() {
        assertParseFailure(parser, PREAMBLE_SUBJECT_PHYS + PREAMBLE_SUBJECT_MATH
                        + VALID_CLASS_MATH + START_DATE_DESC + END_DATE_DESC + " " + PREFIX_INDEX + "1,2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FormCommand.MESSAGE_USAGE));

    }
}
```
###### /java/seedu/address/logic/commands/ListCommandTest.java
``` java
    @Test
    public void execute_listStudent_success() {
        listCommand = new ListCommand(TYPE_STUDENT);
        listCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        showPersonAtIndex(model, INDEX_FIRST);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ONLY_STUDENTS);
        assertCommandSuccess(listCommand, model, MESSAGE_SUCCESS + TYPE_STUDENT, expectedModel);
    }

```
###### /java/seedu/address/storage/XmlAdaptedClassTest.java
``` java
public class XmlAdaptedClassTest {
    private static final String INVALID_NAME = "cl@ss";
    private static final String INVALID_SUBJECT_SYMBOL = "Physic$";
    private static final String INVALID_SUBJECT_SPACE = "Advance Math";
    private static final String INVALID_DATE_SYMBOL = "10-10-2018";
    private static final String INVALID_DATE_CHARACTER = "10 October 2018";
    private static final String INVALID_DATE_NUMBER = "10/10/18";
    private static final String INVALID_STUDENT_NAME = "R@ndy";

    private static final String VALID_NAME = CLASS_CS2103T.getName().fullName;
    private static final String VALID_SUBJECT = CLASS_CS2103T.getSubject().toString();
    private static final String VALID_START_DATE = CLASS_CS2103T.getStartDate().toString();
    private static final String VALID_END_DATE = CLASS_CS2103T.getEndDate().toString();
    private static final List<String> VALID_STUDENT_NAMES = CLASS_CS2103T.getStudents().stream()
            .map(Name::toString).collect(Collectors.toList());

    @Test
    public void toModelType_validClassDetails_returnsClass() throws Exception {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(CLASS_CS2103T);
        assertEquals(CLASS_CS2103T, adaptedClass.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(INVALID_NAME, VALID_SUBJECT, VALID_START_DATE,
                        VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(null, VALID_SUBJECT, VALID_START_DATE,
                        VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_invalidSubjectWithSymbol_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, INVALID_SUBJECT_SYMBOL,
                VALID_START_DATE, VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = Subject.MESSAGE_SUBJECT_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_invalidSubjectWithSpace_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, INVALID_SUBJECT_SPACE,
                VALID_START_DATE, VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = Subject.MESSAGE_SUBJECT_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_nullSubject_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, null, VALID_START_DATE,
                VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_invalidDateWrongSymbol_throwsIllegalArgumentException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, VALID_SUBJECT, INVALID_DATE_SYMBOL,
                        VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = Time.MESSAGE_DATE_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_invalidDateNoCharacter_throwsIllegalArgumentException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, VALID_SUBJECT, INVALID_DATE_CHARACTER,
                        VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = Time.MESSAGE_DATE_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_invalidDateTooLittleDigits_throwsIllegalArgumentException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, VALID_SUBJECT, VALID_START_DATE,
                        INVALID_DATE_NUMBER, VALID_STUDENT_NAMES);
        String expectedMessage = Time.MESSAGE_DATE_CONSTRAINTS;
        Assert.assertThrows(IllegalArgumentException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_invalidDateEndBeforeStart_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, VALID_SUBJECT, VALID_END_DATE,
                        VALID_START_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = Time.MESSAGE_TIME_PERIOD_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, VALID_SUBJECT, null,
                VALID_END_DATE, VALID_STUDENT_NAMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, VALID_SUBJECT, VALID_START_DATE,
                null, VALID_STUDENT_NAMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, adaptedClass::toModelType);
    }

    @Test
    public void toModelType_invalidStudentNames_throwsIllegalArguementException() {
        List<String> invalidStudentNames = new ArrayList<>(VALID_STUDENT_NAMES);
        invalidStudentNames.add(INVALID_STUDENT_NAME);
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(VALID_NAME, VALID_SUBJECT, VALID_START_DATE,
                        VALID_END_DATE, invalidStudentNames);
        Assert.assertThrows(IllegalArgumentException.class, adaptedClass::toModelType);
    }

    @Test
    public void equals() {
        XmlAdaptedClass adaptedClass = new XmlAdaptedClass(CLASS_CS2103T);

        //same object
        assertTrue(adaptedClass.equals(adaptedClass));

        //same value
        XmlAdaptedClass adaptedClassCopy = new XmlAdaptedClass(CLASS_CS2103T);
        assertTrue(adaptedClass.equals(adaptedClassCopy));

        //different type
        assertFalse(adaptedClass.equals(1));

        //different obj
        XmlAdaptedClass anotherCLass = new XmlAdaptedClass(CLASS_MATH);
        assertFalse(adaptedClass.equals(anotherCLass));
    }
}
```
###### /java/seedu/address/model/ModelManagerTest.java
``` java
    @Test
    public void addStudent_addStudentToAddressBook_evokeAddressBookChangedEvent() throws DuplicatePersonException {
        ModelManager modelManager = new ModelManager(addressBook, userPrefs);
        modelManager.addStudent(STUDENT_FAUST);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }

    @Test
    public void addClass_addClassToAddressBook_evokeAddressBookChangedEvent() throws DuplicateClassException {
        ModelManager modelManager = new ModelManager(addressBook, userPrefs);
        modelManager.addClass(CLASS_CS2103T, new ArrayList<>(Collections.singletonList(STUDENT_COOPER)));
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }

    @Test
    public void removeClass_removeClassFromAddressBook_evokeAddressBookChangedEvent()
            throws StudentClassNotFoundException {
        ModelManager modelManager = new ModelManager(addressBook, userPrefs);
        modelManager.deleteClass(CLASS_MATH);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof AddressBookChangedEvent);
    }

```
###### /java/seedu/address/testutil/modelstub/ModelStub.java
``` java
    @Override
    public void addClass(Class group, List<Student> studentList) throws DuplicateClassException {
        fail("This method should not be called.");
    }

    @Override
    public void deleteClass(Class target) throws StudentClassNotFoundException {
        fail("This method should not be called.");
    }
}
```
###### /java/seedu/address/testutil/StudentBuilder.java
``` java
/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    private static final String DEFAULT_NAME = "Alice Pauline";
    private static final String DEFAULT_PHONE = "85355255";
    private static final String DEFAULT_EMAIL = "alice@gmail.com";
    private static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    private static final String DEFAULT_TAGS = "friends";
    private static final String DEFAULT_SUBJECT = "english";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<Subject> subjects;

    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = SampleDataUtil.getTagSet(DEFAULT_TAGS);
        subjects = SampleDataUtil.getSubjectList(DEFAULT_SUBJECT);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        subjects = new ArrayList<>(personToCopy.getSubjectList());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code subjects} into a {@code List<Subject>} and set it to the {@code Student}
     * that we are building.
     */
    public StudentBuilder withSubjects(String ... subjects) {
        this.subjects = SampleDataUtil.getSubjectList(subjects);
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, address, tags, subjects);
    }
}
```
###### /java/seedu/address/testutil/TypicalClass.java
``` java
/**
 * A utility class containing a list of class objects to be used in tests.
 */
public class TypicalClass {

    public static final Class CLASS_CS2103T = new ClassBuilder().withName("T2").withSubject("CS2103T")
            .withStartDate("14/04/2018").withEndDate("28/04/2018")
            .withStudents(STUDENT_COOPER.getName().fullName, STUDENT_DAVID.getName().fullName).build();
    public static final Class CLASS_MATH = new ClassBuilder().withName("math 101").withSubject("Mathematics")
            .withStartDate("30/08/2018").withEndDate("30/12/2019")
            .withStudents(STUDENT_COOPER.getName().fullName, STUDENT_DAVID.getName().fullName).build();
    private static final Class CLASS_BIOLOGY = new ClassBuilder().withName("Bio 01").withSubject("Biology")
            .withStartDate("05/12/2018").withEndDate("05/05/2019")
            .withStudents(STUDENT_ANGUS.getName().fullName, STUDENT_COOPER.getName().fullName,
                    STUDENT_EMILY.getName().fullName).build();
    private static final Class CLASS_PHYSICS = new ClassBuilder().withName("phys 03").withSubject("Physics")
            .withStartDate("04/07/2018").withEndDate("04/12/2018")
            .withStudents(STUDENT_EMILY.getName().fullName).build();

    public static List<Class> getTypicalClasses() {
        return new ArrayList<>(Arrays.asList(CLASS_MATH, CLASS_BIOLOGY, CLASS_PHYSICS));
    }
}
```
###### /java/seedu/address/testutil/ClassBuilder.java
``` java
/**
 * A utility class to help with building class objects.
 */
public class ClassBuilder {

    private static final String DEFAULT_NAME = "Class 01";
    private static final String DEFAULT_SUBJECT = "Math";
    private static final String DEFAULT_START_DATE = "10/10/2018";
    private static final String DEFAULT_END_DATE = "20/10/2019";
    private static final String DEFAULT_STUDENT = "Alice Pauline";

    private Name name;
    private Subject subject;
    private Time start;
    private Time end;
    private List<Name> studentNameList;

    public ClassBuilder() {
        name = new Name(DEFAULT_NAME);
        subject = new Subject(DEFAULT_SUBJECT);
        start = new Time(DEFAULT_START_DATE, true);
        end = new Time(DEFAULT_END_DATE, true);
        studentNameList = SampleDataUtil.getStudentList(DEFAULT_STUDENT);
    }

    /**
     * Initializes the ClassBuilder with the data of {@code classToCopy}.
     */
    public ClassBuilder(Class classToCopy) {
        name = classToCopy.getName();
        subject = classToCopy.getSubject();
        start = classToCopy.getStartDate();
        end = classToCopy.getEndDate();
        studentNameList = new ArrayList<>(classToCopy.getStudents());
    }

    /**
     * Sets the {@code Name} of the {@code Class} that we are building.
     */
    public ClassBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Class} that we are building.
     */
    public ClassBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Class} that we are building.
     */
    public ClassBuilder withEndDate(String endDate) {
        this.end = new Time(endDate, true);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Class} that we are building.
     */

    public ClassBuilder withStartDate(String startDate) {
        this.start = new Time(startDate, true);
        return this;
    }

    /**
     * Parses the {@code studentNames} into a {@code List<Name>} and set it to the {@code Class}
     * that we are building.
     */
    public ClassBuilder withStudents(String ... studentNames) {
        this.studentNameList = SampleDataUtil.getStudentList(studentNames);
        return this;
    }

    public Class build() {
        return new Class(name, subject, start, end, studentNameList);
    }
}
```
###### /java/seedu/address/testutil/TypicalPersons.java
``` java
    // Students
    public static final Student STUDENT_ANGUS = new StudentBuilder().withName("Angus Wyndham")
            .withPhone("9867723").withEmail("wynd@example.com").withAddress("Centre Street")
            .withSubjects("Biology").build();
    public static final Student STUDENT_BRUCE = new StudentBuilder().withName("Bruce Wayne")
            .withPhone("9575232").withEmail("star@example.com").withAddress("Hollywood")
            .withSubjects().build();
    public static final Student STUDENT_COOPER = new StudentBuilder().withName("Cooper Crouch")
            .withPhone("9247637").withEmail("freeman@example.com").withAddress("Kansas")
            .withSubjects("Mathematics", "Biology").build();
    public static final Student STUDENT_DAVID = new StudentBuilder().withName("David Gray")
            .withPhone("9234718").withEmail("alien@example.com").withAddress("Chinatown")
            .withSubjects("Mathematics").build();
    public static final Student STUDENT_EMILY = new StudentBuilder().withName("Emily Walter")
            .withPhone("8537425").withEmail("lily@example.com").withAddress("4th Avenue")
            .withSubjects("Biology", "Physics").build();

    // Manually added
    public static final Student STUDENT_FAUST = new StudentBuilder().withName("Faust Meier")
            .withPhone("9524284").withEmail("mephist@example.com").withAddress("raffles hall")
            .withTags("absent").withSubjects().build();
    public static final Student STUDENT_GUASS = new StudentBuilder().withName("Guass Muller")
            .withPhone("8824681").withEmail("greg@example.com").withAddress("university town")
            .withSubjects().build();
    public static final Student STUDENT_HELEN = new StudentBuilder().withName("Hellen Wetscott")
            .withPhone("8315264").withEmail("knight@example.com").withAddress("Sentosa Resort")
            .withSubjects().build();
    public static final Student STUDENT_ILLYA = new StudentBuilder().withName("Illya Einzbern")
            .withPhone("9275423").withEmail("berserker@example.com").withAddress("fuyuki")
            .withSubjects().build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student STUDENT_AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags().withSubjects().build();
    public static final Student STUDENT_BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_STUDENT)
            .withSubjects().build();

```
