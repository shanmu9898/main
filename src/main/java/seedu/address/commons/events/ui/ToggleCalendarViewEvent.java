package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

//@@author Sisyphus25
/**
 * Indicates a request to toggle Calendar view mode
 */
public class ToggleCalendarViewEvent extends BaseEvent {
    public final Character viewMode;

    public ToggleCalendarViewEvent(Character viewMode) {
        this.viewMode = viewMode;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
