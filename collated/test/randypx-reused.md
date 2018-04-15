# randypx-reused
###### /java/seedu/address/storage/XmlAdaptedStudentTest.java
``` java
public class XmlAdaptedStudentTest {
    private static final String INVALID_NAME = "@l1ce";
    private static final String INVALID_PHONE = "+6582736";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG_NAME = "#absent";
    private static final String INVALID_TAG_COLOR_STYLE = "notacolor";
    private static final String INVALID_SUBJECT_SYMBOL = "Physic$";
    private static final String INVALID_SUBJECT_SPACE = "Advance Math";

    private static final String VALID_NAME = STUDENT_ANGUS.getName().toString();
    private static final String VALID_PHONE = STUDENT_ANGUS.getPhone().toString();
    private static final String VALID_EMAIL = STUDENT_ANGUS.getEmail().toString();
    private static final String VALID_ADDRESS = STUDENT_ANGUS.getAddress().toString();
    private static final List<XmlAdaptedTag> VALID_TAGS = STUDENT_ANGUS.getTags().stream()
            .map(XmlAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<String> VALID_SUBJECTS = STUDENT_ANGUS.getSubjectList().stream()
            .map(Subject::toString).collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        XmlAdaptedStudent student = new XmlAdaptedStudent(STUDENT_ANGUS);
        assertEquals(STUDENT_ANGUS, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        XmlAdaptedStudent student =
                new XmlAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        XmlAdaptedStudent student = new XmlAdaptedStudent(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        XmlAdaptedStudent student =
                new XmlAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = Phone.MESSAGE_PHONE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        XmlAdaptedStudent student = new XmlAdaptedStudent(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        XmlAdaptedStudent student =
                new XmlAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = Email.MESSAGE_EMAIL_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        XmlAdaptedStudent student = new XmlAdaptedStudent(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        XmlAdaptedStudent student =
                new XmlAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = Address.MESSAGE_ADDRESS_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        XmlAdaptedStudent student = new XmlAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_TAGS, VALID_SUBJECTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<XmlAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new XmlAdaptedTag(INVALID_TAG_NAME, INVALID_TAG_COLOR_STYLE));
        XmlAdaptedStudent student =
                new XmlAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, invalidTags, VALID_SUBJECTS);
        Assert.assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidSubjectWithSymbol_throwsIllegalArgumentException() {
        List<String> invalidSubjects = new ArrayList<>(VALID_SUBJECTS);
        invalidSubjects.add(INVALID_SUBJECT_SYMBOL);
        XmlAdaptedStudent student =
                new XmlAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, invalidSubjects);
        Assert.assertThrows(IllegalArgumentException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidSubjectWithSpace_throwsIllegalArguementException() {
        List<String> invalidSubjects = new ArrayList<>(VALID_SUBJECTS);
        invalidSubjects.add(INVALID_SUBJECT_SPACE);
        XmlAdaptedStudent student =
                new XmlAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, invalidSubjects);
        Assert.assertThrows(IllegalArgumentException.class, student::toModelType);
    }

    @Test
    public void equals() {
        XmlAdaptedStudent student = new XmlAdaptedStudent(STUDENT_ANGUS);

        //same object
        assertTrue(student.equals(student));

        //same value
        XmlAdaptedStudent studentCopy = new XmlAdaptedStudent(STUDENT_ANGUS);
        assertTrue(student.equals(studentCopy));

        //different type
        assertFalse(student.equals(1));

        //different obj
        XmlAdaptedStudent anotherStudent = new XmlAdaptedStudent(STUDENT_COOPER);
        assertFalse(student.equals(anotherStudent));
    }
}
```
