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
import seedu.address.commons.events.model.AppointmentListChangedEvent;
import seedu.address.commons.events.ui.ToggleCalendarViewEvent;
import seedu.address.model.event.Appointment;

//@@author Sisyphus25
/**
 * The Calendar Panel of the App.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";

    @FXML
    private CalendarView calendarView;
    private Calendar calendar;

    private ObservableList<Appointment> appointmentList;

    public CalendarPanel(ObservableList<Appointment> appointmentObservableList) {
        super(FXML);
        this.appointmentList = appointmentObservableList;

        calendarView = new CalendarView();
        CalendarSource calendarSource = new CalendarSource("My Calendar");
        calendar = new Calendar("Appointments");

        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());

        calendarView.getCalendarSources().add(calendarSource);
        calendarSource.getCalendars().add(calendar);
        calendar.setStyle(Calendar.Style.getStyle(0));
        calendar.setLookAheadDuration(Duration.ofDays(365));

        updateCalendar();
        disableViews();
        registerAsAnEventHandler(this);
    }

    /**
     * Clear the entry list in the CalendarFX calendar and
     * populate it with appointment in the updated appointmentList
     */
    private void updateCalendar() {
        calendar.clear();
        ArrayList<Entry> entries = getEntries();
        for (Entry entry : entries) {
            calendar.addEntry(entry);
        }
    }

    private ArrayList<Entry> getEntries() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            entries.add(getEntry(appointment));
        }
        return entries;
    }

    private Entry getEntry(Appointment appointment) {
        LocalDateTime ldtstart = LocalDateTime.ofInstant(
                appointment.getTime().value.getTime().toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtend = LocalDateTime.ofInstant(
                appointment.getEndTime().value.getTime().toInstant(), ZoneId.systemDefault());
        String description = appointment.getTitle().value;
        return new Entry(description, new Interval(ldtstart, ldtend));
    }

    @Subscribe
    private void handleAppointmentListChangedEvent(AppointmentListChangedEvent event) {
        appointmentList = event.appointmentList;
        Platform.runLater(
                this::updateCalendar
        );
    }


    //@@author Sisyphus25-reused
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
    //@@author
}
