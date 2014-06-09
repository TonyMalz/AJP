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
public class MovieTest {
	// not implemented because trivial getter/setters:
	// getTitle()
	// setTitle()

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

	/**
	 * Test method for {@link de.uniba.wiai.dsg.ajp.assignment3.Movie#Movie()}.
	 */
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

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.Movie#setPriceCode(int)}.
	 */
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
