package seedu.address.model.person;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;



//@@author randypx
/**
 * A list that is the aggregation of {@code UniquePersonList} and {@code UniqueStudentList}
 * and is the list displayed in the GUI.
 * This list remains up-to-date by listening to the changes of both lists and is not changed by anything else.
 */
public class UniqueContactList {
    private final UniquePersonList persons;
    private final UniqueStudentList students;
    private final ObservableList<Person> combinedList = FXCollections.observableArrayList();

    public UniqueContactList(UniquePersonList p, UniqueStudentList s) {
        persons = p;
        students = s;
        persons.addListener(this);
        students.addListener(this);
    }

    /**
     * This method is called when there is a change in eithor {@code UniquePersonList} or {@code UniqueStudentList}.
     * @param c this contains the change(s) that has occured.
     */
    public void updateList(ListChangeListener.Change<? extends Person> c) {
        while (c.next()) {
            if (c.wasReplaced()) {
                for (int i = 0; i < c.getRemovedSize(); i++) {
                    int index = combinedList.indexOf(c.getRemoved().get(i));
                    combinedList.set(index, c.getAddedSubList().get(i));
                }
                if (c.getTo() > c.getRemovedSize()) {
                    for (int i = c.getRemovedSize(); i < c.getTo(); i++) {
                        combinedList.add(c.getAddedSubList().get(i));
                    }
                }
            } else if (c.wasRemoved()) {
                combinedList.removeAll(c.getRemoved());
            } else if (c.wasAdded()) {
                combinedList.addAll(c.getAddedSubList());
            }
        }
    }

    //@@author LimShiMinJonathan

    /**
     * Returns a sorted list of contacts.
     */

    public void sortList() {
        Comparator<Person> sortByName = new Comparator<Person>() {
            @Override
            public int compare (Person contact1, Person contact2)  {
                return contact1.getName().fullName.compareToIgnoreCase(contact2.getName().fullName);
            }
        };
        FXCollections.sort(combinedList, sortByName);
    }
    //@@author

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asObservableList() {
        return FXCollections.unmodifiableObservableList(combinedList);
    }

}
