package seedu.address.commons.events.ui;

//@@author Sisyphus25

import seedu.address.commons.events.BaseEvent;

/**
 * Indicates a request to toggle List
 */
public class ToggleListEvent extends BaseEvent {
    public final String list;

    public ToggleListEvent(String list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
