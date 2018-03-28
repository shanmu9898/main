package seedu.address.ui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;
import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.layout.Region;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.model.event.Appointment;
import seedu.address.model.event.Event;


/**
 * The Calendar Panel of the App.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";

    @FXML
    private CalendarView calendarView;

    private ObservableList<Event> eventList;

    public CalendarPanel(ObservableList<Event> eventObservableList) {
        super(FXML);

        this.eventList = eventObservableList;

        calendarView = new CalendarView();
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());

        updateCalendar();
        disableViews();
        registerAsAnEventHandler(this);
    }

    /**
     * Creates a new a calendar with the update information
     */
    private void updateCalendar() {
        setTime();
        CalendarSource calendarSource = new CalendarSource("My Calendar");
        Calendar calendar = new Calendar("Appointments");
        calendar.setStyle(Calendar.Style.getStyle(0));
        calendar.setLookAheadDuration(Duration.ofDays(365));

        calendarSource.getCalendars().add(calendar);

        ArrayList<Entry> entries = getEntries(eventList);

        for (Entry entry : entries) {
            calendar.addEntry(entry);
        }
        calendarView.getCalendarSources().add(calendarSource);
    }

    private ArrayList<Entry> getEntries(ObservableList<Event> eventList) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (Event event : eventList) {
            if (event instanceof Appointment) {
                entries.add(getEntry(event));
            }
        }
        return entries;
    }

    private void setTime() {
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        calendarView.getCalendarSources().clear();
    }

    private Entry getEntry(Event event) {
        Appointment appointment = (Appointment) event;
        LocalDateTime ldtstart = LocalDateTime.ofInstant(
                appointment.getTime().value.getTime().toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtend = LocalDateTime.ofInstant(
                appointment.getEndTime().value.getTime().toInstant(), ZoneId.systemDefault());
        String description = appointment.getTitle().value;
        return new Entry(description, new Interval(ldtstart, ldtend));
    }

    @Subscribe
    private void handleNewAppointmentEvent(AddressBookChangedEvent event) {
        eventList = event.data.getEventList();
        Platform.runLater(
                this::updateCalendar
        );
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
        calendarView.showMonthPage();
    }
}
