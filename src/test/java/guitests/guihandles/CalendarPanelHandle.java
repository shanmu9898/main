package guitests.guihandles;

import javafx.scene.Node;

/**
 * Provides a handle for CalendarPanel
 */
public class CalendarPanelHandle extends NodeHandle<Node> {

    public static final String CALENDAR_VIEW_ID = "#calendarPlaceholder";

    protected CalendarPanelHandle(Node rootNode) {
        super(rootNode);
    }
}
