package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.storage.XmlAdaptedClass.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalClass.CLASS_CS2103T;
import static seedu.address.testutil.TypicalClass.CLASS_MATH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.education.Subject;
import seedu.address.model.event.Time;
import seedu.address.model.person.Name;
import seedu.address.testutil.Assert;

//@@author randypx
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
