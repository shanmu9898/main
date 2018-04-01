package seedu.address.commons.events.model;

import javafx.collections.ObservableList;
import seedu.address.commons.events.BaseEvent;
import seedu.address.model.event.Appointment;

//@@author Sisyphus25
/**
 * Indicates the appointment list has changed
 */
public class AppointmentListChangedEvent extends BaseEvent {
    public final ObservableList<Appointment> appointmentList;

    public AppointmentListChangedEvent(ObservableList<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
