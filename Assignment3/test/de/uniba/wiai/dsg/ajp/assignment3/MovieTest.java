/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.Assert;
import org.junit.Test;

public class MovieTest {

	@Test
	public final void testMovieConstructor() {
		Movie movie = new Movie("Titel", 1);
		Assert.assertEquals("Titel", movie.getTitle());
		Assert.assertEquals(1, movie.getPriceCode());
	}

	@Test(expected = NullPointerException.class)
	public final void testSetTitleNull() {
		new Movie().setTitle(null);;
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetTitleEmptyString() {
		new Movie().setTitle("");;
	}

	@Test
	public final void testSetPriceCodeRegular() {
		Movie movie = new Movie("Titel", 0);
		Assert.assertEquals(0, movie.getPriceCode());
	}

	@Test
	public final void testSetPriceCodeChildrens() {
		Movie movie = new Movie("Titel", 2);
		Assert.assertEquals(2, movie.getPriceCode());
	}

	@Test
	public final void testSetPriceCodeNewRelease() {
		Movie movie = new Movie("Titel", 1);
		Assert.assertEquals(1, movie.getPriceCode());
	}

	@Test
	public final void testSetPriceCodeSeries() {
		Movie movie = new Movie("Titel", 3);
		Assert.assertEquals(3, movie.getPriceCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetPriceCodeGreaterThanThree() {
		new Movie("Titel", 234);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetPriceCodeLessThanZero() {
		new Movie("Titel", -234);
	}

}
