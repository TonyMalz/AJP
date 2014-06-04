package de.uniba.wiai.dsg.ajp.assignment3;

public class Rental {
	// TODO test mock/Stub
	// TODO test integration

	// TODO implment aufschlag
	private int daysRented;
	private Movie movie;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(final Movie movie) {
		if (movie == null) {
			throw new IllegalArgumentException("movie was null");
		}
		this.movie = movie;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(final int daysRented) {
		if (daysRented < 0) {
			throw new IllegalArgumentException("daysRented was less than 0");
		}
		this.daysRented = daysRented;
	}

	public double getCharge() {
		return movie.getCharge(daysRented);
	}

	public int getFrequentRenterPoints() {
		return movie.getFrequentRenterPoints(daysRented);
	}

}
