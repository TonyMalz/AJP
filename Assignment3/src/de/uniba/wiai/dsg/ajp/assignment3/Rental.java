package de.uniba.wiai.dsg.ajp.assignment3;

public class Rental {
    // TODO imput validation
    // TODO test mock/Stub
    // TODO test integration

    // TODO implment aufschlag
    private int daysRented;
    private Movie movie;

    public Movie getMovie() {
	return movie;
    }

    public void setMovie(final Movie movie) {
	this.movie = movie;
    }

    public int getDaysRented() {
	return daysRented;
    }

    public void setDaysRented(final int daysRented) {
	this.daysRented = daysRented;
    }

    public double getCharge() {
	return movie.getCharge(daysRented);
    }

    public int getFrequentRenterPoints() {
	return movie.getFrequentRenterPoints(daysRented);
    }

}
