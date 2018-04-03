# Sisyphus25-reused
###### \java\guitests\guihandles\PersonCardHandle.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    /**
     *
     * @param tag Text value of the tag label
     * @return List of style classes for tag label with text value {@code tag}
     */
    public List<String> getTagStyleClasses(String tag) {
        return tagLabels
                .stream()
                .filter(label -> label.getText().equals(tag))
                .map(Label::getStyleClass)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such tag."));
    }
}
```
###### \java\seedu\address\ui\AppointmentCardTest.java
``` java
//Reuse with modification from PersonCardTest
public class AppointmentCardTest extends GuiUnitTest {

    @Test
    public void equals() {
        Appointment appointment = TYPICAL_APPOINTMENT_2;
        AppointmentCard appointmentCard = new AppointmentCard(appointment, 0);

        // same appointment, same index -> returns true
        AppointmentCard copy = new AppointmentCard(appointment, 0);
        assertTrue(appointmentCard.equals(copy));

        // same object -> returns true
        assertTrue(appointmentCard.equals(appointmentCard));

        // null -> returns false
        assertFalse(appointmentCard.equals(null));

        // different types -> returns false
        assertFalse(appointmentCard.equals(0));

        // different appointment, same index -> returns false
        Appointment differentAppointment = TYPICAL_APPOINTMENT_3;
        assertFalse(appointmentCard.equals(new AppointmentCard(differentAppointment, 0)));

        // same appointment, different index -> returns false
        assertFalse(appointmentCard.equals(new AppointmentCard(appointment, 1)));
    }

}
```
###### \java\seedu\address\ui\TaskCardTest.java
``` java
//Reuse with modification from PersonCardTest
public class TaskCardTest extends GuiUnitTest {

    @Test
    public void equals() {
        Task task = TYPICAL_TASK_2;
        TaskCard taskCard = new TaskCard(task, 0);

        // same task, same index -> returns true
        TaskCard copy = new TaskCard(task, 0);
        assertTrue(taskCard.equals(copy));

        // same object -> returns true
        assertTrue(taskCard.equals(taskCard));

        // null -> returns false
        assertFalse(taskCard.equals(null));

        // different types -> returns false
        assertFalse(taskCard.equals(0));

        // different task, same index -> returns false
        Task differentTask = TYPICAL_TASK_1;
        assertFalse(taskCard.equals(new TaskCard(differentTask, 0)));

        Task expiredTask = TYPICAL_TASK_EXPIRED;
        TaskCard expiredTaskCard = new TaskCard(TYPICAL_TASK_EXPIRED, 1);
        // same task, different index -> returns false
        assertFalse(taskCard.equals(expiredTaskCard));
    }
}
```
