
package seedu.address.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.education.Class;

//@@author randypx
/**
 * An UI component that displays information of a {@code Class}.
 */
public class ClassCard extends UiPart<Region> {

    private static final String FXML = "ClassListCard.fxml";
    private static final String DATE_FORMAT = "dd MMMMM yy";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public final Class classroom;

    @FXML
    private HBox cardPane;
    @FXML
    private Label className;
    @FXML
    private Label id;
    @FXML
    private Label subject;
    @FXML
    private Label duration;
    @FXML
    private FlowPane students;

    public ClassCard(Class group, int displayedIndex) {
        super(FXML);
        this.classroom = group;
        id.setText(displayedIndex + ". ");
        className.setText(group.getName().fullName);
        subject.setText(group.getSubject().value);
        duration.setText("From " + DATE_FORMATTER.format(group.getStartDate().value.getTime()) + " to "
                + DATE_FORMATTER.format(group.getEndDate().value.getTime()));
        group.getStudents().forEach(student -> students.getChildren().add(new Label(student.fullName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassCard)) {
            return false;
        }

        // state check
        ClassCard card = (ClassCard) other;
        return id.getText().equals(card.id.getText())
                && classroom.equals(card.classroom);
    }
}
