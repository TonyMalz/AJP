package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.Assert;
import org.junit.Test;

public class RentalTest {

	@Test
	public final void testRentalConstructor() {
		Movie movie = new Movie("Titel", 3);
		Rental rental = new Rental(movie, 1);
		Assert.assertEquals(movie, rental.getMovie());
		Assert.assertEquals(1, rental.getDaysRented());
	}

	@Test(expected = NullPointerException.class)
	public final void testSetMovieNull() {
		new Rental().setMovie(null);;
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetDaysRentedLessThanZero() {
		new Rental().setDaysRented(-3);
	}

}
