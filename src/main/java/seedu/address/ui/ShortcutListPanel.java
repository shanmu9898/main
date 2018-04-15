//@@author shanmu9898
package seedu.address.ui;

import java.util.logging.Logger;

import org.fxmisc.easybind.EasyBind;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * Panel containing the list of Shortcuts.
 */
public class ShortcutListPanel extends  UiPart<Region> {
    private static final String FXML = "ShortcutListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<ShortcutCard> shortcutListView;

    public ShortcutListPanel(ObservableList<ShortcutDoubles> shortcutList) {
        super(FXML);
        setConnections(shortcutList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<ShortcutDoubles> shortcutList) {
        ObservableList<ShortcutCard> mappedList = EasyBind.map(shortcutList, (shortcutDoubles) ->
                new ShortcutCard(shortcutDoubles, shortcutList.indexOf(shortcutDoubles) + 1));
        shortcutListView.setItems(mappedList);
        shortcutListView.setCellFactory(listView -> new ShortcutListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code AppointmentCard}.
     */
    class ShortcutListViewCell extends ListCell<ShortcutCard> {

        @Override
        protected void updateItem(ShortcutCard shortcutCard, boolean isEmpty) {
            super.updateItem(shortcutCard, isEmpty);

            if (isEmpty || shortcutCard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(shortcutCard.getRoot());
            }
        }
    }
}
