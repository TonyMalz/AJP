package de.uniba.wiai.dsg.ajp.assignment3;

public class Rental {
	// TODO test mock/Stub
	// TODO test integration

	private int daysRented;
	private Movie movie;
	private double copyrightFee = 0.;

	public Rental() {
	}

	public Rental(Movie movie, int daysRented) {
		setMovie(movie);
		setDaysRented(daysRented);
	}
	/**
	 * @return current copyright fee
	 */
	public final double getCopyrightFee() {
		return copyrightFee;
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
