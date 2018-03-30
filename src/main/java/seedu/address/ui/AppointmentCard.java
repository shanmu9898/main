
package seedu.address.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Appointment;

//@@author Sisyphus25
/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";
    private static final String DATE_FORMAT = "EEE, MMMMM dd, HH:mm a";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label time;
    @FXML
    private Label endTime;
    @FXML
    private Label personToMeet;

    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        title.setText(appointment.getTitle().value);
        time.setText("From: " + DATE_FORMATTER.format(appointment.getTime().value.getTime()));
        endTime.setText("To: " + DATE_FORMATTER.format(appointment.getEndTime().value.getTime()));
        if (appointment.getPersonToMeet() != null) {
            personToMeet.setText("With " + appointment.getPersonToMeet().getName());
        } else {
            personToMeet.setText("");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }
}
