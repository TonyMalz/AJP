package de.uniba.wiai.dsg.ajp.assignment2.literature.logic;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Helps to validate IDs and email addresses.
 * 
 * @author Simon Harrer, Joerg Lenhard
 * @version 1.0
 */
public class ValidationHelper {

	private static final String ZERO_OR_MORE = "*";
	private static final String ONE_OR_MORE = "+";

	/**
	 * Validates an ID.
	 * 
	 * An ID has to start with a letter followed by zero or more letters or
	 * numbers.
	 * 
	 * @param id
	 *            the id to be checked. must not be null.
	 * @return true if the id is valid, false otherwise
	 * 
	 * @throws NullPointerException
	 *             if id is null
	 */
	public static boolean isId(String id) {
		id = Objects.requireNonNull(id, "id passed is null");

		String startWithLetter = "[a-zA-Z]";
		String anyLetterOrNumber = "[a-zA-Z0-9]" + ZERO_OR_MORE;
		String regexForId = startWithLetter + anyLetterOrNumber;
		return Pattern.matches(regexForId, id);
	}

	/**
	 * Validates an email address.
	 * 
	 * This is a simple validation and does not conform to all correctness
	 * criteria for email addresses.
	 * 
	 * @param email
	 *            the email to be validated. must not be null.
	 * @return true if the email is valid, false otherwise
	 * 
	 * @throws NullPointerException
	 *             if email is null
	 */
	public static boolean isEmail(String email) {
		email = Objects.requireNonNull(email, "email passed is null");

		String name = "[a-zA-Z0-9._%-]" + ONE_OR_MORE;
		String at = "[@]";
		String domain = "[a-zA-Z0-9.-]" + ONE_OR_MORE;
		String dot = "[.]";
		String countryCode = "[a-zA-Z]{2,4}";
		String regexForEmail = name + at + domain + dot + countryCode;
		return Pattern.matches(regexForEmail, email);
	}

}
