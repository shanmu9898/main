# randypx
###### /java/seedu/address/ui/ClassCard.java
``` java
/**
 * An UI component that displays information of a {@code Class}.
 */
public class ClassCard extends UiPart<Region> {

    private static final String FXML = "ClassListCard.fxml";
    private static final String DATE_FORMAT = "dd MMMMM yy";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public final Class classroom;

    @FXML
    private HBox cardPane;
    @FXML
    private Label className;
    @FXML
    private Label id;
    @FXML
    private Label subject;
    @FXML
    private Label duration;
    @FXML
    private Label students;

    public ClassCard(Class group, int displayedIndex) {
        super(FXML);
        this.classroom = group;
        id.setText(displayedIndex + "");
        className.setText(group.getName().fullName);
        subject.setText(group.getSubject().value);
        duration.setText("From " + DATE_FORMATTER.format(group.getStartDate().value.getTime()) + " to "
                + DATE_FORMATTER.format(group.getEndDate().value.getTime()));
        StringBuilder studentList = new StringBuilder();
        group.getStudents().forEach(student -> studentList.append(student.fullName).append("\n"));
        students.setText(studentList.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassCard)) {
            return false;
        }

        // state check
        ClassCard card = (ClassCard) other;
        return id.getText().equals(card.id.getText())
                && classroom.equals(card.classroom);
    }
}
```
###### /java/seedu/address/commons/events/model/ClassListChangedEvent.java
``` java
/**
 * Indicates the class list has changed due to the deletion/edit of a student.
 */
public class ClassListChangedEvent extends BaseEvent {

    public ClassListChangedEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
```
###### /java/seedu/address/commons/events/model/StudentListChangedEvent.java
``` java
/**
 * Indicates the student list has changed due to the addition/ deletion of a class.
 */
public class StudentListChangedEvent extends BaseEvent {

    public StudentListChangedEvent() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
```
###### /java/seedu/address/logic/parser/ParserUtil.java
``` java
    /**
     * Parses a {@code String subject} into a {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws IllegalValueException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new IllegalValueException(Subject.MESSAGE_SUBJECT_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
    }
}
```
###### /java/seedu/address/model/person/UniquePersonList.java
``` java
    /**
     * Add a listener to the list for any changes.
     * Update {@code contacts} for any changes made.
     */
    public void addListener(UniqueContactList contacts) {
        internalList.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                contacts.updateList(c);
            }
        });
    }

```
###### /java/seedu/address/model/person/UniqueContactList.java
``` java
/**
 * A list that is the aggregation of {@code UniquePersonList} and {@code UniqueStudentList}
 * and is the list displayed in the GUI.
 * This list remains up-to-date by listening to the changes of both lists and is not changed by anything else.
 */
public class UniqueContactList {
    private final UniquePersonList persons;
    private final UniqueStudentList students;
    private final ObservableList<Person> combinedList = FXCollections.observableArrayList();

    public UniqueContactList(UniquePersonList p, UniqueStudentList s) {
        persons = p;
        students = s;
        persons.addListener(this);
        students.addListener(this);
    }

    /**
     * This method is called when there is a change in eithor {@code UniquePersonList} or {@code UniqueStudentList}.
     * @param c this contains the change(s) that has occured.
     */
    public void updateList(ListChangeListener.Change<? extends Person> c) {
        while (c.next()) {
            if (c.wasReplaced()) {
                for (int i = 0; i < c.getRemovedSize(); i++) {
                    int index = combinedList.indexOf(c.getRemoved().get(i));
                    combinedList.set(index, c.getAddedSubList().get(i));
                }
                if (c.getTo() > c.getRemovedSize()) {
                    for (int i = c.getRemovedSize(); i < c.getTo(); i++) {
                        combinedList.add(c.getAddedSubList().get(i));
                    }
                }
            } else if (c.wasRemoved()) {
                combinedList.removeAll(c.getRemoved());
            } else if (c.wasAdded()) {
                combinedList.addAll(c.getAddedSubList());
            }
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asObservableList() {
        return FXCollections.unmodifiableObservableList(combinedList);
    }

}
```
###### /java/seedu/address/model/person/UniqueStudentList.java
``` java
    /**
     * Add a listener to the list for any changes.
     * Update {@code contacts} for any changes made.
     */
    public void addListener(UniqueContactList contacts) {
        internalList.addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(Change<? extends Student> c) {
                contacts.updateList(c);
            }
        });
    }

```
###### /java/seedu/address/model/person/Student.java
``` java
/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated.
 * Contact details are immutable.
 *
 * @see Person
 */
public class Student extends Person {

    private List<Subject> subjectList;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        subjectList = new ArrayList<>();
    }

    /**
     * Construct a new student with a filled subject list
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Subject> subjects) {
        super(name, phone, email, address, tags);
        subjectList = new ArrayList<>(subjects);
    }

    /**
     * Adds a new {@code Class} into the student's {@code classList}.
     */
    public void enterClass(Class newClass) {
        subjectList.add(newClass.getSubject());
    }

    /**
     * Removes a {@code Class} from the student's {@code classList}
     */
    public void exitClass(Class toExit) {
        subjectList.remove(toExit.getSubject());
    }

    /**
     * Returns the list of classes the student is attending.
     */
    public List<Subject> getSubjectList() {
        return subjectList;
    }

    /**
     * Checks if student is attending the given {@code class}.
     */
    public boolean isAttending(Class group) {
        return subjectList.contains(group.getSubject());
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Subjects: ");
        getSubjectList().forEach(builder::append);
        return builder.toString();
    }

```
###### /java/seedu/address/model/education/Subject.java
``` java
/**
 * Represents the subject being taught in a class.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {

    public static final String MESSAGE_SUBJECT_CONSTRAINTS =
            "Subject should only contain alphanumeric characters and should not be blank";

    /*
     * The first character of the subject must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String SUBJECT_VALIDATION_REGEX = "[\\p{Alnum}]*";

    public final String value;

    /**
     * Constructs an {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_SUBJECT_CONSTRAINTS);
        this.value = subject;
    }

    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(SUBJECT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && this.value.equals(((Subject) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
```
###### /java/seedu/address/model/education/Class.java
``` java
/**
 * Represents a class, of a particular subject, taught by the user, for a specified duration.
 * Also contains a list of student attending the class.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Class {

    private final Name className;
    private final Subject subject;
    private final Time startDate;
    private final Time endDate;
    private ArrayList<Name> attendingStudents;

    /**
     * Every field must be present and not null.
     * Guarantees: current time <= start time < end time
     */
    public Class (Name name, Subject subject, Time start, Time end, List<Name> studentList) {
        this.className = name;
        this.subject = subject;
        this.startDate = start;
        this.endDate = end;
        attendingStudents = new ArrayList<>(studentList);
    }

    public boolean hasConcluded() {
        return endDate.isExpired();
    }

    public Name getName() {
        return className;
    }

    public Subject getSubject() {
        return subject;
    }

    public Time getStartDate() {
        return startDate;
    }

    public Time getEndDate() {
        return endDate;
    }

    public List<Name> getStudents() {
        return attendingStudents;
    }

    /**
     * Removes a student from the list of students attending the class.
     */
    public void removeStudent(Student student) {
        Name studentName = student.getName();
        attendingStudents.remove(studentName);
    }

    /**
     * Adds a student to the list of students attending the class.
     */
    public void addStudent(Student student) {
        Name studentName = student.getName();
        attendingStudents.add(studentName);
    }

    /**
     * Checks if given {@code student} is attending this class.
     */
    public boolean containStudent(Student student) {
        return attendingStudents.contains(student.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Class // instanceof handles nulls
                && this.className.equals(((Class) other).className)
                && this.subject.equals(((Class) other).subject)
                && this.startDate.equals(((Class) other).startDate)
                && this.endDate.equals(((Class) other).endDate)
                && this.attendingStudents.equals(((Class) other).attendingStudents));
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, startDate, endDate, attendingStudents);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Subject: ")
                .append(getSubject())
                .append(" Start: ")
                .append(getStartDate())
                .append(" Address: ")
                .append(getEndDate())
                .append(" Students: ");
        getStudents().forEach(builder::append);
        return builder.toString();
    }
}
```
###### /java/seedu/address/model/AddressBook.java
``` java
    public void setClasses(List<Class> classes) throws DuplicateClassException {
        this.classes.setClasses(classes);
    }

```
###### /java/seedu/address/model/ModelManager.java
``` java
    /** Raises an event to indicate the change of list view */
    private void evokeToggleListEvent(String type) {
        changeCurrentActiveListType(type);
        raise(new ToggleListEvent(type));
    }
    /** Raises an event to indicate the student list has changed due to the addition/deletion of a class*/
    private void indicateStudentListChanged() {
        raise(new StudentListChangedEvent());
    }

    /** Raises an event to indicate the class list has changed due to the deletion/edit of a student*/
    private void indicateClassListChanged() {
        raise(new ClassListChangedEvent());
    }

```
