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
public class ChidrensPriceTest {

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
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.ChildrensPrice#getCharge(int)}.
	 */
	@Test
	public final void testGetChargeUpToThree() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		double charge = childPrice.getCharge(0);
		Assert.assertEquals(1.5, charge, 0.0);

		charge = childPrice.getCharge(1);
		Assert.assertEquals(1.5, charge, 0.0);

		charge = childPrice.getCharge(2);
		Assert.assertEquals(1.5, charge, 0.0);

		charge = childPrice.getCharge(3);
		Assert.assertEquals(1.5, charge, 0.0);

	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.ChildrensPrice#getCharge(int)}.
	 */
	@Test
	public final void testGetChargeGreaterThree() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		double charge = childPrice.getCharge(4);
		Assert.assertEquals(1.5 * 2, charge, 0.0);

		charge = childPrice.getCharge(10);
		Assert.assertEquals(1.5 * 8, charge, 0.0);

		charge = childPrice.getCharge(100);
		Assert.assertEquals(1.5 * 98, charge, 0.0);

		charge = childPrice.getCharge(5);
		Assert.assertEquals(1.5 * 3, charge, 0.0);

	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.ChildrensPrice#getCharge(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testGetChargeDaysRentedLessZero() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		childPrice.getCharge(-4);
	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.ChildrensPrice#getPriceCode()}.
	 */
	@Test
	public final void testGetPriceCode() {
		Assert.assertEquals(2, new ChildrensPrice().getPriceCode());
	}

	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.ChildrensPrice#getFrequentRenterPoints(int)}
	 * .
	 */
	@Test
	public final void testGetFrequentRenterPoints() {
		final Random r = new Random(123456789);
		final ChildrensPrice childPrice = new ChildrensPrice();
		for (int i = 0; i < 10000; i++) {
			Assert.assertEquals(1,
					childPrice.getFrequentRenterPoints(r.nextInt()));
		}
	}
	/**
	 * Test method for
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.ChildrensPrice#getCreatorCharge(double)}
	 * .
	 */
	@Test
	public final void testGetCreatorCharge() {
		fail("Not yet implemented"); // TODO
	}
}
