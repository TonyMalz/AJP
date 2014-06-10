/**
 * 
 */
package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class ChidrensPriceTest {

	@Test
	public final void testGetChargeUpToThree() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		double charge = childPrice.getCharge(0);
		Assert.assertEquals(1.5 * 1.42, charge, 0.0);

		charge = childPrice.getCharge(1);
		Assert.assertEquals(1.5 * 1.42, charge, 0.0);

		charge = childPrice.getCharge(2);
		Assert.assertEquals(1.5 * 1.42, charge, 0.0);

		charge = childPrice.getCharge(3);
		Assert.assertEquals(1.5 * 1.42, charge, 0.0);

	}

	@Test
	public final void testGetChargeGreaterThree() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		double charge = childPrice.getCharge(4);
		Assert.assertEquals(1.5 * 2 * 1.42, charge, 0.0);

		charge = childPrice.getCharge(10);
		Assert.assertEquals(1.5 * 8 * 1.42, charge, 0.0);

		charge = childPrice.getCharge(100);
		Assert.assertEquals(1.5 * 98 * 1.42, charge, 0.0);

		charge = childPrice.getCharge(5);
		Assert.assertEquals(1.5 * 3 * 1.42, charge, 0.0);

	}

	@Test(expected = IllegalArgumentException.class)
	public final void testGetChargeDaysRentedLessZero() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		childPrice.getCharge(-4);
	}

	@Test
	public final void testGetPriceCode() {
		Assert.assertEquals(2, new ChildrensPrice().getPriceCode());
	}

	@Test
	public final void testGetFrequentRenterPoints() {
		final Random r = new Random(123456789);
		final ChildrensPrice childPrice = new ChildrensPrice();
		for (int i = 0; i < 10000; i++) {
			Assert.assertEquals(1,
					childPrice.getFrequentRenterPoints(r.nextInt()));
		}
	}

	@Test
	public final void testSetCopyrightFee() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		childPrice.setCopyrightFee(20.5);
		Assert.assertEquals(20.5, childPrice.getCopyrightFee(), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetCopyrightFeeLowerThanCurrentValue() {
		final ChildrensPrice childPrice = new ChildrensPrice();
		childPrice.setCopyrightFee(20.5);
		childPrice.setCopyrightFee(10.5);
	}

	@Test
	public final void testGetCopyrightFeeForCalc() {
		Assert.assertEquals(1.42,
				new ChildrensPrice().getCopyrightFeeForCalc(), 0.0);
	}
}
