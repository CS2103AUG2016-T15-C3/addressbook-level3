package seedu.addressbook.commands;


import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class Number extends Command{
	 public static final String COMMAND_WORD = "findbynumber";

	    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose number contain any of "
	            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
	            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
	            + "Example: " + COMMAND_WORD + " 98765432";

	    private String keywords;

	    public Number(String keywords) {
	        this.keywords = keywords;
	    }

	    /**
	     * Returns copy of keywords in this command.
	     */
	    public String getKeywords() {
	        return keywords;
	    }

	    @Override
	    public CommandResult execute() {
	        final List<ReadOnlyPerson> personsFound = getPersonsWithNumberContainingAnyKeyword(keywords);
	        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
	    }

	    /**
	     * Retrieve all persons in the address book whose names contain some of the specified keywords.
	     *
	     * @param keywords for searching
	     * @return list of persons found
	     */
	    private List<ReadOnlyPerson> getPersonsWithNumberContainingAnyKeyword(String keywords) {
	        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
	        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
	            final String number = new String(person.getPhone().toString());
	            if (number.compareTo(keywords)==0) {
	                matchedPersons.add(person);
	            }
	        }
	        return matchedPersons;
	    }

}
