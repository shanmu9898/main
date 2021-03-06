package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalContacts;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;
import static seedu.address.ui.testutil.GuiTestAssert.assertPersonCardEquals;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.person.Person;

public class PersonListPanelTest extends GuiUnitTest {
    private static final ObservableList<Person> TYPICAL_PERSONS =
            FXCollections.observableList(getTypicalContacts());

    private static final JumpToListRequestEvent JUMP_TO_SECOND_EVENT = new JumpToListRequestEvent(INDEX_SECOND);

    private PersonListPanelHandle personListPanelHandle;

    @Before
    public void setUp() {
        PersonListPanel personListPanel = new PersonListPanel(TYPICAL_PERSONS);
        uiPartRule.setUiPart(personListPanel);

        personListPanelHandle = new PersonListPanelHandle(getChildNode(personListPanel.getRoot(),
                PersonListPanelHandle.PERSON_LIST_VIEW_ID));
    }

    @Test
    public void display() {
        for (int i = 0; i < TYPICAL_PERSONS.size(); i++) {
            personListPanelHandle.navigateToCard(TYPICAL_PERSONS.get(i));
            Person expectedPerson = TYPICAL_PERSONS.get(i);
            PersonCardHandle actualCard = personListPanelHandle.getPersonCardHandle(i);

            assertCardDisplaysPerson(expectedPerson, actualCard);
            assertEquals(Integer.toString(i + 1), actualCard.getId());
        }
    }

    @Test
    public void handleJumpToListRequestEvent() {
        postNow(JUMP_TO_SECOND_EVENT);
        guiRobot.pauseForHuman();

        PersonCardHandle expectedCard = personListPanelHandle.getPersonCardHandle(INDEX_SECOND.getZeroBased());
        PersonCardHandle selectedCard = personListPanelHandle.getHandleToSelectedCard();
        assertPersonCardEquals(expectedCard, selectedCard);
    }
}
