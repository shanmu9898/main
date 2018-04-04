# Sisyphus25-reused
###### /resources/view/TagColour.css
``` css
Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
 */
#tags .teal {
    -fx-text-fill: white;
    -fx-background-color: #3e7b91;
}

#tags .red {
    -fx-text-fill: black;
    -fx-background-color: red;
}

#tags .yellow {
    -fx-background-color: yellow;
    -fx-text-fill: black;
}

#tags .blue {
    -fx-text-fill: white;
    -fx-background-color: blue;
}

#tags .orange {
    -fx-text-fill: black;
    -fx-background-color: orange;
}

#tags .brown {
    -fx-text-fill: white;
    -fx-background-color: brown;
}

#tags .green {
    -fx-text-fill: black;
    -fx-background-color: green;
}

#tags .pink {
    -fx-text-fill: black;
    -fx-background-color: pink;
}

#tags .black {
    -fx-text-fill: white;
    -fx-background-color: black;
}

#tags .grey {
    -fx-text-fill: black;
    -fx-background-color: grey;
}
```
###### /java/seedu/address/ui/CalendarPanel.java
``` java
    //Reused from https://github.com/CS2103AUG2017-T17-B2/main with modifications
    private void setTime() {
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
    }

    @Subscribe
    private void handleToggleCalendarViewEvent(ToggleCalendarViewEvent event) {
        Character c = event.viewMode;
        Platform.runLater(() -> toggleView(c));
    }

    public CalendarView getRoot() {
        return this.calendarView;
    }

    /**
     * Remove clutter from interface
     */
    private void disableViews() {
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowSearchField(false);
        calendarView.setShowSearchResultsTray(false);
        calendarView.setShowPrintButton(false);
        calendarView.setShowPageSwitcher(false);
        calendarView.setShowSourceTrayButton(false);
        calendarView.setShowPageToolBarControls(false);
        calendarView.setShowToolBar(false);
        calendarView.setShowSourceTray(false);

        calendarView.showDayPage();
    }

    /**
     * Changes calendar view accordingly
     */
    private void toggleView(Character c) {
        switch(c) {
        case ('d'):
            calendarView.showDayPage();
            return;
        case ('w'):
            calendarView.showWeekPage();
            return;
        case ('m'):
            calendarView.showMonthPage();
            return;
        default:
            //should not reach here
            assert (false);
        }
    }
```
###### /java/seedu/address/ui/PersonCard.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    /**
     * Returns the color style for {@code tagName}'s label.
     */
    private void initTags(Person person) {
        person.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.getStyleClass().add(tag.tagColorStyle);
            tags.getChildren().add(tagLabel);
        });
    }

```
###### /java/seedu/address/model/tag/Tag.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    private static final String[] TAG_COLOR_STYLES = {"teal", "red", "yellow", "blue", "orange", "brown",
        "green", "pink", "black", "grey"};
```
###### /java/seedu/address/model/tag/Tag.java
``` java
    //Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits/167b3d0b4f7ad34296d2fbf505f9ae71f983f53c
    /**
     * Returns a color style for {@code tagName}
     */
    private String getTagColorStyle(String tagName) {
        // we use the hash code of the tag name to generate a random color, so that the color remain consistent
        // between different runs of the program while still making it random enough between tags.
        return TAG_COLOR_STYLES[Math.abs(tagName.hashCode()) % TAG_COLOR_STYLES.length];
    }


}
```
