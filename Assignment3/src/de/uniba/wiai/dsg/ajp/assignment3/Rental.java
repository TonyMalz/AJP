package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.Objects;

public class Rental {
	// TODO test mock/Stub
	// TODO test integration

	private int daysRented;
	private Movie movie;

	public Rental() {
	}

	public Rental(Movie movie, int daysRented) {
		setMovie(movie);
		setDaysRented(daysRented);
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(final Movie movie) {
		Objects.requireNonNull(movie, "movie is null");

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
