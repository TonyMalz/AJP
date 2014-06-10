package de.uniba.wiai.dsg.ajp.assignment3;

public class SeriesPrice extends Price {
	@Override
	double getCharge(final int daysRented) {
		if (daysRented < 0) {
			throw new IllegalArgumentException("daysRented was less than 0");
		}
		double result = 3.5;
		if (daysRented > 1)
			result += 3;
		if (daysRented > 2) {
			result += (daysRented - 2) * 0.5;
		}
		return result * getCopyrightFeeForCalc();
	}
	@Override
	int getFrequentRenterPoints(final int daysRented) {
		if (daysRented < 0) {
			throw new IllegalArgumentException("daysRented was less than 0");
		}
		return 3;
	}

	@Override
	int getPriceCode() {
		return Movie.SERIES;
	}

	@Override
	public double getCopyrightFeeForCalc() {
		return copyrightFee * 2 + 1;
	}
}
