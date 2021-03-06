package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * This class contains all Rentals and the name of one Costumer. It is possible
 * to print a customers statement.
 */
public class Customer {
	/** the name of the Customer. */
	private String name;
	/** All the active rentals of the customer. */
	private List<Rental> rentals = new LinkedList<Rental>();

	/**
	 * Default Constructor.
	 */
	public Customer() {

	}

	/**
	 * Constructor sets the name.
	 * 
	 * @param name
	 *            of the customer
	 */
	public Customer(final String name) {
		super();
		setName(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the rentals
	 */
	public List<Rental> getRentals() {
		return rentals;
	}

	/**
	 * @param rentals
	 *            the rentals to set
	 */
	public void setRentals(final List<Rental> rentals) {
		Objects.requireNonNull(rentals, "rentals is null");
		this.rentals = rentals;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		Objects.requireNonNull(name, "name is null");
		if (name.length() == 0) {
			throw new IllegalArgumentException("name is empty");
		}
		this.name = name;
	}

	/**
	 * Generates a statement containing the record of the rentals of the
	 * customer.
	 * <p>
	 * Example of the format:<br>
	 * Rental record for exampleCustomer <br>
	 * &nbsp &nbsp MovieA &nbsp &nbsp 30.00<br>
	 * &nbsp &nbsp MovieB &nbsp &nbsp 2.00<br>
	 * &nbsp &nbsp MovieC &nbsp &nbsp 0.00<br>
	 * Amount owed is 32.00<br>
	 * You earned 3 frequent renter points<br>
	 * 
	 * @return the statement as a string
	 */
	public String statement() {
		String result = "Rental Record for " + getName() + "\n";

		for (final Rental each : rentals) {

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(each.getCharge()) + "\n";
		}

		// add footer lines
		result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned "
				+ String.valueOf(getTotalFrequentRenterPoints())
				+ " frequent renter points";
		return result;
	}

	/**
	 * Generates a statement with coupon customer and adjusts value of used
	 * coupon.
	 * <p>
	 * Example of the format:<br>
	 * Rental record for exampleCustomer <br>
	 * &nbsp &nbsp MovieA &nbsp &nbsp 30.00<br>
	 * &nbsp &nbsp MovieB &nbsp &nbsp 2.00<br>
	 * &nbsp &nbsp MovieC &nbsp &nbsp 0.00<br>
	 * Amount owed is 17.00<br>
	 * #### Voucher redeemed ####<br>
	 * Code: &nbsp &nbsp &nbspXYZ123<br>
	 * Value: &nbsp &nbsp 15.00<br>
	 * ####<br>
	 * Total amount owed is 2.00<br>
	 * Voucher value left is 0.00<br>
	 * You earned 6 frequent renter points<br>
	 * 
	 * @param coupon
	 *            coupon value is subtracted of totalCharge and printed out
	 * @return the statement as a string
	 */
	public String statement(Coupon coupon) {
		if (coupon.getValue() < 0.0) {
			throw new IllegalArgumentException("Coupons value was less than 0");
		}

		String result = "Rental Record for " + getName() + "\n";

		for (final Rental each : rentals) {

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(each.getCharge()) + "\n";
		}

		// add footer lines

		result += "Amount owed is " + getTotalCharge() + "\n";
		// redeem voucher
		result += "#### Voucher redeemed ####\n";
		result += "Code: \t" + coupon.getCode() + "\n";
		result += "Value:\t" + coupon.getValue() + "\n";
		result += "####\n";
		double totalAmountOwed = getTotalCharge() - coupon.getValue();
		if (totalAmountOwed < 0.) {
			coupon.setValue(totalAmountOwed * -1);
			totalAmountOwed = 0.;
		} else {
			coupon.setValue(0);
		}
		result += "Total amount owed is " + totalAmountOwed + "\n";
		result += "Voucher value left is " + coupon.getValue() + "\n";
		// /
		result += "You earned "
				+ String.valueOf(getTotalFrequentRenterPoints() + 3)
				+ " frequent renter points";
		return result;
	}

	/**
	 * Generates a html statement with coupon.
	 * 
	 * Example of the format:<br>
	 * <H1>Rentals for <EM>exampleCustomer</EM></H1>
	 * <P>
	 * Movie A: 30.00<BR>
	 * Movie B: 2.00<BR>
	 * Movie C: 0.00<BR>
	 * <P>
	 * You owe <EM>32.00</EM>
	 * <P>
	 * On this rental you earned <EM>3</EM> frequent renter points
	 * <P>
	 * 
	 * @return the html statement as a string
	 */
	public String htmlStatement() {
		String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";

		for (final Rental each : rentals) {
			// show figures for each rental
			result += each.getMovie().getTitle() + ": "
					+ String.valueOf(each.getCharge()) + "<BR>\n";
		}

		// add footer lines
		result += "<P>You owe <EM>" + String.valueOf(getTotalCharge())
				+ "</EM><P>\n";
		result += "On this rental you earned <EM>"
				+ String.valueOf(getTotalFrequentRenterPoints())
				+ "</EM> frequent renter points<P>";
		return result;
	}

	/**
	 * Generates a html statement with coupon and adjusts value of used coupon.
	 * 
	 * Example of the format:<br>
	 * <H1>Rentals for <EM>exampleCustomer</EM></H1>
	 * <P>
	 * Movie A: 30.00<BR>
	 * Movie B: 2.00<BR>
	 * Movie C: 0.00<BR>
	 * <P>
	 * You owe <EM>17.00</EM>
	 * <P>
	 * #### Voucher redeemed ####<BR>
	 * Code: &nbsp &nbsp &nbsp XYZ123<BR>
	 * Value: &nbsp &nbsp 15.00<BR>
	 * ####<BR>
	 * Total amount owed is <EM>2.00</EM><BR>
	 * Voucher value left is 0.00<BR>
	 * On this rental you earned <EM>6</EM> frequent renter points
	 * <P>
	 * 
	 * @param coupon
	 *            coupon value is subtracted of totalCharge and printed out
	 * @return the html statement as a string
	 */
	public String htmlStatement(Coupon coupon) {
		if (coupon.getValue() < 0.0) {
			throw new IllegalArgumentException("Coupons value was less 0");
		}

		String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";

		for (final Rental each : rentals) {
			// show figures for each rental
			result += each.getMovie().getTitle() + ": "
					+ String.valueOf(each.getCharge()) + "<BR>\n";
		}

		result += "<P>You owe " + String.valueOf(getTotalCharge()) + "<P>\n";
		// redeem voucher
		result += "#### Voucher redeemed ####<BR>\n";
		result += "Code: \t" + coupon.getCode() + "<BR>\n";
		result += "Value:\t" + coupon.getValue() + "<BR>\n";
		result += "####<BR>\n";
		double totalAmountOwed = getTotalCharge() - coupon.getValue();
		if (totalAmountOwed < 0.) {
			coupon.setValue(totalAmountOwed * -1);
			totalAmountOwed = 0.;
		} else {
			coupon.setValue(0);
		}
		result += "Total amount owed is <EM>" + totalAmountOwed + "</EM><BR>\n";
		result += "Voucher value left is " + coupon.getValue() + "<BR>\n";
		// /
		result += "On this rental you earned <EM>"
				+ String.valueOf(getTotalFrequentRenterPoints() + 3)
				+ "</EM> frequent renter points<P>";
		return result;
	}

	/**
	 * Computes the total charge of this customer.
	 * 
	 * @return the total charge
	 */
	double getTotalCharge() {
		double result = 0;

		for (final Rental each : rentals) {
			result += each.getCharge();
		}
		return result;
	}

	/**
	 * Computes the total renter points of this customer for all movies.
	 * 
	 * @return the total amount of frequent renter points
	 */
	int getTotalFrequentRenterPoints() {
		int result = 0;

		for (final Rental each : rentals) {
			result += each.getFrequentRenterPoints();
		}
		return result;
	}
}
