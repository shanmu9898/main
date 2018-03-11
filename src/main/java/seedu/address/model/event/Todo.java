package seedu.address.model.event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//@@author Sisyphus25
/**
 * Represent a to-do item in the schedule, contains deadline as well as the title
 */
public class Todo implements Event {
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    private String title;
    private Date time;

    //Every field must be present and not null
    public Todo(String title, Calendar deadline) {
        this.title = title;
        this.time = deadline.getTime();
    }

    public String getTitle() {
        return title;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) other;
        return otherTodo.getTitle().equals(this.getTitle())
                && otherTodo.getTime().equals(this.getTime());
    }
}
