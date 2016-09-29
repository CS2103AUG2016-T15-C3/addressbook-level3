package seedu.addressbook.data.person;

import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashMap;
import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson {

    private Name name;
    private HashMap<String, Phone> phoneList;
    private HashMap<String, Email> emailList;
    private HashMap<String, Address> addressList;

    private final UniqueTagList tags;
    /**
     * Assumption: Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, UniqueTagList tags) {
        this.name = name;
        this.phoneList.put("default", phone);
        this.emailList.put("default", email);
        this.addressList.put("default", address);
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Person(ReadOnlyPerson source) {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    /**
     * Returns default phone number if user does not request a specific type of phone number
     */
    public Phone getPhone() {
        return phoneList.get("default");
    }
    
    /**
     * Returns the request type of phone number
     */
    public Phone getPhone(String type) {
        return phoneList.get(type);
    }
    
    /**
     * Returns default email if user does not request a specific type of email
     */
    public Email getEmail() {
        return emailList.get("default");
    }
    
    /**
     * Returns the request type of email
     */
    public Email getEmail(String type) {
        return emailList.get(type);
    }
    
    /**
     * Returns default address if user does not request a specific type of address
     */
    public Address getAddress() {
        return addressList.get("default");
    }
    
    /**
     * Returns the request type of address
     */
    public Address getAddress(String type) {
        return addressList.get(type);
    }
    
    /**
     * Add an alternative phone number to a person
     * Specifies the type of the phone number, e.g. "home," "work"
     */
    public void addAlternativePhone(String type, Phone phone) {
    	this.phoneList.put(type, phone);
    }
    
    /**
     * Add an alternative email address to a person
     * Specifies the type of the email address, e.g. "personal," "work"
     */
    public void addAlternativeEmail(String type, Email email) {
    	this.emailList.put(type, email);
    }
    
    /**
     * Add an alternative address to a person
     * Specifies the type of the address, e.g. "home," "work"
     */
    public void addAlternativePhone(String type, Address address) {
    	this.addressList.put(type, address);
    }
    
    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phoneList.get("default"), emailList.get("default"), addressList.get("default"), tags);
    }

    @Override
    public String toString() {
        return getAsTextShowAll();
    }

}
