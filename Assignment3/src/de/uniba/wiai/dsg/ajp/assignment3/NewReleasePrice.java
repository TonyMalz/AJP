package de.uniba.wiai.dsg.ajp.assignment3;

public class NewReleasePrice extends Price {
	@Override
	double getCharge(final int daysRented) {
		if (daysRented < 0) {
			throw new IllegalArgumentException("daysRented was less than 0");
		}
		double result = 3;
		if (daysRented > 1) {
			result += (daysRented - 1) * 3;
		}
		return result * getCopyrightFeeForCalc();
	}

	@Override
	int getFrequentRenterPoints(final int daysRented) {
		if (daysRented < 0) {
			throw new IllegalArgumentException("daysRented was less than 0");
		}
		if (daysRented > 1) {
			return 2;
		} else {
			return 1;
		}
	}

	@Override
	int getPriceCode() {
		return Movie.NEW_RELEASE;
	}

}
