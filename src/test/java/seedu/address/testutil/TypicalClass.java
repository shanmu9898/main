package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.STUDENT_ANGUS;
import static seedu.address.testutil.TypicalPersons.STUDENT_COOPER;
import static seedu.address.testutil.TypicalPersons.STUDENT_DAVID;
import static seedu.address.testutil.TypicalPersons.STUDENT_EMILY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.education.Class;

//@@author randypx
/**
 * A utility class containing a list of class objects to be used in tests.
 */
public class TypicalClass {

    private static final Class CLASS_MATH = new ClassBuilder().withName("math 101").withSubject("Mathematics")
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
