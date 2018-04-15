# LimShiMinJonathan
###### /java/seedu/address/logic/commands/SortCommand.java
``` java

/**
 * Sorts contacts lexicographically.
 */

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts contacts lexicographically "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Contacts have been sorted!";

    @Override
    public CommandResult execute() {

        model.sortByNameFilteredPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

```
###### /java/seedu/address/model/person/UniqueContactList.java
``` java

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
```
###### /java/seedu/address/model/AddressBook.java
``` java
    public void sortContacts() {
        contacts.sortList();
    }
```
###### /java/seedu/address/model/ModelManager.java
``` java
    @Override
    public void sortByNameFilteredPersonList() {
        addressBook.sortContacts();
        indicateAddressBookChanged();
    }
```
