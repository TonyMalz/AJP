package de.uniba.wiai.dsg.ajp.assignment3;

public class NewReleasePrice extends Price {
    // TODO imput validation
    // TODO test mock/Stub
    // TODO test integration
    @Override
    double getCharge(final int daysRented) {
	return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(final int daysRented) {
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
