# randypx-reused
###### /java/seedu/address/ui/ClassListPanel.java
``` java
//Reuse from PersonListPanel class with modification
/**
 * Panel containing the list of classes.
 */
public class ClassListPanel extends UiPart<Region> {
    private static final String FXML = "ClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassListPanel.class);

    @FXML
    private ListView<ClassCard> classListView;

    public ClassListPanel(ObservableList<Class> classList) {
        super(FXML);
        setConnections(classList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Class> classList) {
        ObservableList<ClassCard> mappedList = EasyBind.map(classList, (group)
            -> new ClassCard(group, classList.indexOf(group) + 1));
        classListView.setItems(mappedList);
        classListView.setCellFactory(listView -> new ClassListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ClassCard}.
     */
    class ClassListViewCell extends ListCell<ClassCard> {

        @Override
        protected void updateItem(ClassCard classCard, boolean empty) {
            super.updateItem(classCard, empty);

            if (empty || classCard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(classCard.getRoot());
            }
        }
    }

}
```
###### /java/seedu/address/logic/Logic.java
``` java
    /** Returns an unmodifiable view of the filtered list of classes */
    ObservableList<Class> getFilteredClassList();

```
###### /java/seedu/address/logic/parser/ParserUtil.java
``` java
    /**
     * Parses a {@code Optional<String> time} into an {@code Optional<Time>} if {@code time} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Time> parseDate(Optional<String> date) throws IllegalArgumentException {
        requireNonNull(date);
        return date.isPresent() ? Optional.of(parseDate(date.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String time} into a {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Time parseDate(String date) throws IllegalArgumentException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        return new Time(trimmedDate, true);
    }

```
###### /java/seedu/address/model/person/UniqueStudentList.java
``` java
    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                && this.internalList.equals(((UniqueStudentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
```
###### /java/seedu/address/model/education/UniqueClassList.java
``` java
/**
 * A list of class that enforces uniqueness between its elements and does not allow nulls.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Class#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueClassList implements Iterable<Class> {

    private final ObservableList<Class> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent class as the given argument.
     */
    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a class to the list.
     *
     * @throws DuplicateClassException if the class to add is a duplicate of an existing class in the list.
     */
    public void add(Class toAdd) throws DuplicateClassException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the class {@code target} in the list with {@code editedClass}.
     *
     * @throws DuplicateClassException if the replacement is equivalent to another existing class in the list.
     * @throws StudentClassNotFoundException  if {@code target} could not be found in the list.
     */
    public void setClass(Class target, Class editedClass)
            throws DuplicateClassException, StudentClassNotFoundException {
        requireNonNull(editedClass);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentClassNotFoundException();
        }

        if (!target.equals(editedClass) && internalList.contains(editedClass)) {
            throw new DuplicateClassException();
        }

        internalList.set(index, editedClass);
    }

    /**
     * Removes the equivalent class from the list.
     *
     * @throws StudentClassNotFoundException if no such class could be found in the list.
     */
    public boolean remove(Class toRemove) throws StudentClassNotFoundException {
        requireNonNull(toRemove);
        final boolean classFoundAndDeleted = internalList.remove(toRemove);
        if (!classFoundAndDeleted) {
            throw new StudentClassNotFoundException();
        }
        return classFoundAndDeleted;
    }

    public void setClasses(UniqueClassList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setClasses(List<Class> classes) throws DuplicateClassException {
        requireAllNonNull(classes);
        final UniqueClassList replacement = new UniqueClassList();
        for (final Class group : classes) {
            replacement.add(group);
        }
        setClasses(replacement);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Class> asObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Class> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueClassList // instanceof handles nulls
                && this.internalList.equals(((UniqueClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
```
###### /java/seedu/address/model/AddressBook.java
``` java
    /**
     * Removes the particular tag for that particular student in the AddressBook.
     */
    private void removeTagFromStudent(Tag tag, Student student)
            throws PersonNotFoundException, DuplicatePersonException {
        Set<Tag> listOfTags = new HashSet<>(student.getTags());

        if (listOfTags.contains(tag)) {
            listOfTags.remove(tag);
        } else {
            return;
        }

        Student updatedStudent = new Student(student.getName(), student.getPhone(), student.getEmail(),
                student.getAddress(), listOfTags);

        updateStudent(student, updatedStudent);
    }

```
###### /java/seedu/address/model/AddressBook.java
``` java
    /**
     * Adds a class to the address book.
     *
     * @throws DuplicateClassException if an equivalent class already exists.
     */
    public void addClass(Class c) throws DuplicateClassException {
        classes.add(c);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws StudentClassNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removeClass(Class key) throws StudentClassNotFoundException {
        if (classes.remove(key)) {
            for (Student student: students) {
                if (student.isAttending(key)) {
                    student.exitClass(key);
                }
            }
            return true;
        } else {
            throw new StudentClassNotFoundException();
        }
    }

    //// util methods
```
###### /java/seedu/address/model/ModelManager.java
``` java
    @Override
    public ObservableList<Class> getFilteredClassList() {
        return FXCollections.unmodifiableObservableList(filteredClass);
    }

```
