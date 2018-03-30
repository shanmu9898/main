package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

//@@author Sisyphus25
/**
 * Indicates a request to change them
 */
public class ThemeChangeEvent extends BaseEvent {
    public final String theme;

    public ThemeChangeEvent(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
