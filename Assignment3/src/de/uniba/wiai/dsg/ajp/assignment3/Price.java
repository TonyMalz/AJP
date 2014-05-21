package de.uniba.wiai.dsg.ajp.assignment3;

public abstract class Price {
    // TODO imput validation
    // TODO test mock/Stub
    // TODO test integration

    // TODO implment new price for series
    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(final int daysRented) {
	return 1;
    }

    abstract int getPriceCode();

}
