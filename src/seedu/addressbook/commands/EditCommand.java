package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Edit contact detail of a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command {

	public static final String COMMAND_WORD = "edit";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edit contact detail (ie. Phone, Email or Address) of a person identified using it's last displayed index from the address book.\n\t"
			+ "Parameter: INDEX CONTACTTYPE/NEWCONTACT\n\t"
			+ "Example: " + COMMAND_WORD + " 1 p/98765432";
	
	public static final String EDIT_CONTACT_TYPE_PHONE = "p";
	public static final String EDIT_CONTACT_TYPE_EMAIL = "e";
	public static final String EDIT_CONTACT_TYPE_ADDRESS = "a";
	
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_INVALID_CONTACT_TYPE = "The contact type: %1$s is invalid";
    public static final String MESSAGE_INVALID_CONTACT_DETAIL = "The contact detail: %1$s is invalid";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    
    private final String contactType;
    private final String newContact;
    
    public EditCommand (int targetVisibleIndex, String contactType, String newContact) {
    	super(targetVisibleIndex);
    	this.contactType = contactType;
    	this.newContact = newContact;
    }
    
    @Override
    public CommandResult execute() {
        try {
        	final ReadOnlyPerson target = getTargetPerson();
        	Person personWithNewContact;
        	switch (contactType) {
	    		case EDIT_CONTACT_TYPE_PHONE:
	    			Phone newPhone = new Phone(newContact, target.getPhone().isPrivate());
	    			personWithNewContact = new Person(target.getName(), newPhone, target.getEmail(), target.getAddress(), target.getTags());
	    			break;
	    		case EDIT_CONTACT_TYPE_EMAIL:
	    			Email newEmail = new Email(newContact, target.getEmail().isPrivate());
	    			personWithNewContact = new Person(target.getName(), target.getPhone(), newEmail, target.getAddress(), target.getTags());
	    			break;
	    		case EDIT_CONTACT_TYPE_ADDRESS:
	    			Address newAddress = new Address(newContact, target.getAddress().isPrivate());
	    			personWithNewContact = new Person(target.getName(), target.getPhone(), target.getEmail(), newAddress, target.getTags());
	    			break;
	    		default:
	    			return new CommandResult(String.format(MESSAGE_INVALID_CONTACT_TYPE, contactType));
        	}
            addressBook.editPerson(target, personWithNewContact);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, personWithNewContact));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        } catch (IllegalValueException ive) {
        	return new CommandResult(String.format(MESSAGE_INVALID_CONTACT_DETAIL, newContact));
        }
    }
}
