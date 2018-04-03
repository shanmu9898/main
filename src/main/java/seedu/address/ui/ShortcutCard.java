package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.shortcuts.ShortcutDoubles;

/**
 * An UI component that displays information of a {@code Shortcut Double}
 */
public class ShortcutCard extends UiPart<Region> {

    private static final String FXML = "ShortcutListCard.fxml";

    public  final ShortcutDoubles shortcutDoubles;

    @FXML
    private HBox cardPane;
    @FXML
    private Label shortcut;
    @FXML
    private Label command;
    @FXML
    private Label id;

    public ShortcutCard(ShortcutDoubles shortcutDoubles, int displayedIndex) {
        super(FXML);

        this.shortcutDoubles = shortcutDoubles;
        id.setText(displayedIndex + ". ");
        shortcut.setText("===> " + shortcutDoubles.shortcutWord);
        command.setText(shortcutDoubles.commandWord);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShortcutCard)) {
            return false;
        }

        // state check
        ShortcutCard card = (ShortcutCard) other;
        return id.getText().equals(card.id.getText())
                && shortcutDoubles.equals(card.shortcutDoubles);
    }
}
