/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.fail;

import java.util.Random;

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
public class RegularPriceTest {

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
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.RegularPrice#getCharge(int)}.
	 */
	@Test
	public final void testGetChargeUpToThree() {
		final RegularPrice regularPrice = new RegularPrice();
		double charge = regularPrice.getCharge(0);
		Assert.assertEquals(2.0, charge, 0.0);

		charge = regularPrice.getCharge(1);
		Assert.assertEquals(2.0, charge, 0.0);

		charge = regularPrice.getCharge(2);
		Assert.assertEquals(2.0, charge, 0.0);

	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.RegularPrice#getCharge(int)}.
	 */
	@Test
	public final void testGetChargeGreaterThree() {
		final RegularPrice regularPrice = new RegularPrice();
		double charge = regularPrice.getCharge(4);
		Assert.assertEquals(1.5 * 2 + 2.0, charge, 0.0);

		charge = regularPrice.getCharge(10);
		Assert.assertEquals(1.5 * 8 + 2.0, charge, 0.0);

		charge = regularPrice.getCharge(100);
		Assert.assertEquals(1.5 * 98 + 2.0, charge, 0.0);

		charge = regularPrice.getCharge(5);
		Assert.assertEquals(1.5 * 3 + 2.0, charge, 0.0);

	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.RegularPrice#getCharge(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testGetChargeDaysRentedLessZero() {
		final RegularPrice regularPrice = new RegularPrice();
		regularPrice.getCharge(-4);
	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.RegularPrice#getPriceCode()}.
	 */
	@Test
	public final void testGetPriceCode() {
		Assert.assertEquals(0, new RegularPrice().getPriceCode());
	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.RegualarPrice#getFrequentRenterPoints(int)}
	 * .
	 */
	@Test
	public final void testGetFrequentRenterPoints() {
		final Random r = new Random(123456789);
		final RegularPrice regularPrice = new RegularPrice();
		for (int i = 0; i < 10000; i++) {
			Assert.assertEquals(1,
					regularPrice.getFrequentRenterPoints(r.nextInt()));
		}
	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.RegularPrice#getCreatorCharge(double)}
	 * .
	 */
	@Test
	public final void testGetCreatorCharge() {
		fail("Not yet implemented"); // TODO
	}
}
