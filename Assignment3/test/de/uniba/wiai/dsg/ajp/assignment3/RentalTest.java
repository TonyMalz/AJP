/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author mathias
 * 
 */
public class RentalTest {
	// not implemented because trivial getter/setters:
	// getMovie()
	// setMovie()
	// getDaysRented()
	// setDaysRented()

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

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
