package de.uniba.wiai.dsg.ajp.assignment3;

public class ChildrensPrice extends Price {
	// TODO test mock/Stub
	// TODO test integration
	@Override
	double getCharge(final int daysRented) {
		if (daysRented < 0) {
			throw new IllegalArgumentException("daysRented was less than 0");
		}
		double result = 1.5;
		if (daysRented > 3) {
			result += (daysRented - 3) * 1.5;
		}
		return result;
	}

	@Override
	int getPriceCode() {
		return Movie.CHILDRENS;
	}

}
