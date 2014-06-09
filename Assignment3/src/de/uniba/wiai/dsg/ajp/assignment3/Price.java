package de.uniba.wiai.dsg.ajp.assignment3;

public abstract class Price {
	/** the percentage the creator of the film gets for every charge. */
	protected double copyrightFee = 0.42;

	// TODO test mock/Stub
	// TODO test integration

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

	/**
	 * @return current copyright fee
	 */
	public final double getCopyrightFee() {
		return copyrightFee;
	}

	protected double getCopyrightFeeForCalc() {
		return copyrightFee + 1;
	}

	/**
	 * @param copyrightFee
	 *            the new copyright fee
	 * @throws IllegalArgumentException
	 *             if new fee is less than current one
	 */
	public final void setCopyrightFee(final double copyrightFee) {
		if (copyrightFee < this.copyrightFee) {
			throw new IllegalArgumentException(
					"given copyright fee is less than current fee of "
							+ this.copyrightFee);
		}
		this.copyrightFee = copyrightFee;
	}

}
