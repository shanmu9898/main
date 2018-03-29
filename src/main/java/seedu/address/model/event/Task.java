package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

//@@author Sisyphus25
/**
 * Represent a Task in the schedule, contains deadline as well as the title
 */
public class Task {
    private Title title;
    private EventTime time;

    //Every field must be present and not null
    public Task(Title title, EventTime deadline) {
        requireAllNonNull(title, deadline);
        this.title = title;
        this.time = deadline;
    }

    public Title getTitle() {
        return title;
    }

    public EventTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(this.getTitle())
                && otherTask.getTime().equals(this.getTime());
    }

    @Override
    public String toString() {
        return title + ", Deadline: " + time;
    }
}
