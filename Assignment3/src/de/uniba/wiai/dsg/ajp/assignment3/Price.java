package de.uniba.wiai.dsg.ajp.assignment3;

public abstract class Price {
	// TODO test mock/Stub
	// TODO test integration

	// TODO implment new price for series
	/**
	 * Computes the charge depending on the type of the Price.<br>
	 * Types:<br>
	 * <UL>
	 * <Li>Regular: Up to 2 days the charge is 1.5 otherwise 1.5 plus 2 for each
	 * day.
	 * <Li>New release: Every day costs 3
	 * <Li>Children: Up to 3 days the charge is 1.5 otherwise 1.5 plus 1.5 for
	 * each day
	 * <UL>
	 * 
	 * @param daysRented
	 *            the duration of the rental in days.
	 * @return the charge
	 */
	abstract double getCharge(int daysRented);

	/**
	 * Computes the frequent renter points for this type of price.<br>
	 * <UL>
	 * <Li>Regular: 1
	 * <Li>New release: 1 when One day rental, 2 when greater
	 * <Li>Children: 1
	 * <UL>
	 * 
	 * @param daysRented
	 *            the amount of days rented
	 * @return the amount of points earned.
	 */
	int getFrequentRenterPoints(final int daysRented) {
		return 1;
	}

	/**
	 * Get the price code for this type of price.
	 * <UL>
	 * <Li>Regular: 0
	 * <Li>Children: 1
	 * <Li>New release: 2
	 * <UL>
	 * 
	 * @return the price code
	 */
	abstract int getPriceCode();

}
