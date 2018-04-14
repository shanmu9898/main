package seedu.address.ui;

import java.util.logging.Logger;

import org.fxmisc.easybind.EasyBind;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.education.Class;

//@@author randypx-reused
//Reuse from PersonListPanel class with modification
/**
 * Panel containing the list of classes.
 */
public class ClassListPanel extends UiPart<Region> {
    private static final String FXML = "ClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassListPanel.class);

    @FXML
    private ListView<ClassCard> classListView;

    public ClassListPanel(ObservableList<Class> classList) {
        super(FXML);
        setConnections(classList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Class> classList) {
        ObservableList<ClassCard> mappedList = EasyBind.map(classList, (group)
            -> new ClassCard(group, classList.indexOf(group) + 1));
        classListView.setItems(mappedList);
        classListView.setCellFactory(listView -> new ClassListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ClassCard}.
     */
    class ClassListViewCell extends ListCell<ClassCard> {

        @Override
        protected void updateItem(ClassCard classCard, boolean empty) {
            super.updateItem(classCard, empty);

            if (empty || classCard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(classCard.getRoot());
            }
        }
    }

}
