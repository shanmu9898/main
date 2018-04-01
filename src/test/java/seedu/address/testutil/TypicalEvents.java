package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.event.Appointment;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Task;
import seedu.address.model.event.Title;

//@@author Sisyphus25
/**
 * A utility class containing a list of event objects to be used in tests.
 */
public class TypicalEvents {
    public static final Appointment TYPICAL_APPOINTMENT_1 =
            new AppointmentBuilder("Meeting with parents", "09/10/2018 10:00", "09/10/2018 11:00", ALICE).build();
    public static final Appointment TYPICAL_APPOINTMENT_2 =
            new AppointmentBuilder("Consultation session", "04/07/2018 10:00", "04/07/2018 11:00", BOB).build();
    public static final Appointment TYPICAL_APPOINTMENT_3 =
            new AppointmentBuilder("Tutoring session", "30/04/2018 10:00", "30/04/2018 11:00", CARL).build();
    public static final Appointment APPOINTMENT_WITHOUT_PERSON_1 =
            new AppointmentBuilder("Meeting with parents", "09/10/2018 10:00", "09/10/2018 11:00").build();
    public static final Appointment APPOINTMENT_WITHOUT_PERSON_2 =
            new AppointmentBuilder("Consultation session", "04/07/2018 10:00", "04/07/2018 11:00", BOB).build();
    public static final Appointment APPOINTMENT_WITHOUT_PERSON_3 =
            new AppointmentBuilder("Tutoring session", "30/04/2018 10:00", "30/04/2018 11:00").build();


    public static final Task TYPICAL_TASK_1 =
            new Task(new Title("To do"), new EventTime("10/10/2018 10:00"));
    public static final Task TYPICAL_TASK_2 =
            new Task(new Title("Mark papers"), new EventTime("15/04/2018 23:00"));
    public static final Task TYPICAL_TASK_3 =
            new Task(new Title("Purchase markers"), new EventTime("19/04/2018 10:00"));
    public static final Task TYPICAL_TASK_EXPIRED =
            new Task(new Title("Expired task"), new EventTime("19/04/2013 10:00"));

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(TYPICAL_APPOINTMENT_1, TYPICAL_APPOINTMENT_2));
    }
    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TYPICAL_TASK_1, TYPICAL_TASK_2));
    }
}
