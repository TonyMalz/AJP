package de.uniba.wiai.dsg.ajp.assignment3;

public class RegularPrice extends Price {
	@Override
	double getCharge(final int daysRented) {
		if (daysRented < 0) {
			throw new IllegalArgumentException("daysRented was less than 0");
		}
		double result = 2;
		if (daysRented > 2) {
			result += (daysRented - 2) * 1.5;
		}
		return result * getCopyrightFeeForCalc();
	}

	@Override
	int getPriceCode() {
		return Movie.REGULAR;
	}

}
